package sg.edu.nus.iss.paf_day26_workshopB.model;

public class Song {
    
    private String trackName;
    private String artistName;


    public Song() {
    }

    public Song(String trackName, String artistName) {
        this.trackName = trackName;
        this.artistName = artistName;
    }


    public String getTrackName() {
        return trackName;
    }
    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    
    @Override
    public String toString() {
        return "Song [trackName=" + trackName + ", artistName=" + artistName + "]";
    }

}
