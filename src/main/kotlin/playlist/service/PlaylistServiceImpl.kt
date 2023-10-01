package com.wafflestudio.seminar.spring2023.playlist.service

import com.wafflestudio.seminar.spring2023.playlist.repository.PlaylistGroupRepository
import com.wafflestudio.seminar.spring2023.playlist.repository.PlaylistRepository
import com.wafflestudio.seminar.spring2023.song.service.Artist
import com.wafflestudio.seminar.spring2023.song.service.Song
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Primary
@Service
class PlaylistServiceImpl(
    private val playlistRepository: PlaylistRepository,
    private val playlistGroupRepository: PlaylistGroupRepository
) : PlaylistService {

    override fun getGroups(): List<PlaylistGroup> {
        val groups = playlistGroupRepository.findRelevant()
        return groups.map {
            g -> PlaylistGroup(g.id, g.title, g.playlists.map {
                p -> PlaylistBrief(p.id, p.title, p.subtitle, p.image)
            })
        }
    }

    override fun get(id: Long): Playlist {
        val p= playlistRepository.findById(id)
        if (p.isEmpty) {
            throw PlaylistNotFoundException()
        } else {
            val pp = p.get()
            val generatedPlaylist = Playlist(pp.id, pp.title, pp.subtitle, pp.image, pp.playlist_song.map {
                s -> Song(s.song.id, s.song.title, s.song.song_artists.map {
                    sa-> Artist(sa.artist.id, sa.artist.name)
                }, s.song.album.title, s.song.album.image, s.song.duration.toString())
            })
            return generatedPlaylist
        }
    }
}

