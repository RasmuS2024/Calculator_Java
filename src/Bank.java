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
        Customer customer = new Customer(fullName);
        customers.add(customer);
        return customer;
    }

    public DebitAccount openDebitAccount(Customer owner) {
        String accountNumber = "D-ACC-" + accountNumberCounter++;
        DebitAccount account = new DebitAccount(accountNumber, 0, owner);
        accounts.add(account);
        return account;
    }
    
    public CreditAccount openCreditAccount(Customer owner, double creditLimit) {
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

    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            logTransaction(TransactionType.DEPOSIT, amount, null, accountNumber, 
                          false, "Счет не найден");
            return false;
        }
        
        if (account.deposit(amount)) {
            logTransaction(TransactionType.DEPOSIT, amount, null, accountNumber, true, "ОК");
            return true;
        }
        logTransaction(TransactionType.DEPOSIT, amount, null, accountNumber, false, "Пополнение счета не удалось!");
        return false;
        
    }

    public void printCustomerAccounts(int customerId) {
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Клиент с ID " + customerId + " не найден");
            return;
        }
        
        System.out.println("\n=== Счета клиента: " + customer.getFullName() + " ===");
        System.out.printf("%-15s %-20s %-12s %-10s%n", 
                         "Номер счета", "Тип счета", "Баланс", "Доп. информация");
        System.out.println("-".repeat(60));
        
        boolean hasAccounts = false;
        for (Account account : accounts) {
            if (account.getOwner().getId() == customerId) {
                hasAccounts = true;
                String accountType = account instanceof CreditAccount ? "Кредитный" : "Дебетовый";
                String additionalInfo = "";
                if (account instanceof CreditAccount) {
                    additionalInfo = "Лимит: " + ((CreditAccount) account).getCreditLimit();
                }
                
                System.out.printf("%-15s %-20s %-12.2f %-10s%n",
                                 account.getAccountNumber(),
                                 accountType,
                                 account.getBalance(),
                                 additionalInfo);
            }
        }
        
        if (!hasAccounts) {
            System.out.println("У клиента нет открытых счетов");
        }
    }

    public Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    private void logTransaction(TransactionType type, double amount, String fromAccount, String toAccount,
                               boolean success, String message) {
        Transaction transaction = new Transaction(type, amount, fromAccount, toAccount, success, message);
        transactions.add(transaction);
    }

}
