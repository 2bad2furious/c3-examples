package cz.educanet.praha.intro.lesson1;

public class Results {
    private final int sum;
    private final int difference;
    private final int product;
    private final double quotient;

    public int getSum() {
        return sum;
    }

    public int getDifference() {
        return difference;
    }

    public int getProduct() {
        return product;
    }

    public double getQuotient() {
        return quotient;
    }

    public Results(int sum, int difference, int product, double quotient) {
        this.sum = sum;
        this.difference = difference;
        this.product = product;
        this.quotient = quotient;
    }
}
