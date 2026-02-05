package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Concesionario
import com.example.cochesMySQL.model.ConcesionarioCoche
import com.example.cochesMySQL.model.ConcesionarioCocheId
import com.example.cochesMySQL.model.Coche
import com.example.cochesMySQL.model.PrecioCocheDTO
import com.example.cochesMySQL.model.ConcesionarioConCochesDTO
import com.example.cochesMySQL.repository.ConcesionarioCocheRepository
import com.example.cochesMySQL.repository.CocheRepository
import com.example.cochesMySQL.repository.ConcesionarioRepository
import org.springframework.stereotype.Service

@Service
class ConcesionarioCocheService(
    private val concesionarioCocheRepository: ConcesionarioCocheRepository,
    private val concesionarioRepository: ConcesionarioRepository,
    private val cocheRepository: CocheRepository
) {

    fun listarTodas(): List<ConcesionarioCoche> =
        concesionarioCocheRepository.findAll()

    fun listarConcesionarios(): List<Concesionario> = concesionarioRepository.findAll()
    fun listarCoches(): List<Coche> = cocheRepository.findAll()
    fun obtenerCochesDeConcesionario(idConcesionario: Int): List<ConcesionarioCoche> =
        concesionarioCocheRepository.obtenerCochesDeConcesionario(idConcesionario)

    fun guardar(idConcesionario: Int, idCoche: Int, precio: Int) {
        // 1. Buscamos las entidades (lanzará error si no existen)
        val concesionarioRef = concesionarioRepository.findById(idConcesionario).orElseThrow()
        val cocheRef = cocheRepository.findById(idCoche).orElseThrow()

        // 2. Creamos la clave compuesta
        val id = ConcesionarioCocheId(idConcesionario, idCoche)

        // 3. Creamos la entidad relación
        val nuevaRelacion = ConcesionarioCoche(
            id = id,
            concesionario = concesionarioRef,
            coche = cocheRef,
            precio = precio
        )

        concesionarioCocheRepository.save(nuevaRelacion)
    }
    fun obtenerConcesionariosConCoches(): List<ConcesionarioConCochesDTO> {

        val concesionarios = concesionarioRepository.findAll()

        return concesionarios.map { concesionario ->

            val relaciones =
                concesionarioCocheRepository.obtenerCochesDeConcesionario(
                    concesionario.id_concesionario
                )

            val PrecioCocheDTO = relaciones.mapNotNull { rel ->
                val coche = rel.coche ?: return@mapNotNull null

                PrecioCocheDTO(
                    Coche(
                        id_coche = coche.id_coche,
                        marca = coche.marca,
                        modelo = coche.modelo,
                        foto = coche.foto
                    ),
                    precio = rel.precio
                )
            }

            val marcas = PrecioCocheDTO
                .map { "https://gcronax.github.io/car-images/brands/"+it.coche.marca+".jpg" }
                .distinct()
                .sorted()

            ConcesionarioConCochesDTO(
                id = concesionario.id_concesionario,
                nombre = concesionario.nombre,
                coches = PrecioCocheDTO,
                marcas = marcas
            )
        }
    }



}
