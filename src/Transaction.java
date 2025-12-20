import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String fromAccount;
    private final String toAccount;
    private final LocalDateTime timestamp;
    private final boolean success;
    private final String message;

    public Transaction(TransactionType type, double amount, String from, String to, boolean success, String message) {
        this.type = type;
        this.amount = amount;
        this.fromAccount = from;
        this.toAccount = to;
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f | From: %s | To: %s | Success: %b (%s)",
                timestamp, type, amount, fromAccount, toAccount, success, message);
    }
    
}
