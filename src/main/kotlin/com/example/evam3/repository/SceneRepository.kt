package com.example.evam3.repository

import com.example.evam3.model.Scene
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface SceneRepository:JpaRepository<Scene, Long> {


    fun findById(id: Long?): Scene?

    //
    @Query("SELECT COALESCE(SUM(s.minutes), 0) FROM Scene s WHERE s.filmId = :filmId")
    fun sumMinutesByFilmId(@Param("filmId") filmId: Long): Long?

    // forma de obtener la suma de los minutos de todas las escenas de una película específica de una base de datos.





}