import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String fromAccountNumber;
    private final String toAccountNumber;
    private final LocalDateTime timestamp;
    private final boolean success;
    private final String message;

    public Transaction (TransactionType type, double amount, String fromAccountNumber, String toAccountNumber,
        LocalDateTime timestamp, boolean success, String message) {
        if (type == null) {
            throw new IllegalArgumentException("Тип транзакции не может быть null");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        if (message == null) {
            throw new IllegalArgumentException("Сообщение не может быть null");
        }
        this.type = type;
        this.amount = amount;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.timestamp = timestamp;
        this.success = success;
        this.message = message;
    }
}
