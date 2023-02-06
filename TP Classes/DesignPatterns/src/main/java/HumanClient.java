public class HumanClient implements Client {

    private OrderingStrategy strategy;

    public HumanClient(OrderingStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void happyHourStarted(Bar bar) {
        bar.startHappyHour();
    }

    @Override
    public void happyHourEnded(Bar bar) {
        bar.endHappyHour();
    }

    @Override
    public void wants(StringDrink drink, StringRecipe recipe, StringBar bar) {
        strategy.wants(drink, recipe, bar);
    }
}
