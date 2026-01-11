package com.example.screen_music.main;

import com.example.screen_music.model.Artist;
import com.example.screen_music.model.ArtistType;
import com.example.screen_music.model.Music;
import com.example.screen_music.repository.ArtistRepository;
import com.example.screen_music.service.UseChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ScreenMusicApp {
    private Scanner in = new Scanner(System.in);
    private ArtistRepository repository;
    private UseChatGPT chatGPTService;

    public ScreenMusicApp(ArtistRepository repository, UseChatGPT chatGPTService) {
        this.repository = repository;
        this.chatGPTService = chatGPTService;
    }

    public void displayMenu() {
        int option;

        do{
            var menu = """
                    -----------------------------------
                    ********** Screen Music **********
                    -----------------------------------
                    Manage your Fav Artists and Musics
                    -----------------------------------
                    1 - Add new artist
                    2 - Add music
                    3 - List musics
                    4 - Search music by artist
                    5 - Search info about an artist
                    -----------------------------------
                    0 - Exit
                    -----------------------------------
                    """;

            System.out.println(menu);
            System.out.println("Enter one of the options above");
            option = in.nextInt();
            in.nextLine();

            switch (option) {
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                case 1:
                    addNewArtist();
                    break;
                    case 2:
                        addMusic();
                        break;
                        case 3:
                            listMusics();
                            break;
                            case 4:
                                searchMusic();
                                break;
                                case 5:
                                    displayInfo();
                                    break;
                                    default:
                                        System.out.println("Invalid option");

            }
        }while(option != 0);

    }

    private void displayInfo() {
        System.out.println("Enter an artist name to get info");
        var text = in.nextLine();

        String response = chatGPTService.getInfo(text);
        System.out.println(response.trim());
    }

    private void searchMusic() {
        System.out.println("Enter the artist for which you wanna see the fav musics");
        var artist = in.nextLine();
        List<Music> musics = repository.findMusicByArtist(artist);
        musics.forEach(System.out::println);
    }

    private void listMusics() {
        List<Artist> artists = repository.findAll();
        artists.forEach(a -> a.getMusics()
                .forEach(System.out::println));
    }

    private void addMusic() {
        System.out.println("From which artist do you want to add a music?");
        List<String> artists = repository.findAll()
                .stream()
                .map(a -> a.getName())
                .toList();

        artists.forEach(System.out::println);
        var artistName = in.nextLine();
        Optional<Artist> artist = repository.findByNameContainingIgnoreCase(artistName);
        if(artist.isPresent()) {
            System.out.println("Enter the music title");
            var title = in.nextLine();

            Music music = new Music(title);
            music.setArtist(artist.get());
            artist.get().getMusics().add(music);

            repository.save(artist.get());
        }else{
            System.out.println("Invalid artist name");
        }
    }

    private void addNewArtist() {
        var addNewArtist = "";

        do{
            System.out.println("Enter the artist name");
            var name = in.nextLine();
            System.out.println("Inform the type SOLO, DUO or BAND");
            var type = in.nextLine();
            ArtistType artistType = ArtistType.valueOf(type.toUpperCase());

            Artist artist = new Artist(name, artistType);
            repository.save(artist);

            System.out.println("Do you want to add another artist?");
            addNewArtist = in.nextLine();

        }while(!addNewArtist.substring(0,1).equalsIgnoreCase("n"));

    }
}
