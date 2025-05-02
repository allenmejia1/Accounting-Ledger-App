import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Array;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class Ledger {


    //Constructor
    public Ledger() {
    }


    //Display the ledger and transactions history
    public void displayLedger() {
//        String[] args = new String[0];
        Main home = new Main();
        TransactionManager manager = new TransactionManager();
        boolean isrunning = true;

        while (isrunning) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n--- LEDGER ---");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim().toUpperCase();
            switch (input) {
                case "A":
                    displayAllTransactions();
                    break;
                case "D":
                    displayDeposits();
                    break;
                case "P":
                    displayPayments();
                    break;
                case "R":
                    Report report = new Report(this);
                    report.displayReports();
                    break;
                case "H":
                    Main.showHomeScreen(scanner, manager);
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");

            }
        }
    }

    //Read all transactions
    public List<Transaction> readAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("transactions.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length != 5) continue;

                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                transactions.add(new Transaction(date, time, description, vendor, amount));

            }
        } catch (Exception e) {
            System.out.println("Error reading transactions: " + e.getMessage());
        }

        return transactions;

    }


    //Display all transactions
    public void displayAllTransactions() {
        List<Transaction> transactions = readAllTransactions();

        System.out.println("\nDATE             TIME           | DESCRIPTION              | VENDOR                | AMOUNT");
        System.out.println("------------------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            System.out.printf("%s %s | %-25s | %-15s | %10.2f\n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());


        }

    }

    public void displayDeposits() {
        List<Transaction> transactions = readAllTransactions();
        System.out.println("\n---DEPOSITS---");
        System.out.println("DATE           TIME         |DESCRIPTION                |VENDOR                  |AMOUNT");
        System.out.println("------------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            if (t.getAmount() > 0) {
                System.out.printf("%s %s | %-25s | %-15s | %10.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

            }
        }
    }

    public void displayPayments() {
        List<Transaction> transactions = readAllTransactions();
        System.out.println("\n---Payements---");
        System.out.println("DATE           TIME         |DESCRIPTION                |VENDOR                  |AMOUNT");
        System.out.println("------------------------------------------------------------------------------------");

        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {
                System.out.printf("%s %s | %-25s | %-15s | %10.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

            }
        }
    }
}
