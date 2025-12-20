public class DebitAccount extends Account {
    public DebitAccount(String accountNumber, double balance, Customer owner) {
        super(accountNumber, balance, owner);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && getBalance() >= amount) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }
}
