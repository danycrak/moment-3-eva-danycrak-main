package com.example.evam3.service

import com.example.evam3.model.Characters
import com.example.evam3.repository.CharactersRepository
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class CharactersService {

    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var charactersRepository: CharactersRepository

    fun list ():List<Characters>{
        return charactersRepository.findAll()
    }

    fun save (characters: Characters): Characters {
        try{
            sceneRepository.findById(characters.sceneId)
                ?: throw Exception("Id escena no se encuentra")


            characters.actor?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception(" no debe ser vacio")

            characters.description?.takeIf { it.trim().isNotEmpty() }
                ?: throw Exception(" no debe ser vacio")


            //  costo de los personajes en la escena calculo
            val scene = sceneRepository.findById(characters.sceneId)
            val currentTotalCost = charactersRepository.sumCostBySceneId(characters.sceneId!!) ?: 0.0



            // Verificar si el nuevo personaje excede el presupuesto
            if (scene != null) {
                if (currentTotalCost + (characters.cost ?: 0.0) > (scene.budget ?: 0.0)) {
                    throw Exception("El costo excede el presupuesto ")
                }
            }

            return charactersRepository.save(characters)
        }
        catch (ex: Exception){
            throw ResponseStatusException(HttpStatus.BAD_REQUEST,ex.message)
        }

    }
    fun update(characters: Characters): Characters {
        try {

            charactersRepository.findById(characters.id)
                ?: throw Exception("ID no existe")

            return charactersRepository.save(characters)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

        fun delete (id: Long?):Boolean?{
            try{
                val response = charactersRepository.findById(id)
                    ?: throw Exception("ID no existe")
                charactersRepository.deleteById(id!!)
                return true
            }
            catch (ex:Exception){
                throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
            }
        }


}