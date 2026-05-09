import java.awt.datatransfer.FlavorListener;


// liskov Substitution principle
abstract class Bird{
    public abstract void eat();

}

interface Flyable {
    public void fly();
}


interface Swimmable {
    public void swim();
}

class Penguine extends Bird implements Swimmable{

    @Override
    public void eat() {

    }

    @Override
    public void swim() {

    }
}

class Sparrow extends Bird implements Flyable{

    @Override
    public void eat() {

    }

    @Override
    public void fly() {

    }
}


public class LiskovSubstitutionPrinciple {
}
