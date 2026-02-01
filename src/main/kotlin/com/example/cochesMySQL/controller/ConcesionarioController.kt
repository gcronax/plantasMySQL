package com.example.cochesMySQL.controller

import com.example.cochesMySQL.model.Concesionario
import com.example.cochesMySQL.service.ConcesionarioService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class ConcesionarioController(private val concesionarioService: ConcesionarioService) {

    @GetMapping("/concesionarios")
    fun listar(model: Model): String {
        model.addAttribute("concesionarios", concesionarioService.listarConcesionarios())
        return "concesionarios"
    }

    @GetMapping("/concesionario/{id_concesionario}")
    fun detalle(@PathVariable id_concesionario: Int, model: Model): String {
        val concesionario = concesionarioService.buscarPorId(id_concesionario) ?: return "error"
        model.addAttribute("concesionario", concesionario)
        return "detalleConcesionario"
    }

    @GetMapping("/concesionarios/nuevo")
    fun nuevoConcesionario(model: Model): String {
        val concesionarioVacio = Concesionario(nombre = "", ubicacion = "")
        model.addAttribute("concesionario", concesionarioVacio)
        model.addAttribute("titulo", "Nuevo Concesionario")
        return "formularioConcesionario"
    }

    @GetMapping("/concesionarios/editar/{id_concesionario}")
    fun editarConcesionario(@PathVariable id_concesionario: Int, model: Model): String {
        val concesionario = concesionarioService.buscarPorId(id_concesionario) ?: return "redirect:/concesionarios"
        model.addAttribute("concesionario", concesionario)
        model.addAttribute("titulo", "Editar Concesionario")
        return "formularioConcesionario"
    }

    @PostMapping("/concesionarios/guardar")
    fun guardarConcesionario(@ModelAttribute concesionario: Concesionario): String {
        concesionarioService.guardar(concesionario)
        return "redirect:/concesionarios"
    }

    @GetMapping("/concesionarios/borrar/{id_concesionario}")
    fun borrarConcesionario(@PathVariable id_concesionario: Int): String {
        concesionarioService.borrar(id_concesionario)
        return "redirect:/concesionarios"
    }

}
