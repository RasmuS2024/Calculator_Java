public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.deposit(100500);
        System.out.println("Баланс: " + account.getBalance());
    }
}