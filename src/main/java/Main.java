import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Main {
    public Main () {}

    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        Scanner scanner = new Scanner(System.in);

        //Create a greeting when app first opened
        System.out.println("Welcome to the AccountingLedgerApp!");
        showHomeScreen(scanner, manager);

        scanner.close();
    }

    //Create HomeScreen
    public static void showHomeScreen(Scanner scanner, TransactionManager manager) {
        boolean running = true;

    Ledger ledger = new Ledger();


        //
        while (running) {
            System.out.println("\n=== HOME SCREEN ===");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
                case "D":
                    manager.addTransaction(scanner, true);
                    break;
                case "P":
                    manager.addTransaction(scanner, false);
                    break;
                case "L":
                    ledger.displayLedger();
                    break;
                case "X":
                    System.out.println("Goodbye!");
                    //running = false;
                    System.exit(0);
                    //allTransaction.clear();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");

            }

        }

    }

 }