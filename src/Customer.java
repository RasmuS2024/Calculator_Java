public class Customer {
    private static int idCounter = 1;
    private final int id;
    private final String fullName;

    Customer(String fullName) {
        this.id = idCounter++;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }
}
