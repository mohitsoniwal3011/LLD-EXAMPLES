package solid;


/*
Don't force the clients to implement interfaces or the functions which they don't want
 */
interface MultiFunctionPrinterBeforeISP{
    void print();
    void scan();
    void fax();
}


// this is a basic printer , it cant scan or fax but it is forced to do it which is wrong
class BasicPrinter implements  MultiFunctionPrinterBeforeISP{

    @Override
    public void print() {

    }

    @Override
    public void scan() {

    }

    @Override
    public void fax() {

    }
}

//==================================================

interface Printable{
    void print();
}

interface Scanner{
    void scan();
}

interface Faxer {
    void fax();
}


class MultiFunctionPrinterAfterISP implements Printable, Scanner, Faxer{

    @Override
    public void fax() {

    }

    @Override
    public void print() {

    }

    @Override
    public void scan() {

    }
}

class BasicPrinterAfterISP  implements Printable{

    @Override
    public void print() {

    }

}

public class InterfaceSegragationPrinciple {
}
