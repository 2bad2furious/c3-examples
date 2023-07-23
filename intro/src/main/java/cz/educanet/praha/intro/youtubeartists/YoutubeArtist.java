package cz.educanet.praha.intro.youtubeartists;

public class YoutubeArtist {
    private String artist;
    private long totalViews;
    private int numberOfSongsAboveHundredMillionViews;
    private int averageDailyViews;


    public YoutubeArtist(String artist, long totalViews, int numberOfSongsAboveHundredMillionViews, int averageDailyViews) {
        this.artist = artist;
        this.totalViews = totalViews;
        this.numberOfSongsAboveHundredMillionViews = numberOfSongsAboveHundredMillionViews;
        this.averageDailyViews = averageDailyViews;
    }

    public String getArtist() {
        return artist;
    }

    public long getTotalViews() {
        return totalViews;
    }

    public int getNumberOfSongsAboveHundredMillionViews() {
        return numberOfSongsAboveHundredMillionViews;
    }

    public int getAverageDailyViews() {
        return averageDailyViews;
    }
}
