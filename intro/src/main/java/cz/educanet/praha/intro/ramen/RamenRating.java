package cz.educanet.praha.intro.ramen;

public class RamenRating {
    private int id;
    private String brand;
    private String variety;
    private String style;
    private String country;

    private double stars;

    public RamenRating(int id, String brand, String variety, String style, String country, double stars) {
        this.id = id;
        this.brand = brand;
        this.variety = variety;
        this.style = style;
        this.country = country;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getVariety() {
        return variety;
    }

    public String getStyle() {
        return style;
    }

    public String getCountry() {
        return country;
    }

    public double getStars() {
        return stars;
    }
}
