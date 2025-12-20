public class CreditAccount extends Account{
    private double creditLimit;

    public CreditAccount(String accountNumber, double balance, double creditLimit, Customer owner) {
        super(accountNumber, balance, owner);
        if (creditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит должен быть положительным или равным 0");
        }
        this.creditLimit = creditLimit;
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

    public double getCreditLimit() {
        return creditLimit;
    }

}
