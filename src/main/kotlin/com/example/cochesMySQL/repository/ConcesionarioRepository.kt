package com.example.cochesMySQL.repository

import com.example.cochesMySQL.model.Concesionario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConcesionarioRepository : JpaRepository<Concesionario, Int>