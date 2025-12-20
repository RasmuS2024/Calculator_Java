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
        if (amount > 0 && (getBalance() - amount) >= -creditLimit) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

}
