package com.example.cochesMySQL.model

import jakarta.persistence.*

@Entity
@Table(name = "notas")
data class Nota(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nota")
    var id_nota: Int? = null,

    @Column
    var coche_fk: Int? = null,

    @Column(nullable = false)
    var texto: String? = null
)