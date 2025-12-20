public abstract class Account {
    private String accountNumber;
    private double balance = 0;
    private Customer owner;

    Account(String accountNumber, double balance, Customer owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(Account to, double amount) {
        if (amount > 0) {
            return this.withdraw(amount) && to.deposit(amount);
        }
        return false;
    }

    public double getBalance() {
        return this.balance;
    }
    
    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Customer getOwner() {
        return this.owner;
    }
}
