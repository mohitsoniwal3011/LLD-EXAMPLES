
public class Encapculation_BankDetails {
    private long balance;

    public void deposite(long amount){
        if(amount > 0){
            balance += amount;
        }
    }

    public long getBalance(){
        return this.balance;
    }
}
