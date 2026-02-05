package com.example.cochesMySQL.model

data class ConcesionarioConCochesDTO(
    val id: Int?,
    val nombre: String,
    val coches: List<PrecioCocheDTO>,
    val marcas: List<String>
)
