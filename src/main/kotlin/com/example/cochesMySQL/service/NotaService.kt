package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Nota
import com.example.cochesMySQL.repository.NotaRepository
import org.springframework.stereotype.Service

@Service
class NotaService(private val repository: NotaRepository) {

    fun listarTodas(): List<Nota> = repository.findAll()

    fun obtenerPorId(id: Int): Nota? = repository.findById(id).orElse(null)

    fun obtenerPorFk(cocheFk: Int): List<Nota> = repository.findAllByCocheFk(cocheFk)

    fun guardar(nota: Nota): Nota = repository.save(nota)

    fun borrar(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        }
    }
}