package com.example.cochesMySQL.controller

import com.example.cochesMySQL.model.Coche
import com.example.cochesMySQL.service.CocheService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class CocheController(private val cocheService: CocheService) {

    @GetMapping("/coches")
    fun listar(model: Model): String {
        model.addAttribute("coches", cocheService.listarCoches())
        return "coches"
    }

    @GetMapping("/coche/{id_coche}")
    fun detalle(@PathVariable id_coche: Int, model: Model): String {
        val coche = cocheService.buscarPorId(id_coche) ?: return "error"
        model.addAttribute("coche", coche)
        return "detalleCoche"
    }

    @GetMapping("/coches/nuevo")
    fun nuevoCoche(model: Model): String {
        val cocheVacio = Coche(marca = "", modelo = "", foto = "")
        model.addAttribute("coche", cocheVacio)
        model.addAttribute("titulo", "Nuevo Coche")
        return "formularioCoche"
    }

    @GetMapping("/coches/editar/{id_coche}")
    fun editarCoche(@PathVariable id_coche: Int, model: Model): String {
        val coche = cocheService.buscarPorId(id_coche) ?: return "redirect:/coches"
        model.addAttribute("coche", coche)
        model.addAttribute("titulo", "Editar Coche")
        return "formularioCoche"
    }

    @PostMapping("/coches/guardar")
    fun guardarCoche(@ModelAttribute coche: Coche): String {
        cocheService.guardar(coche)
        return "redirect:/coches"
    }

    @GetMapping("/coches/borrar/{id_coche}")
    fun borrarCoche(@PathVariable id_coche: Int): String {
        cocheService.borrar(id_coche)
        return "redirect:/coches"
    }

    @GetMapping("/")
    fun inicio(): String {
        return "index"
    }

    @GetMapping("/importar")
    fun importarDatos(): String {
        cocheService.importarDesdeCSV()
        return "redirect:/coches"
    }
}
