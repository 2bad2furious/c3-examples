package cz.educanet.praha.intro.spotify;

public class Artist {
    private int id;
    private String name;

    private Long leadStreams;
    private Long featureStreams;
    private int tracks;
    private int passedOneBillionStreamsCount;
    private int passedHundredMillionStreamsCount;

    public Artist(int id, String name, Long leadStreams, Long featureStreams, int tracks, int passedOneBillionStreamsCount, int passedHundredMillionStreamsCount) {
        this.id = id;
        this.name = name;
        this.leadStreams = leadStreams;
        this.featureStreams = featureStreams;
        this.tracks = tracks;
        this.passedOneBillionStreamsCount = passedOneBillionStreamsCount;
        this.passedHundredMillionStreamsCount = passedHundredMillionStreamsCount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getLeadStreams() {
        return leadStreams;
    }

    public Double getAverageStreamsPerSong(){
        return (double) leadStreams / tracks;
    }

    public Long getFeatureStreams() {
        return featureStreams;
    }

    public int getTracks() {
        return tracks;
    }

    public int getPassedOneBillionStreamsCount() {
        return passedOneBillionStreamsCount;
    }

    public int getPassedHundredMillionStreamsCount() {
        return passedHundredMillionStreamsCount;
    }
}
