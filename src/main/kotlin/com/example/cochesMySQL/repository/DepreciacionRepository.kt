package com.example.cochesMySQL.repository

import com.example.cochesMySQL.model.Depreciacion
import com.example.cochesMySQL.model.Nota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DepreciacionRepository : JpaRepository<Depreciacion, Int>{
    fun findByCocheFk(cocheFk: Int): Depreciacion
}