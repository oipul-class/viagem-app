package br.senai.sp.jandira.viagens.model

data class Comentario(
    var idComentario: Long = 0,
    var comentario: String = "",
    var nota: Double = 0.0,
    var dataComentario: String = ""
)