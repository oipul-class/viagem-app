package br.senai.sp.jandira.viagens.api

import br.senai.sp.jandira.viagens.api.UrlApi.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApi {

    companion object {
        // uma função que cria e retorna uma api pra ser usada
        fun getRetrofit() : Retrofit {,
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }


    }

}