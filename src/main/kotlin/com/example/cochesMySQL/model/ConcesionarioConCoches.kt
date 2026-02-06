package com.example.cochesMySQL.model

data class ConcesionarioConCoches(
    val id: Int?,
    val nombre: String,
    val coches: List<PrecioCoche>,
    val marcas: List<String>
)
