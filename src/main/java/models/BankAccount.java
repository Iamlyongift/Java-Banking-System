package models;

public class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Withdraw method
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // Transfer method
    public void transfer(BankAccount target, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            target.deposit(amount);
            System.out.println("Transferred " + amount + " to " + target.getAccountHolderName());
        } else {
            System.out.println("Transfer failed: insufficient funds or invalid amount.");
        }
    }

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Optional: override toString for easy display
    @Override
    public String toString() {
        return accountNumber + " | " + accountHolderName + " | Balance: " + balance;
    }
}
