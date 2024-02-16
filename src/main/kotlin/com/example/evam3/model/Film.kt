package com.example.evam3.model

import jakarta.persistence.*

@Entity
@Table (name="film")
class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var director: String? = null
    var duration: Long? = null
    var genre: String? = null
    var description: String? = null
    var expence: Double? = null

}

