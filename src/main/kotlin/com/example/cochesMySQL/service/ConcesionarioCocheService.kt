package com.example.cochesMySQL.service


import com.example.cochesMySQL.repository.ConcesionarioCocheRepository
import org.springframework.stereotype.Service

@Service
class JardinPlantaService(
    private val ConcesionarioCocheRepository: ConcesionarioCocheRepository,
    private val jardinRepository: JardinRepository,
    private val plantaRepository: PlantaRepository
) {

    fun listarTodas(): List<JardinPlanta> =
        jardinPlantaRepository.findAll()

    // Métodos para llenar los desplegables
    fun listarJardines(): List<Jardin> = jardinRepository.findAll()
    fun listarPlantas(): List<Planta> = plantaRepository.findAll()

    // Método para guardar
    fun guardar(idJardin: Int, idPlanta: Int, cantidad: Int) {
        // 1. Buscamos las entidades (lanzará error si no existen, lo cual es bueno para integridad)
        val jardinRef = jardinRepository.findById(idJardin).orElseThrow()
        val plantaRef = plantaRepository.findById(idPlanta).orElseThrow()

        // 2. Creamos la clave compuesta
        val id = JardinPlantaId(idJardin, idPlanta)

        // 3. Creamos la entidad relación
        val nuevaRelacion = JardinPlanta(
            id = id,
            jardin = jardinRef,
            planta = plantaRef,
            cantidad = cantidad
        )

        jardinPlantaRepository.save(nuevaRelacion)
    }
}