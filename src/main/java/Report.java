import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;

public class Report {

    private Ledger ledger;

    //Constructors
    public Report(Ledger ledger) {
        this.ledger = ledger;

    }


    //Display new screen with the reports or to run a custom search
    public void displayReports() {
        System.out.println("Reports menu operating");
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n--- REPORTS ---");
        System.out.println("1) Month To Date");
        System.out.println("2) Previous Month");
        System.out.println("3) Year To Date");
        System.out.println("4) Previous Year");
        System.out.println("5) Search");
        System.out.println("0) Back");

        String input = scanner.nextLine().trim();
        switch (input) {
            case "1":
                displayMonthToDate();
                break;
            case "2":
                displayPreviousMonth();
                break;
            case "3":
                displayYearToDate();
                break;
            case "4":
                displayPreviousYear();
                break;
            case "5":
                System.out.print("Enter vendor name: ");
                String vendor = scanner.nextLine().trim();
                searchByVendor(vendor);
                break;
            case "0":
                ledger.displayLedger();
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;

        }
    }

    public void displayMonthToDate() {
        List<Transaction> transactions = ledger.readAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfMonth(1);

        System.out.println("\n---MONTH TO DATE ---");
        printFilteredTransactions(transactions, start, today);
    }

    private void printFilteredTransactions(List<Transaction> transactions, LocalDate start, LocalDate end) {
        for (Transaction t : transactions) {
            if (!t.getDate().isBefore(start) && !t.getDate().isAfter(end)) {
                System.out.printf("%s %s | %-25s | %-15s | %10.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
            }

        }
    }

    public void searchByVendor(String vendor){
        List<Transaction> transactions = ledger.readAllTransactions();
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.printf("%s %s | %-25s | %-15s | %10.2f\n",
                        t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());

            }
        }

    }

    public void displayPreviousMonth() {
        List<Transaction> transactions = ledger.readAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.minusMonths(1).withDayOfMonth(1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        System.out.println("\n--- PREVIOUS MONTH---");
        printFilteredTransactions(transactions, start, end );


    }

    public void displayYearToDate(){
        List<Transaction> transactions = ledger.readAllTransactions();
        LocalDate today = LocalDate.now();
        LocalDate start = today.withDayOfYear(1);

        System.out.println("\n--- YEAR TO DATE ---");
        printFilteredTransactions(transactions, start, today);

    }

    public void displayPreviousYear() {
        List<Transaction> transactions = ledger.readAllTransactions();
        LocalDate start = LocalDate.now().minusYears(1).withDayOfYear(1);
        LocalDate end = start.withDayOfMonth(12).withDayOfMonth(31);

        System.out.println("\n--- PREVIOUS YEAR ---");
        printFilteredTransactions(transactions, start, end);

    }



}

