package com.example.cochesMySQL.model
import jakarta.persistence.*

@Entity
@Table(name = "concesionario")
data class Concesionario(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_concesionario")
    var id_concesionario: Int? = null,

    @Column(nullable = false)
    var nombre: String = "",

    @Column(nullable = false)
    var ubicacion: String = ""
)