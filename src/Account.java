public class Account {
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
        } else {
            System.out.println("Сумма должна быть положительной!");
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (this.balance > 0 && amount <= this.balance) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("На счете не хватает средств!");
            return false;
        }
    }

    public boolean transfer(Account to, double amount) {
        if (to == null) {
            System.out.println("Ошибка: счет получателя не существует");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Ошибка: сумма должна быть положительной");
            return false;
        }
        
        if (this == to) {
            System.out.println("Ошибка: нельзя переводить на тот же счет");
            return false;
        }
        
        return this.withdraw(amount) && to.deposit(amount);
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
