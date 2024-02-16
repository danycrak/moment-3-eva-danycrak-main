package com.example.evam3.model

import jakarta.persistence.*


@Entity
@Table(name="characters")
class Characters {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var description: String? = null
    var cost: Double? = null
    var actor: String? = null
    var roles: String? = null
    @Column(name="scene_id")
    var sceneId: Long? = null



}
