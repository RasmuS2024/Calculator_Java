public class Customer {
    private int id = 1;
    private String fullName;

    Customer(String fullName) {
        this.id = id++;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }
}
