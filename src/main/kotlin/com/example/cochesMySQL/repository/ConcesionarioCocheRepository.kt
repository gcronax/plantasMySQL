package com.example.cochesMySQL.repository

import com.example.cochesMySQL.model.ConcesionarioCoche
import com.example.cochesMySQL.model.ConcesionarioCocheId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface JardinPlantaRepository :
    JpaRepository<ConcesionarioCoche, ConcesionarioCocheId> {}