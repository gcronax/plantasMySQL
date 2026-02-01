package com.example.cochesMySQL.service

import com.example.cochesMySQL.model.Concesionario
import com.example.cochesMySQL.repository.ConcesionarioRepository
import org.springframework.stereotype.Service

import java.io.File
import kotlin.collections.map
import kotlin.io.readLines
import kotlin.text.split


@Service
class ConcesionarioService(private val repository: ConcesionarioRepository) {

    fun listarConcesionarios(): List<Concesionario> = repository.findAll()

    fun buscarPorId(id: Int): Concesionario? = repository.findById(id).orElse(null)

    fun guardar(concesionario: Concesionario): Concesionario = repository.save(concesionario)

    fun borrar(id: Int) {
        if (repository.existsById(id)) {
            repository.deleteById(id)
        }
    }


}