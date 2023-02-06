package pt.up.fe.ldts.example3;

public class NoDiscount extends Discount {

    public NoDiscount() {
        super(0);
    }

    @Override
    public double applyDiscount(double price) {
        return price;
    }
}
