public class StringCaseChanger implements StringTransformer {

    @Override
    public void execute(StringDrink drink) {
        String text = drink.getText();
        String res = "";
        for (int i = 0; i < text.length(); i++){
            if (Character.isLowerCase(text.charAt(i)))
                res += Character.toUpperCase(text.charAt(i));
            else
                res += Character.toLowerCase(text.charAt(i));
        }
        drink.setText(res);
    }
}
