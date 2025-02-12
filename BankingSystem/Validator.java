import java.util.Scanner;

public class Validator {
    Scanner input = new Scanner(System.in);
    Bank bank = new Bank();
    private Account loggedInAccount;

    public Validator() {
        System.out.print("Enter account name: ");
        String accountName = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();
        if (isLoginValid(accountName, password)) {
            System.out.println("Login successful!");
            System.out.println("Welcome, "+loggedInAccount.getAccountName()+"!");
        }
        else {
            System.out.println("Account name or password is incorrect.");
        }
        input.close();
    }
    public boolean isLoginValid(String accountName, String password) {
        Account acc = bank.getAccount(accountName);
        {
            if (acc != null && acc.getAccountName().equals(accountName) && acc.getPassword().equals(password)) {
                loggedInAccount = acc;
                return true;
            }
        }
        return false;
    }
}
