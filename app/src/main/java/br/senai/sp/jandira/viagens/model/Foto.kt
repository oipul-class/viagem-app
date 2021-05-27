package br.senai.sp.jandira.viagens.model

import com.google.gson.annotations.SerializedName

data class Foto (
    var id : Long = 0,
    @SerializedName("url") var urlFoto : String = "", // apenas usar @SerializedName(nome do campo da requesição) quando quebrar o padrão camelCase
    var destaque : Boolean = false
)