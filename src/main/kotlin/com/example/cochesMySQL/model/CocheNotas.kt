package com.example.cochesMySQL.model

data class CocheNotas (
    val coche: Coche,
    val notas: List<Nota> = emptyList()
)