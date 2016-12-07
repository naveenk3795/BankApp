import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {

    private static ArrayList<Customer> customerList = new ArrayList<>();
    private static int customerID = 10567;

    public static void main(String[] args)throws IOException{

        while(true){

            System.out.print("Enter choice:  1. New Customer   2. Existing Customer or Press any other key to exit : ");

            String ch = getString();
            if(!ch.matches("\\d+"))
                return;

            int choice = Integer.parseInt(ch);

            switch(choice){
                case 1:
                    System.out.print("Enter your name :");
                    String name = getString();
                    int pin = (int) (java.lang.Math.random() * 10000);
                    Customer newOne = new Customer(name, customerID, pin);
                    customerList.add(newOne);
                    System.out.println("Your account is created. Your account ID is " + customerID++ +
                            " Your security pin is " + pin);
                    System.out.println("Enter your ID to login.");
                    break;

                case 2:
                    System.out.print("Enter your Customer ID to logIn");
                    int id = getInt();
                    boolean found = false;
                    for (Customer c : customerList)
                        if (c.getCustomerID() == id) {
                            c.actions();
                            found = true;
                        }
                    if(!found)
                        System.out.println("The entered ID is not found.");
                    break;

                default:
                    return;
            }
        }

    }

    public static String getString() throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    public static double getDouble()throws IOException{
        return Double.parseDouble(getString());
    }

    public static int getInt() throws IOException{
        return Integer.parseInt(getString());
    }

    public static char getChar()throws IOException{
        return getString().charAt(0);
    }
}
