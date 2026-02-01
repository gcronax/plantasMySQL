package com.example.cochesMySQL.repository

import com.example.cochesMySQL.model.Coche
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CocheRepository : JpaRepository<Coche, Int>