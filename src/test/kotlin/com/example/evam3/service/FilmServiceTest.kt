package com.example.evam3.service

import com.example.evam3.model.Film
import com.example.evam3.repository.FilmRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import java.io.File


@SpringBootTest
internal class FilmServiceTest {

    @InjectMocks
    lateinit var filmService: FilmService // clase que se va a probar

    @Mock // objeto simulado
    lateinit var filmRepository: FilmRepository

    val jsonStringFilm = File("./src/test/resources/film.json").readText(Charsets.UTF_8)
   val filmMock = Gson().fromJson(jsonStringFilm, Film::class.java)

    @Test
    fun saveFilmCorrect() {
        Mockito.`when`(filmRepository.save(Mockito.any(Film::class.java))).thenReturn(filmMock)
        val response = filmService.save(filmMock)
        Assertions.assertEquals(response.id, filmMock.id)
    }

    @Test
    fun listFilmCorrect() {
        val filmList = listOf(filmMock)
        Mockito.`when`(filmRepository.findAll()).thenReturn(filmList)
        val response = filmService.list()
        Assertions.assertEquals(response.size, filmList.size)
        Assertions.assertEquals(response[0].id, filmList[0].id)
    }
}
