package com.example.cochesMySQL.controller

import com.example.cochesMySQL.model.Depreciacion
import com.example.cochesMySQL.repository.DepreciacionRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/depreciaciones")
class DepreciacionController(
    private val depreciacionRepository: DepreciacionRepository
) {

    @GetMapping
    fun listarTodas(): List<Depreciacion> {
        return depreciacionRepository.findAll()
    }

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): Depreciacion? {
        return depreciacionRepository.findById(id).orElse(null)
    }
}