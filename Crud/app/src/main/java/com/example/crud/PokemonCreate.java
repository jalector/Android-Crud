package com.example.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.crud.service.GlobalRequest;
import com.example.crud.service.Queue;

import org.json.JSONObject;

public class PokemonCreate extends AppCompatActivity {

    Queue queue;
    EditText name;
    EditText type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_create);
        queue = Queue.getInstance(this);

        name = this.findViewById(R.id.pokemon_create_name);
        type = this.findViewById(R.id.pokemon_create_type);
    }

    public  void createIfValid(View view){
        if(!name.getText().toString().isEmpty() && !type.getText().toString().isEmpty()){
            create();
        }else{
            Toast.makeText(PokemonCreate.this, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show();
        }
    }

    public void create(){
        JSONObject body = new JSONObject();
        try{
            body.put("name", name.getText().toString());
            body.put("type", type.getText().toString());

        }catch(Exception e){
            Toast.makeText(PokemonCreate.this, "Error al guardar la información", Toast.LENGTH_LONG).show();
        }

        queue.addtoQueue(GlobalRequest.postRequest(
                GlobalRequest.POKEMON ,
                body, new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Toast.makeText(PokemonCreate.this, "Registro guardado", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PokemonCreate.this, "Error al guardar", Toast.LENGTH_LONG).show();
                    }
                }
        ));
    }
}
