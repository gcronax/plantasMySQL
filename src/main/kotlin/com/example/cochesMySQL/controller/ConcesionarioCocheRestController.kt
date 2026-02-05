package com.example.cochesMySQL.controller

import com.example.cochesMySQL.model.ConcesionarioConCochesDTO
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
    fun getConcesionariosConCoches(): List<ConcesionarioConCochesDTO> {
        return concesionarioCocheService.obtenerConcesionariosConCoches()
    }
}