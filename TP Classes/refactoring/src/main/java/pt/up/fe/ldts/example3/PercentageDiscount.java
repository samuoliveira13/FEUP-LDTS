package pt.up.fe.ldts.example3;

public class PercentageDiscount extends Discount {
    public PercentageDiscount(double discount) {
        super(discount);
    }

    @Override
    public double applyDiscount(double price) {
        return price - price * getDiscount();
    }
}
