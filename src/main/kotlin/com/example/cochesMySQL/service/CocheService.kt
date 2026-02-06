package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Coche
import com.example.cochesMySQL.model.CocheNotas
import com.example.cochesMySQL.repository.CocheRepository
import com.example.cochesMySQL.repository.NotaRepository
import org.springframework.stereotype.Service

import java.io.File
import kotlin.collections.map
import kotlin.io.readLines
import kotlin.text.split


@Service
class CocheService(
    private val cocheRepository: CocheRepository,
    private val notaRepository: NotaRepository

) {

    fun listarCoches(): List<Coche> = cocheRepository.findAll()

    fun buscarPorId(id: Int): Coche? = cocheRepository.findById(id).orElse(null)

    fun guardar(coche: Coche): Coche = cocheRepository.save(coche)

    fun borrar(id: Int) {
        if (cocheRepository.existsById(id)) {
            cocheRepository.deleteById(id)
        }
    }

    fun listarCochesConNotas(): List<CocheNotas> {
        val coches = cocheRepository.findAll()

        return coches.map { coche ->
            val notas = notaRepository.findAllByCocheFk(coche.id_coche!!)
            CocheNotas(
                coche = coche,
                notas = notas
            )
        }
    }

    fun importarDesdeCSV() {

        // 1. Verificar si ya hay datos para no duplicar (opcional)
        //if (repository.count() > 0) return

        // 2. Leer el fichero (Lógica del Ejemplo 3)
        val filePath = "src/main/resources/data/coches.csv"
        val file = File(filePath)

        if (file.exists()) {
            println("el fichero existe")
            val cochesLeidos = file.readLines().map { linea ->
                val partes = linea.split(";")
                // 3. Crear el objeto Planta (Lógica del Ejemplo 4)
                // IMPORTANTE: Ponemos id_planta = null para que se genere uno nuevo
                Coche(
                    id_coche = null,
                    marca = partes[1],
                    modelo = partes[2],
                    foto = partes[3]
                )
            }
            // 4. Guardar todo en la BD de golpe
            cocheRepository.saveAll(cochesLeidos)
        }
    }
}