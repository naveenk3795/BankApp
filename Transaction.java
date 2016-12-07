import java.util.*;
public class Transaction {
    private boolean isCredit;
    private double amount;
    private Date date;

    public Transaction(boolean isCredit, double amount){
        this.isCredit = isCredit;
        this.amount = amount;
        date = new Date();
    }

    public void display(){
        if(isCredit)
            System.out.println(amount + " is credited at " + date.toString() + ".");
        else
            System.out.println(amount + " is debited at " + date.toString() + ".");
    }
}
