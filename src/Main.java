public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer(0, "Gonsales");
        Account Debit = new DebitAccount("1234 1234", 50000, customer);
        Account Credit = new CreditAccount("5678 3456", 3000, customer);

        Debit.deposit(3000);
        System.out.println("Баланс: " + Debit.getBalance());
        System.out.println("Баланс кредитного счета: " + Credit.getAccountNumber() + " равен: " + Credit.getBalance());
        Credit.transfer(Debit, 13000);
        System.out.println("Баланс счета: " + Debit.getAccountNumber() + " равен: " + Debit.getBalance());
        System.out.println("Баланс кредитного счета: " + Credit.getAccountNumber() + " равен: " + Credit.getBalance());
    }
}