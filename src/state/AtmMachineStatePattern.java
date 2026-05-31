package state;


interface MachineState{
   void insert(AtmStateManager manager);

   void eject(AtmStateManager manager);

   void verifyPin(AtmStateManager manager, int pin);

   void withdraw(AtmStateManager manager, int amount);
}

class CardInsertedState implements MachineState{

    @Override
    public void insert(AtmStateManager manager) {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void eject(AtmStateManager manager) {
        System.out.println("Card Ejected");
        manager.getCurrentUser().setState(AtmStateManager.NO_CARD_STATE);
    }

    @Override
    public void verifyPin(AtmStateManager manager, int pin) {
        System.out.println("Verifying Pin...");
        if(pin == manager.getCurrentUser().getPin()){
            System.out.println("Pin Verified Successfully");
            manager.getCurrentUser().setState(AtmStateManager.AUTHENTICATED_STATE);
        }else{
            System.out.println("Invalid Pin");
        }
    }

    @Override
    public void withdraw(AtmStateManager manager, int amount) {
        System.out.println("Cant withdraw money, please verify pin first");
        //System.out.printf("Amount %d withdrawn from your account successfully\n", amount);
    }
}

class NoCardState implements MachineState{

    @Override
    public void insert(AtmStateManager manager) {
        System.out.println("Card inserted successfully");
        manager.getCurrentUser().setState(AtmStateManager.CARD_INSERTED_STATE);
    }

    @Override
    public void eject(AtmStateManager manager) {
        System.out.println("No Card to Eject");
    }

    @Override
    public void verifyPin(AtmStateManager manager, int pin) {
        System.out.println("Please insert a card first");
    }

    @Override
    public void withdraw(AtmStateManager manager, int amount) {
        System.out.println("Please insert a card first");
    }
}


class AuthenticatedState implements  MachineState {

    @Override
    public void insert(AtmStateManager manager) {
        System.out.println("Card is already inserted.");
    }

    @Override
    public void eject( AtmStateManager manager) {
        System.out.println("Card Ejected");
        manager.getCurrentUser().setState(AtmStateManager.NO_CARD_STATE);
    }

    @Override
    public void verifyPin(AtmStateManager manager, int pin) {
        System.out.println("Pin is already verified.");
    }

    @Override
    public void withdraw(AtmStateManager manager, int amount) {
        if(amount > manager.getAvailableCash()){
            System.out.println("This ATM does not have sufficient cash for this txn.");
            manager.getCurrentUser().setState(AtmStateManager.NO_CARD_STATE);
            return;
        }

        if(amount <= manager.getCurrentUser().getBalance()){
            manager.getCurrentUser().setBalance(manager.getCurrentUser().getBalance() - amount);
            manager.setAvailableCash(manager.getAvailableCash()- amount);
            System.out.printf("Amount %d withdrawn from your account successfully\n", amount);
        }else{
            System.out.print("Insufficient funds\n");
        }
        manager.getCurrentUser().setState(AtmStateManager.CARD_INSERTED_STATE);
    }
}

// we wont store like this in record, like balance and correct pin
// but this is just for learning and demo
class AtmUser {
    private int balance;

    public AtmUser(int balance, String name, int pin) {
        this.balance = balance;
        this.name = name;
        this.pin = pin;
        this.state = AtmStateManager.NO_CARD_STATE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public int getPin() {
        return pin;
    }

    private String name;
    private MachineState state;
    private int pin;
}

class AtmStateManager {

    private int availableCash;
    public static final MachineState AUTHENTICATED_STATE = new AuthenticatedState();
    public static final MachineState CARD_INSERTED_STATE = new CardInsertedState();
    public static final MachineState NO_CARD_STATE = new NoCardState();

    AtmStateManager(int availableCash) {
        this.availableCash = availableCash;
    }

    public int getAvailableCash() {
        return availableCash;
    }

    public void setAvailableCash(int availableCash) {
        this.availableCash = availableCash;
    }

    public void setCurrentUser(AtmUser currentUser) {
        this.currentUser = currentUser;
    }

    public AtmUser getCurrentUser() {
        return this.currentUser;
    }

    private  AtmUser currentUser ;


    void insert(){

        currentUser.getState().insert(this);
    }

    void eject(){

        currentUser.getState().eject(this);
    }

    void verifyPin(int pin){

        currentUser.getState().verifyPin(this, pin);
    }

    void withdraw(int amount){

        currentUser.getState().withdraw(this, amount);
    }

    public void setState(MachineState state) {

        currentUser.setState(state);
    }
}


public class AtmMachineStatePattern {

    public static void main(String[] args) {
        AtmStateManager manager = new AtmStateManager(100000);

        AtmUser user = new AtmUser(10000, "Mohit", 3434);
        manager.setCurrentUser(user);

        manager.withdraw(1000);

        manager.eject();

        manager.insert();

        manager.withdraw(1089);

        manager.verifyPin(2341);

        manager.withdraw(3452);

        manager.verifyPin(1234);

        manager.withdraw(21210);

        manager.withdraw(2311);

        manager.eject();

    }
}
