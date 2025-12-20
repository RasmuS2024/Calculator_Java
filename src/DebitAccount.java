public class DebitAccount extends Account {
    public DebitAccount(String accountNumber, double balance, Customer owner) {
        super(accountNumber, balance, owner);
    }

    @Override
    public boolean withdraw(double amount) {
        if (getBalance() >= amount) {
            return super.withdraw(amount);
        } 
        return false;
    }
}
