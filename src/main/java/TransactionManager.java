import java.util.Scanner;


public class TransactionManager {
    private static final String FILE_PATH = "transactions.csv";

    //Methods for deposit or payments
    public void addTransaction(Scanner scanner, boolean isDeposit) {
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) {
            amount *= -1;
        }

    }
}

