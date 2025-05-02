Welcome to my Accounting-Ledger-App!
This project is a command line Java application to allow users to track and manage their financial transactions whewnever they want.


Project Features:

Transactions are stored in the ledger class and transactions.csv class which displays a custom format (date|time|description|vendor|amount).


![2025-05-01 (5)](https://github.com/user-attachments/assets/c073d124-d7d9-44cd-9e56-ab428d024580)



![2025-05-01 (6)](https://github.com/user-attachments/assets/e9c9a931-b889-4d80-9dff-ee317ed20656)


Home Screen navigation:
D: Add Deposit
P: Make Payment
L: Open the Ledger
X: Exit the application


![2025-05-01 (3)](https://github.com/user-attachments/assets/a905a122-3fc7-4d35-a26c-c7a2bc42ab45)






View Ledger Screen:
A: All
D: Deposits
P: Payments
R: Reports
H: Home

![2025-05-01 (7)](https://github.com/user-attachments/assets/a8e7e33f-5233-425f-887e-555b1656e816)



Reports Screen:
1: Month To Date
2: Previous Month
3: Year To Date
4: Previous Year
5: Seach by Vendor



![2025-05-01 (8)](https://github.com/user-attachments/assets/7729152c-bbd2-4824-943d-656a89f98176)



This project consists of 6 different class. Each class plays a key role in the format this application runs. 

The Main.java class starts the app by portraying the home screen and handles user navigation. It keeps the application running in a loop until the user exits out.

The Ledger.java is responsible for viewing and displaying trasnaction history from the trasnaction.csv class. It displays all trasnactions, deposits, and payments.

The Report.java is a key role to track when certain deposits or payments were made. It is kept into this file to keep records recorded from different times. They are recorded from Month To Date, Previous month, Year To Date, Previous Year, and has an option to search a report by their vendor.

The Transaction.java class is used to represent the stored fields such as date, time, description, vendor, and amount. This class also has getters to return information.

The TransactionManager.java handles new transactions recorded in both deposits and payments, and converts the users input into transaction outputs.

The Transaction.csv class is a simple database where all transaction rercords are stored. It is read from the Ledger and Reports class to portray transaction information accordingly.




One piece of code that I found interesting was when I implemented a filter to my transactions called "FilteredTransactions" to filter the full list of transactions based on the different types of data i needed. It helped me organize data more effectively, which made the Ledger class more powerful and useful to users.  



![2025-05-01 (9)](https://github.com/user-attachments/assets/f8ef613c-f000-4adc-b892-687c3ab3d6ba)




How to Run This App
1.Clone the repository: 

2. Open the project in IntelliJ or your favorite IDE.
3. Run `Main.java` to launch the CLI app.
4. Make sure the `transactions.csv` file is in the project root (it will be created automatically if missing).









