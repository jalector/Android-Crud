<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class PokemonModel extends Model {
    protected $table = "pokemon";
    protected $fillable = ['name', 'type'];
}
