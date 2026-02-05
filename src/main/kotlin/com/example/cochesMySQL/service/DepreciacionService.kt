package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Depreciacion
import com.example.cochesMySQL.repository.DepreciacionRepository
import org.springframework.stereotype.Service

@Service
class DepreciacionService(
    private val repository: DepreciacionRepository
) {

    fun listarTodas(): List<Depreciacion> = repository.findAll()

    fun obtenerPorId(id: Int): Depreciacion? = repository.findById(id).orElse(null)

    fun guardar(depreciacion: Depreciacion): Depreciacion = repository.save(depreciacion)

    fun borrar(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        }
    }
}