package com.example.evam3.model

import jakarta.persistence.*


@Entity
@Table(name="scene")
class Scene {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    val id: Long? = null
    var description: String? = null
    val budget: Double? = null
    var minutes: Long? = null
    val location: String? = null
    var effects: String? = null
    @Column(name="film_id")
    var filmId: Long? = null


}

