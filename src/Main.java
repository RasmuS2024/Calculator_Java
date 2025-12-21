import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        boolean running = true;

        while (running) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        System.out.print("Введите ФИО клиента: ");
                        String fullName = scanner.nextLine();
                        Customer customer = bank.createCustomer(fullName);
                        System.out.println("Клиент создан. ID: " + customer.getId());
                        break;
                        
                    case 2:
                        System.out.print("Введите ID клиента: ");
                        int customerId = Integer.parseInt(scanner.nextLine());
                        customer = bank.findCustomerById(customerId);
                        DebitAccount debitAccount = bank.openDebitAccount(customer);
                        System.out.println("Счет открыт. Номер счета: " + debitAccount.getAccountNumber());
                        break;
                        
                    case 3:
                        System.out.print("Введите ID клиента: ");
                        customerId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите кредитный лимит: ");
                        int creditLimit = Integer.parseInt(scanner.nextLine());

                        customer = bank.findCustomerById(customerId);
                        CreditAccount creditAccount = bank.openCreditAccount(customer, creditLimit);
                        System.out.println("Счет открыт. Номер счета: " + creditAccount.getAccountNumber());
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