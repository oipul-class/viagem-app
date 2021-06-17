package br.senai.sp.jandira.viagens.api

import br.senai.sp.jandira.viagens.model.Comentario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ComentarioCall {

    @GET("comentarios/destinos/{id}")
    fun getDestinoComentarios(@Path("id")id: Long) : Call<List<Comentario>>
}