package com.example.cochesMySQL.controller.restControllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.example.cochesMySQL.model.Coche
import com.example.cochesMySQL.service.CocheService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.example.cochesMySQL.model.CocheNotas

@RestController
@RequestMapping("/cars")
class CocheRestController(private val cocheService: CocheService) {

    @GetMapping
    fun getCars(): List<Coche> {
        return cocheService.listarCoches()
    }

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Int): ResponseEntity<Coche> {
        val coche = cocheService.buscarPorId(id)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(coche)
    }

    @GetMapping("/notes")
    fun getCarsWithNotes(): ResponseEntity<List<CocheNotas>> {
        val resultado = cocheService.listarCochesConNotas()
        return ResponseEntity.ok(resultado)
    }

    @PostMapping
    fun createCar(@RequestBody coche: Coche): ResponseEntity<Coche> {
        val cocheGuardado = cocheService.guardar(coche)
        return ResponseEntity.status(HttpStatus.CREATED).body(cocheGuardado)
    }

    @PutMapping("/{id}")
    fun updateCar(
        @PathVariable id: Int,
        @RequestBody coche: Coche
    ): ResponseEntity<Coche> {

        val existente = cocheService.buscarPorId(id)
            ?: return ResponseEntity.notFound().build()

        val cocheActualizado = existente.copy(
            marca = coche.marca,
            modelo = coche.modelo,
            foto = coche.foto
        )

        return ResponseEntity.ok(cocheService.guardar(cocheActualizado))
    }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Int): ResponseEntity<Void> {
        cocheService.borrar(id)
        return ResponseEntity.noContent().build()
    }
}
