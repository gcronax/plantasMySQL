package com.example.cochesMySQL.model

import jakarta.persistence.*

@Entity
@Table(name = "depreciaciones")
data class Depreciacion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depreciacion")
    var id_depreciacion: Int? = null,

    @Column(name = "cocheFk")
    var cocheFk: Int? = null,

    @Column(name = "valor")
    var valores: List<Int> = emptyList()
)