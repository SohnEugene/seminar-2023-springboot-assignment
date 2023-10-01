package com.wafflestudio.seminar.spring2023.playlist.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PlaylistGroupRepository : JpaRepository<PlaylistGroupEntity, Long> {
    @Query("SELECT g FROM playlist_groups g JOIN FETCH g.playlists p WHERE g.open = true")
    fun findRelevant(): List<PlaylistGroupEntity>
}