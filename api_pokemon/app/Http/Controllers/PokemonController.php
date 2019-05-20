<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\PokemonModel;

class PokemonController extends Controller {

    public function get(Request $request){        
        $result = null;
        if($request->id){
            $result = PokemonModel::find($request->id);
        }else{
            $result = PokemonModel::get();
        }
        return $result;
    }

    public function create(Request $request){
        $rules = [
            'name' => 'required',
            'type' => 'required'
        ];

        $data = $request->all();
        $errors = $this->validate($data, $rules);

        if(count($errors) > 0){
            return $this->error($errors);
        }

        $poke = PokemonModel::create($data);
        return $this->success($poke);
    }

    public function update(Request $request){
        $newData = $request->all();
        $data = PokemonModel::find($request->id);

        if(!$data){
            return $this->error(["Pokemon not found"]);            
        }

        $data->update($newData);

        return $this->success($data);
    }

    public function delete(Request $request){
        $data = PokemonModel::find($request->id);

        if(!$data){
            return $this->error(["Pokemon not found"]);            
        }

        return $this->success([$data->delete()]);

    }
}
