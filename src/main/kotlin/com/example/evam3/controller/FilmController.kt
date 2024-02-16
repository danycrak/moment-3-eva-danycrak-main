package com.example.evam3.controller

import com.example.evam3.model.Film
import com.example.evam3.service.FilmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/film")

@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])


class FilmController {
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun list (): ResponseEntity<*> {
        return ResponseEntity(filmService.list(), HttpStatus.OK)
    }



    @PostMapping
    fun save (@RequestBody film: Film): ResponseEntity<*> {
        return ResponseEntity<Film>(filmService.save(film), HttpStatus.CREATED)
    }

    @PutMapping
    fun update(@RequestBody film: Film): ResponseEntity<Film> {
        return ResponseEntity(filmService.update(film), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): Boolean? {

        return filmService. delete(id)
    }


}