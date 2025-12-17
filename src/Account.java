public class Account {
    private int accountNumber;
    private double balance;
    private Customer owner;

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            System.out.println("Сумма должна быть положительной!");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (balance > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            System.out.println("На счете не хватает средств!");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }

    public Customer getOwner() {
        return owner;
    }
}
