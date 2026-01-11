package com.example.screen_music.repository;

import com.example.screen_music.model.Artist;
import com.example.screen_music.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByNameContainingIgnoreCase(String artistName);

    @Query("SELECT m FROM Artist a JOIN a.musics m WHERE a.name ILIKE %:name%")
    List<Music> findMusicByArtist(String name);
}
