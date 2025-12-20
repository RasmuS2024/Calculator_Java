public class Customer {
    private int id = 1;
    private String fullName;

    Customer(int id, String fullName) {
        this.id = id++;
        this.fullName = fullName;
    }

    public String getfullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }
}
