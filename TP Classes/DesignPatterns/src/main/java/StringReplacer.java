public class StringReplacer implements StringTransformer {

    private char victim;
    private char replacer;

    public StringReplacer(char victim, char replacer) {
        this.victim = victim;
        this.replacer = replacer;
    }

    @Override
    public void execute(StringDrink drink) {
        String text = drink.getText();
        String res = "";
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) == victim)
                res += replacer;
            else
                res += text.charAt(i);
        }
        drink.setText(res);
    }
}