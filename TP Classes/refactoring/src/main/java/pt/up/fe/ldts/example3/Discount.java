package pt.up.fe.ldts.example3;

public abstract class Discount {
    private final double discount;

    public Discount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

    public abstract double applyDiscount(double price);
}
