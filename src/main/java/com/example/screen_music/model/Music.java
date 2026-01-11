package com.example.screen_music.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "musics")
@Getter
@Setter
@NoArgsConstructor
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    private Artist artist;

    public Music(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", artist='" + artist.getName() + "\'";
    }
}
