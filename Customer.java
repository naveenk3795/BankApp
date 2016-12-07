import java.io.IOException;
import java.util.ArrayList;

public class Customer {

    private String name;
    private int customerID;
    private int pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Customer(String name, int customerID, int pin) {
        this.name = name;
        this.customerID = customerID;
        this.pin = pin;
        balance = 0;
        transactionHistory = new ArrayList<>();
    }


    public int getCustomerID() {
        return customerID;
    }


    public void actions() throws IOException {
        int count = 0;
        System.out.print("Enter your pin number to login : ");
        int pin1 = App.getInt();
        while (pin1 != pin) {
            if (count == 3) {
                System.out.println("Too many failed attempts. Try again later");
                return;
            }
            System.out.println("Pin mismatch. Try again.");
            pin1 = App.getInt();
            count++;
        }

        while (true) {
            System.out.print("Enter choice:  1. Credit  2. withdrawal  3. Transaction history or " +
                    "Press any other key to exit : ");
            String ch = App.getString();

            if(!ch.matches("\\d+"))
                return;

            int choice = Integer.parseInt(ch);

            switch (choice) {
                case 1:
                    credit();
                    break;

                case 2:
                    debit();
                    break;

                case 3:
                    System.out.println("Your current balance is : " + balance);
                    transactionHistory.stream().forEach(Transaction -> Transaction.display());

                    System.out.println();
                    break;

                default:
                    return;
            }
        }
    }

    private void credit() throws IOException {
        System.out.print("Enter the amount to be credited: ");
        double amount = App.getDouble();

        if(amount == 0){
            System.out.println("Zero rupees cannot be credited.");
            return;
        }

        Transaction credit = new Transaction(true, amount);
        transactionHistory.add(credit);

        balance = balance + amount;

        System.out.println(amount + " is credited to your account. Your Current " +
                "balance is " + balance + ".");
    }

    private void debit() throws IOException {
        System.out.println("Your current account balance is " + balance);
        if (balance == 0) {
            System.out.println("Sorry! Your account does not have sufficient balance.");
            return;
        }

        System.out.print("Enter the amount to be withdrawn: ");
        double amount = App.getDouble();

        if(amount == 0){
            System.out.println("Zero rupees cannot be withdrawn.");
            return;
        }

        while (amount > balance) {
            System.out.print("Sorry! Your account does not have sufficient balance. Enter a new amount : ");
            amount = App.getDouble();
        }

        Transaction debit = new Transaction(false, amount);
        transactionHistory.add(debit);

        balance = balance - amount;

        System.out.println(amount + " is debited from your account. Your Current " +
                "balance is " + balance + ".");
    }
}
