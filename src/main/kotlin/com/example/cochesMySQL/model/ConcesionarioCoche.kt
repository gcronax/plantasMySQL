package com.example.cochesMySQL.model
import jakarta.persistence.*

@Embeddable
class ConcesionarioCocheId(
    var id_concesionario: Int = 0,
    var id_coche: Int = 0
)

@Entity
@Table(name = "concesionario_coches")
class ConcesionarioCoche(

    @EmbeddedId
    var id: ConcesionarioCocheId = ConcesionarioCocheId(),

    @ManyToOne
    @MapsId("idJardin")
    @JoinColumn(name = "id_jardin")
    var coche: Coche? = null,

    @ManyToOne
    @MapsId("idPlanta")
    @JoinColumn(name = "id_planta")
    var concesionario: Concesionario? = null,

    @Column(nullable = false)
    var cantidad: Int = 0
)