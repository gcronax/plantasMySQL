package com.example.plantasMySQL.controller

import com.example.plantasMySQL.model.Planta
import com.example.plantasMySQL.service.PlantaService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/plantas")
class PlantaRestController(private val plantaService: PlantaService) {

    @GetMapping
    fun listar(): List<Planta> {
        return plantaService.listarPlantas()
    }

    @GetMapping("/{id}")
    fun detalle(@PathVariable id: Int): Planta {
        return plantaService.buscarPorId(id)
            ?: throw RuntimeException("Planta no encontrada")
    }

    @PostMapping
    fun crear(@RequestBody planta: Planta): Planta {
        return plantaService.guardar(planta)
    }

    @PutMapping("/{id}")
    fun actualizar(
        @PathVariable id: Int,
        @RequestBody planta: Planta
    ): Planta {
        planta.id_planta = id
        return plantaService.guardar(planta)
    }

    @DeleteMapping("/{id}")
    fun borrar(@PathVariable id: Int) {
        plantaService.borrar(id)
    }
}
