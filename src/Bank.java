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
        String accountNumber = "D-" + accountNumberCounter++;
        DebitAccount account = new DebitAccount(accountNumber, 0, owner);
        accounts.add(account);
        return account;
    }
    
    public CreditAccount openCreditAccount(Customer owner, double creditLimit) {
        String accountNumber = "C-" + accountNumberCounter++;
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
        
        boolean result = account.deposit(amount);
        String message = result ? "Пополнение успешно" : "Пополнение неуспешно";
        logTransaction(TransactionType.WITHDRAW, amount, accountNumber, null,
                      result, message);
        return result;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account == null) {
            logTransaction(TransactionType.WITHDRAW, amount, accountNumber, null,
                          false, "Счет не найден");
            return false;
        }
        
        boolean result = account.withdraw(amount);
        String message = result ? "Снятие успешно" : "Снятие неуспешно";
        logTransaction(TransactionType.WITHDRAW, amount, accountNumber, null,
                      result, message);
        return result;
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);
        
        if (fromAccount == null || toAccount == null) {
            String errorMsg = fromAccount == null ? "Счет отправителя не найден" : "Счет получателя не найден";
            logTransaction(TransactionType.TRANSFER, amount, fromAccountNumber, toAccountNumber,
                          false, errorMsg);
            return false;
        }
        
        if (fromAccountNumber.equals(toAccountNumber)) {
            logTransaction(TransactionType.TRANSFER, amount, fromAccountNumber, toAccountNumber,
                          false, "Нельзя переводить на тот же счет");
            return false;
        }
        
        boolean success = fromAccount.transfer(toAccount, amount);
        String message = success ? "Перевод выполнен" : "Перевод не выполнен";
        logTransaction(TransactionType.TRANSFER, amount, fromAccountNumber, toAccountNumber, success, message);
        return success;
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
        System.out.println("-".repeat(65));
        
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

    public void printTransactions() {
        System.out.println("\n=== История всех транзакций ===");
        System.out.printf("%-25s %-12s %-10s %-15s %-15s %-8s %-30s%n",
                         "Дата и время", "Тип", "Сумма", "От", "К", "Статус", "Сообщение");
        System.out.println("-".repeat(120));
        
        for (Transaction transaction : transactions) {
            System.out.printf("%-25s %-12s %-10.2f %-15s %-15s %-8s %-30s%n",
                             transaction.getTimestamp(),
                             transaction.getType(),
                             transaction.getAmount(),
                             transaction.getFromAccountNumber() != null ? 
                                 transaction.getFromAccountNumber() : "—",
                             transaction.getToAccountNumber() != null ? 
                                 transaction.getToAccountNumber() : "—",
                             transaction.isSuccess() ? "✓" : "✗",
                             transaction.getMessage());
        }
        
        System.out.println("Всего транзакций: " + transactions.size());
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
