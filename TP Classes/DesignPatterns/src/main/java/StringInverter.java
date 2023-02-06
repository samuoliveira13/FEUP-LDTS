public class StringInverter implements StringTransformer{
    public void execute(StringDrink drink) {
        StringBuilder str = new StringBuilder(drink.getText());
        str.reverse();
        drink.setText(str.toString());

    }
}
