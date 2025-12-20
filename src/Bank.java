import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Customer> customers;
    private final List<Account> accounts;
    private final List<Transaction> transactions;
    private int accountNumberCounter = 1;

    public Bank() {
        customers = new ArrayList<>();
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public Customer createCustomer(String fullName) {
        return new Customer(fullName);
    }

    public Account openDebitAccount(Customer owner) {
        String accountNumber = "D-ACC-" + accountNumberCounter++;
        DebitAccount account = new DebitAccount(accountNumber, 0, owner);
        accounts.add(account);
        return account;
    }
    
    public Account openCreditAccount(Customer owner, double creditLimit) {
        String accountNumber = "C-ACC-" + accountNumberCounter++;
        CreditAccount account = new CreditAccount(accountNumber, 0, creditLimit, owner);
        accounts.add(account);
        return account;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

}
