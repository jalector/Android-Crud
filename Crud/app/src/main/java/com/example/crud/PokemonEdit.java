package com.example.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.crud.model.Pokemon;
import com.example.crud.service.GlobalRequest;
import com.example.crud.service.Queue;

import org.json.JSONObject;

public class PokemonEdit extends AppCompatActivity {

    Pokemon pokemon;
    Queue queue;

    EditText name;
    EditText type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_edit);

        queue = Queue.getInstance(this);

        Bundle extra = getIntent().getExtras();
        pokemon = (Pokemon) extra.getSerializable("pokemon");

        name = this.findViewById(R.id.edit_pokemon_name);
        type = this.findViewById(R.id.edit_pokemon_type);

        name.setText(pokemon.getName());
        type.setText(pokemon.getType());

    }


    public void save(View view){
        JSONObject body = new JSONObject();
        try{
            body.put("name", name.getText().toString());
            body.put("type", type.getText().toString());

        }catch(Exception e){
            Toast.makeText(PokemonEdit.this, "Error al guardar la información", Toast.LENGTH_LONG).show();
        }

        queue.addtoQueue(GlobalRequest.patchRequest(
            GlobalRequest.POKEMON + "/" + pokemon.getId(),
            body, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    Toast.makeText(PokemonEdit.this, "Registro guardado", Toast.LENGTH_LONG).show();
                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(PokemonEdit.this, "Error al guardar", Toast.LENGTH_LONG).show();
                }
            }
        ));
    }



    public void delete(View view){
        queue.addtoQueue(GlobalRequest.deleteRequest(
                GlobalRequest.POKEMON + "/" + pokemon.getId(),
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(PokemonEdit.this, "Registro eliminado", Toast.LENGTH_LONG).show();
                        finish();
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PokemonEdit.this, "Error al eliminar", Toast.LENGTH_LONG).show();
                    }
                }
        ));
    }
}
