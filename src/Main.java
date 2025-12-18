public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(0, "Gonsales");
        Account account = new Account("1234 1234", 50000, customer);
        Account account2 = new Account("5678 3456", 0, customer);

        account.deposit(100500);
        System.out.println("Баланс: " + account.getBalance());
        account.transfer(account2, 150000);
        System.out.println("Баланс счета: " + account.getAccountNumber() + " равен: " + account.getBalance());
        System.out.println("Баланс счета: " + account2.getAccountNumber() + " равен: " + account2.getBalance());
    }
}