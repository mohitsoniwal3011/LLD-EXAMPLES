

abstract class BasicPizza{
    public abstract int cost();
}

class ExtraCheeseDecorator extends BasicPizza{

    private final BasicPizza pizza;

    public ExtraCheeseDecorator(BasicPizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return this.pizza.cost() + 10;
    }
}

class MushroomDecorator extends BasicPizza{

    private final BasicPizza pizza;

    public MushroomDecorator(BasicPizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return this.pizza.cost() + 20;
    }
}

class CheeseBurst extends BasicPizza{

    private final BasicPizza pizza;

    public CheeseBurst(BasicPizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return this.pizza.cost() + 50;
    }
}

class Margarita extends BasicPizza{

    @Override
    public int cost() {
        return 100;
    }
}

class VegDelight extends BasicPizza{

    @Override
    public int cost() {
        return 120;
    }
}

public class PizzaDecoratorPattern {
    static BasicPizza margaritaWithMushroom = new MushroomDecorator(new Margarita());
    static BasicPizza vegDelightWithCheeseAndMushroom =
            new ExtraCheeseDecorator(
                    new MushroomDecorator(
                            new VegDelight()
                    )
            );



    public static void main(String[] args) {
        System.out.println( "margaritaWithMushroom: "+margaritaWithMushroom.cost());
        System.out.println( "vegDelightWithCheeseAndMushroom: "+vegDelightWithCheeseAndMushroom.cost());
    }
}
