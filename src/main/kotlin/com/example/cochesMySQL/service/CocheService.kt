package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Coche
import com.example.cochesMySQL.repository.CocheRepository
import org.springframework.stereotype.Service

import java.io.File
import kotlin.collections.map
import kotlin.io.readLines
import kotlin.text.split
import kotlin.text.toDouble


@Service
class CocheService(private val repository: CocheRepository) {

    fun listarCoches(): List<Coche> = repository.findAll()

    fun buscarPorId(id: Int): Coche? = repository.findById(id).orElse(null)

    fun guardar(coche: Coche): Coche = repository.save(coche)

    fun borrar(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
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
            repository.saveAll(cochesLeidos)
        }
    }
}