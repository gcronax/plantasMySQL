package com.example.cochesMySQL.controller.restControllers

import com.example.cochesMySQL.model.ConcesionarioConCoches
import com.example.cochesMySQL.service.ConcesionarioCocheService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/dealers")
class ConcesionarioCocheRestController(
    private val concesionarioCocheService: ConcesionarioCocheService
) {

    @GetMapping("/cars")
    fun getConcesionariosConCoches(): List<ConcesionarioConCoches> {
        return concesionarioCocheService.obtenerConcesionariosConCoches()
    }
}