package com.example.evam3.repository

import com.example.evam3.model.Characters
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CharactersRepository: JpaRepository<Characters, Long> {


    fun findById(id: Long?): Characters?



    @Query("SELECT COALESCE(SUM(c.cost), 0.0) FROM Characters c WHERE c.sceneId = :sceneId")
    fun sumCostBySceneId(@Param("sceneId") sceneId: Long): Double?

    //forma de obtener la suma de los costos de los personajes en una escena específica de una base de datos.

}