import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        Customer customer;
        DebitAccount debitAccount;
        CreditAccount creditAccount;
        String fullName;
        String accountNumber;
        String accountNumberFrom;
        String accountNumberTo;
        boolean running = true;
        int choice;
        int customerId;
        int amount;
        int creditLimit;

        while (running) {
            printMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        System.out.print("Введите ФИО клиента: ");
                        fullName = scanner.nextLine();
                        customer = bank.createCustomer(fullName);
                        System.out.println("Клиент создан. ID: " + customer.getId());
                        break;
                        
                    case 2:
                        System.out.print("Введите ID клиента: ");
                        customerId = Integer.parseInt(scanner.nextLine());
                        
                        customer = bank.findCustomerById(customerId);
                        debitAccount = bank.openDebitAccount(customer);
                        System.out.println("Счет открыт. Номер счета: " + debitAccount.getAccountNumber());
                        break;
                        
                    case 3:
                        System.out.print("Введите ID клиента: ");
                        customerId = Integer.parseInt(scanner.nextLine());
                        
                        System.out.print("Введите кредитный лимит: ");
                        creditLimit = Integer.parseInt(scanner.nextLine());

                        customer = bank.findCustomerById(customerId);
                        creditAccount = bank.openCreditAccount(customer, creditLimit);
                        System.out.println("Счет открыт. Номер счета: " + creditAccount.getAccountNumber());
                        break;
                        
                    case 4:
                        System.out.print("Введите номер счета: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Введите сумму для пополнения: ");
                        amount = Integer.parseInt(scanner.nextLine());

                        if (bank.deposit(accountNumber, amount)) {
                            System.out.println("Счет " + accountNumber + " пополнен на сумму: " + amount);
                        } else {
                            System.out.println("Не удалось пополнить счет " + accountNumber + " на сумму: " + amount);
                        }
                        break;

                    case 5:
                        System.out.print("Введите номер счета: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Введите сумму для снятия: ");
                        amount = Integer.parseInt(scanner.nextLine());
                        if (bank.withdraw(accountNumber, amount)) {
                            System.out.println("Со счета " + accountNumber + " снята сумма: " + amount);
                        } else {
                            System.out.println("Не удалось снять со счета " + accountNumber + " сумму: " + amount);
                        }
                        break;

                    case 6:
                        System.out.print("Введите номер счета с которого нужно перевести: ");
                        accountNumberFrom = scanner.nextLine();
                        System.out.print("Введите номер счета на который нужно перевести: ");
                        accountNumberTo = scanner.nextLine();
                        System.out.print("Введите сумму для перевода: ");
                        amount = Integer.parseInt(scanner.nextLine());

                        if (bank.transfer(accountNumberFrom, accountNumberTo, amount)) {
                            System.out.println("Со счета " + accountNumberFrom + " переведена сумма: " + amount + " на счет: " + accountNumberTo);
                        } else {
                            System.out.println("Не удалось перевести со счета " + accountNumberFrom + " сумма: " + amount + " на счет: " + accountNumberTo);
                        }
                        break;
                    
                    case 7:
                        System.out.print("Введите ID клиента: ");
                        customerId = Integer.parseInt(scanner.nextLine());
                        bank.printCustomerAccounts(customerId);
                        break;
                    
                    case 8:
                        break;
                    
                    case 9:
                        break;

                    case 0:
                        running = false;
                        System.out.println("Выход из программы...");
                        break;

                    default:
                        System.out.println("Неверный выбор!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число.");
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

        }
        scanner.close();

    }

    public static void printMenu() {
        System.out.println("\r\n" + 
                        "---------------------------------------\r\n" +
                        "Вы работаете с банком. Вам доступно:\r\n" + 
                        "1. Создать клиента\r\n" + 
                        "2. Открыть дебетовый счёт\r\n" + 
                        "3. Открыть кредитный счёт\r\n" + 
                        "4. Пополнить\r\n" + 
                        "5. Снять\r\n" + 
                        "6. Перевести\r\n" + 
                        "7. Показать счета клиента\r\n" + 
                        "8. Показать транзакции\r\n" + 
                        "9. Отчёт банка\r\n" + 
                        "0. Выход\r\n" + 
                        "Введите номер задачи и нажмите Enter:");
    }
}