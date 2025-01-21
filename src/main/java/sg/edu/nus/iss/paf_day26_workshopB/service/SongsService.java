package sg.edu.nus.iss.paf_day26_workshopB.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.paf_day26_workshopB.model.Song;
import sg.edu.nus.iss.paf_day26_workshopB.repository.SongsRepository;

@Service
public class SongsService {
    
    @Autowired
    SongsRepository songsRepository;

    public List<Integer> getYears() {
        return songsRepository.getYears();
    }


    public List<Song> getSongsByYear(int year) {

        List<Document> songsRaw = songsRepository.findSongsByYear(year);

        List<Song> songs = new ArrayList<>();

        for (Document d : songsRaw) {
            
            String trackName = "";
            Object trackNameObj = d.get("track_name");

            if (trackNameObj instanceof Integer) {
                trackName = String.valueOf(trackNameObj);
            } else {
                trackName = (String) trackNameObj;
            }

            String artistName = d.getString("artist(s)_name");
            Song song = new Song(trackName, artistName);
            songs.add(song);
        }

        return songs;
    }
}
