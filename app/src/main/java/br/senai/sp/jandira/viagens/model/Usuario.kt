package br.senai.sp.jandira.viagens.model

data class Usuario (
    var id: Long = 0,
    var displayName: String = "",
    var email: String = "",
    var photoUrl: String = ""
)