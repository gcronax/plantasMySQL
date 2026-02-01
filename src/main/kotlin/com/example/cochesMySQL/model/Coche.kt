package com.example.cochesMySQL.model
import jakarta.persistence.*

@Entity
@Table(name = "coches")
data class Coche(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coche")
    var id_coche: Int? = null,

    @Column(nullable = false)
    var marca: String = "",

    @Column(nullable = false)
    var modelo: String = "",

    @Column
    var foto: String? = null
)