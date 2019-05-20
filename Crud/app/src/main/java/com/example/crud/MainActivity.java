package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.crud.adapters.RVPokemonAdapter;
import com.example.crud.model.Pokemon;
import com.example.crud.service.GlobalRequest;
import com.example.crud.service.Queue;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RVPokemonAdapter rvPokemonAdapter;
    private Queue queue;
    private List<Pokemon>pokemons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemons = new ArrayList<>();
        queue = Queue.getInstance(this);

        recyclerView = findViewById(R.id.rv_pokemons);
        rvPokemonAdapter = new RVPokemonAdapter(MainActivity.this, pokemons);

        recyclerView.setAdapter(rvPokemonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.VERTICAL, false));

    }

    public void getAllPokemons(){
        StringRequest request = GlobalRequest.getRequest(
                GlobalRequest.POKEMON, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray rawObjects = new JSONArray(response);
                            pokemons.clear();

                            for (int i = 0; i < rawObjects.length(); i++) {
                                JSONObject wildPokemon = (JSONObject) rawObjects.get(i);
                                Pokemon pokemon = new Pokemon(
                                        wildPokemon.get("id").toString(),
                                        wildPokemon.get("name").toString(),
                                        wildPokemon.get("type").toString()
                                );

                                pokemons.add(pokemon);
                            }

                            rvPokemonAdapter.notifyDataSetChanged();
                        }catch (Exception e){}



                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "No se obtuvieron los datos", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        Log.e("***ERROR***", error.toString());
                    }
                }
        );

        queue.addtoQueue(request);
    }


    public void addPokemon(View view){
        startActivity(new Intent(this, PokemonCreate.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.getAllPokemons();
    }
}
