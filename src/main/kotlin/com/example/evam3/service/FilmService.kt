package com.example.evam3.service

import com.example.evam3.model.Film
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Service
class FilmService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    fun list ():List<Film>{
        return filmRepository.findAll()
    }

    fun save (film:Film): Film{
        try{
            film.title?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception("Film no debe ser vacio")
            return filmRepository.save(film)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }
    fun update(film: Film): Film {
        try {
            filmRepository.findById(film.id)
                ?: throw Exception("ID no existe")

            return filmRepository.save(film)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    //
    fun delete (id: Long?):Boolean?{
        try{
            val response = filmRepository.findById(id)
                ?: throw Exception("ID no existe")
            filmRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
}