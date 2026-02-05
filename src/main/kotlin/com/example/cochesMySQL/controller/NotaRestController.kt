package com.example.cochesMySQL.controller

import com.example.cochesMySQL.model.Nota
import com.example.cochesMySQL.service.NotaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notes")
class NotaController(private val notaService: NotaService) {

    @GetMapping
    fun listarTodas(): List<Nota> = notaService.listarTodas()

    @GetMapping("/{id}")
    fun obtenerPorId(@PathVariable id: Int): ResponseEntity<Nota> {
        val nota = notaService.obtenerPorId(id)
        return if (nota != null) ResponseEntity.ok(nota)
        else ResponseEntity.notFound().build()
    }

    @GetMapping("/car/{id}")
    fun obtenerPorFk(@PathVariable id: Int):  List<Nota> = notaService.obtenerPorFk(id)


    @PostMapping
    fun crear(@RequestBody nota: Nota): Nota = notaService.guardar(nota)

    @PutMapping("/{id}")
    fun actualizar(@PathVariable id: Int, @RequestBody nota: Nota): ResponseEntity<Nota> {
        val existente = notaService.obtenerPorId(id)
        return if (existente != null) {
            val actualizada = notaService.guardar(nota.copy(id_nota = id))
            ResponseEntity.ok(actualizada)
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun borrar(@PathVariable id: Int): ResponseEntity<Void> {
        val existe = notaService.obtenerPorId(id) != null
        return if (existe) {
            notaService.borrar(id)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }
}