package com.example.crud.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.crud.PokemonEdit;
import com.example.crud.R;
import com.example.crud.model.Pokemon;

import java.util.List;

public class RVPokemonAdapter extends RecyclerView.Adapter<RVPokemonAdapter.RVPokemonAdapterViewHolder>{

    private Context context;
    private List<Pokemon> pokemons;


    public RVPokemonAdapter(Context context, List<Pokemon> pokemons){
        this.context = context;
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public RVPokemonAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RVPokemonAdapterViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_rv_pokemon, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RVPokemonAdapterViewHolder rvPokemonAdapterViewHolder, int i) {
        final Pokemon pokemon = pokemons.get(i);

        rvPokemonAdapterViewHolder.name.setText(pokemon.getName());
        rvPokemonAdapterViewHolder.type.setText(pokemon.getType());


        rvPokemonAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               Intent pokemonEdit = new Intent(context, PokemonEdit.class);
               pokemonEdit.putExtra("pokemon", pokemon);
               context.startActivity(pokemonEdit);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class RVPokemonAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView type;

        public RVPokemonAdapterViewHolder(View view){
            super(view);
            this.name = view.findViewById(R.id.rv_tv_name);
            this.type= view.findViewById(R.id.rv_tv_type);
        }
    }
}
