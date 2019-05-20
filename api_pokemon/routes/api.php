<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group([
    'prefix' =>  'pokemon'
], function () {
    Route::get('{id}', 'PokemonController@get');
    Route::get('', 'PokemonController@get');
    Route::post('', 'PokemonController@create');
    Route::patch('{id}', 'PokemonController@update');
    Route::delete('{id}', 'PokemonController@delete');
});
