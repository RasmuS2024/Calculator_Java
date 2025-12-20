public class CreditAccount extends Account{
    private double creditLimit = 10000;

    public CreditAccount(String accountNumber, double balance, Customer owner) {
        super(accountNumber, balance, owner);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= super.getBalance() + creditLimit) {
            if (amount > super.getBalance()) {
                this.creditLimit -= amount - super.getBalance();
            }
            return super.withdraw(amount);
        }
        return false;
    }

    @Override
    public double getBalance() {
        return this.creditLimit + super.getBalance();
    }

}
