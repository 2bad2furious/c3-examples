package cz.educanet.praha.intro.youtubeartists;

import jdk.jfr.Frequency;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/youtube-artists")
public class YoutubeArtistController {

    @GetMapping
    public List<YoutubeArtist> getAll(
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) String search
    ) throws IOException {
        String searchLowerCase = search == null ? null : search.toLowerCase();
        return loadAllFromFile()
                .stream()
                .filter(artist -> searchLowerCase == null || artist.getArtist().toLowerCase().contains(searchLowerCase))
                .sorted(Comparator.comparing(artist -> artist.getArtist().toLowerCase()))
                .skip(pageSize == null || pageNumber == null ? 0 : pageSize * (pageNumber - 1L))
                .limit(pageSize == null ? Integer.MAX_VALUE : pageSize)
                .toList();
    }

    @GetMapping("/better-daily-avg-than/{name}")
    public List<YoutubeArtist> getAllBetterThan(
            @PathVariable String name
    ) throws IOException {
        return loadAllFromFile()
                .stream()
                .sorted(Comparator.<YoutubeArtist, Integer>comparing(artist -> artist.getAverageDailyViews()).reversed())
                .takeWhile(artist -> !artist.getArtist().equalsIgnoreCase(name))
                .toList();
    }

    @GetMapping("/sum-of-avg-of-artists-with-total-views-above/{totalViews}")
    public Long getSumOfDailyAverageLargerThan(
            @PathVariable long totalViews
    ) throws IOException {
        return loadAllFromFile()
                .stream()
                .filter(artist -> artist.getTotalViews() > totalViews)
                .map(artist -> (long) artist.getAverageDailyViews())
                .reduce(0L, (acc, dailyAvg) -> acc + dailyAvg);
    }

    private List<YoutubeArtist> loadAllFromFile() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("topyoutube.csv"))) {
            return lines
                    .skip(1)
                    .map(line -> {
                        String[] parts = line.split(";");
                        return new YoutubeArtist(
                                parts[0],
                                Math.round(Double.parseDouble(parts[1].replace(",", "")) * 1_000_000),
                                parts[2].isBlank() ? 0 : Integer.parseInt(parts[2]),
                                (int) Math.round(Double.parseDouble(parts[3]) * 1_000_000)
                        );
                    })
                    .toList();
        }
    }
}
