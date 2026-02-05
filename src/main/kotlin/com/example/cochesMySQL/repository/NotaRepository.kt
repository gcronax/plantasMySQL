package com.example.cochesMySQL.repository

import com.example.cochesMySQL.model.Nota
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface NotaRepository : JpaRepository<Nota, Int> {
    fun findAllByCocheFk(cocheFk: Int): List<Nota>
}