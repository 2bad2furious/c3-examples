package cz.educanet.praha.intro.spotify;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class SpotifyStatsController {

    @GetMapping("/artists/{id}")
    public Optional<Artist> getArtistByID(@PathVariable int id) throws IOException {
        return loadArtistsFromFile()
                .stream()
                .filter(artist -> artist.getId() == id)
                .findFirst();
    }

    @GetMapping("/artists")
    public List<Artist> getAllArtists(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Long leadStreamsMin,
            @RequestParam(required = false) Long leadStreamsMax
    ) throws IOException {
        String searchLowercase = search == null ? null : search.toLowerCase();
        return loadArtistsFromFile()
                .stream()
                .filter(artist -> searchLowercase == null || artist.getName().toLowerCase().contains(searchLowercase))
                .filter(artist -> leadStreamsMin == null || artist.getLeadStreams() >= leadStreamsMin)
                .filter(artist -> leadStreamsMax == null || artist.getLeadStreams() <= leadStreamsMax)
                .toList();
    }

    @GetMapping("/artists/best-avg-streams")
    public Optional<Artist> getArtistsForCountry() throws IOException {
        return loadArtistsFromFile()
                .stream()
                .reduce((best, artist) -> best.getAverageStreamsPerSong() > artist.getAverageStreamsPerSong() ? best : artist);
    }

    private List<Artist> loadArtistsFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("spotify_artist_data2.csv"))) {
            return lines
                    .skip(1)
                    .map(line -> {
                        String[] parts = line.split(";");
                        return new Artist(
                                Integer.parseInt(parts[0]),
                                parts[1],
                                Long.parseLong(parts[2].replace(",", "")),
                                Long.parseLong(parts[3].replace(",", "")),
                                Integer.parseInt(parts[4].replace(",", "")),
                                Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6])
                        );
                    }).toList();
        }
    }
}
