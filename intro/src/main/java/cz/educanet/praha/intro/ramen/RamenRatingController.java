package cz.educanet.praha.intro.ramen;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class RamenRatingController {
    @GetMapping("/ramen-ratings")
    public List<RamenRating> getAll(
            @RequestParam(value = "min-score", required = false) Double minScore,
            @RequestParam(value = "max-score", required = false) Double maxScore
    ) throws FileNotFoundException {
        ArrayList<RamenRating> result = new ArrayList<>();
        for (RamenRating ramenRating : loadAllFromFile()) {
            double stars = ramenRating.getStars();
            if ((minScore == null || minScore <= stars) && (maxScore == null || maxScore >= stars)) {
                result.add(ramenRating);
            }
        }
        return result;
    }

    @GetMapping("/ramen-ratings/{id}")
    public RamenRating getById(@PathVariable int id) throws FileNotFoundException {
        for (RamenRating ramenRating : loadAllFromFile()) {
            if (ramenRating.getId() == id) {
                return ramenRating;
            }
        }
        return null;
    }

    private List<RamenRating> loadAllFromFile() throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File("ramen-ratings.csv"))) {
            ArrayList<RamenRating> result = new ArrayList<>();
            sc.nextLine(); // skip header
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(";");
                result.add(new RamenRating(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        Double.parseDouble(parts[5])
                ));
            }
            return result;
        }
    }
}
