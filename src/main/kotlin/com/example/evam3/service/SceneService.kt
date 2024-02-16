package com.example.evam3.service


import com.example.evam3.model.Scene
import com.example.evam3.repository.FilmRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class SceneService {
    @Autowired
    lateinit var filmRepository: FilmRepository

    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun list(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun save(scene: Scene): Scene {
        try {
            scene.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw IllegalArgumentException("La descripción de la escena no debe ser vacía")

            val film = filmRepository.findById(scene.filmId)
                ?: throw IllegalArgumentException("ID del film no encontrado")

            if (scene.budget!! > film.expence!!) {
                throw IllegalArgumentException("El costo de la escena excede el gasto total de la película")
            }

            val currentTotalCost = sceneRepository.sumMinutesByFilmId(scene.filmId!!) ?: 0

            if (currentTotalCost + (scene.minutes ?: 0) > (film.duration ?: 0)) {
                throw IllegalArgumentException("El total de minutos excede la duración de la película")
            }

            return sceneRepository.save(scene)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message)
        }
    }

    fun update(scene: Scene): Scene {
        try {
            sceneRepository.findById(scene.id)
                ?: throw IllegalArgumentException("ID no existe")

            return sceneRepository.save(scene)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun delete(id: Long?): Boolean? {
        try {
            val scene = sceneRepository.findById(id)
                ?: throw IllegalArgumentException("ID no existe")

            sceneRepository.delete(scene)
            return true
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
}
