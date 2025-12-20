public class Main {
    public static void main(String[] args) {

        Bank bank = new Bank();
        Customer customer = bank.createCustomer("Вася");
        Account debit = bank.openDebitAccount(customer);
        Account credit = bank.openCreditAccount(customer, 7000);
        System.out.println(debit.getAccountNumber());

        bank.deposit("D-ACC-1", 5000);
        bank.printCustomerAccounts(1);
        System.out.println(customer.getId());

    }
}