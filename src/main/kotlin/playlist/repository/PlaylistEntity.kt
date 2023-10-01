package com.wafflestudio.seminar.spring2023.playlist.repository

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity(name = "playlists")
//@Table(name = "playlists")는 안 되는 이유?

class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    val subtitle: String,
    val image: String,

    @OneToMany(mappedBy = "playlist")
    val playlist_like : List<PlaylistLikeEntity>,
    @OneToMany(mappedBy = "playlist")
    val playlist_song : List<PlaylistSongEntity>,

    @ManyToOne
    @JoinColumn(name = "group_id")
    val group:PlaylistGroupEntity,
}