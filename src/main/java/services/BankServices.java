package services;

import models.BankAccount;
import dbConnect.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankServices {

    private List<BankAccount> accounts;

    public BankServices() {
        this.accounts = new ArrayList<>();
        DatabaseConnection.createTable();  // ✅ Create table on startup
        loadAccountsFromDatabase();        // ✅ Load from database instead of file
    }

    public void createAccount(String accountNumber, String fullName, double initialDeposit) {

        // Check if account already exists
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                System.out.println("Error: Account with number " + accountNumber + " already exists!");
                return;
            }
        }

        BankAccount newAccount = new BankAccount(accountNumber, fullName, initialDeposit);
        accounts.add(newAccount);

        // ✅ Save to database immediately
        saveAccountToDatabase(newAccount);

        System.out.println("Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + fullName);
        System.out.println("Initial Balance: $" + initialDeposit);
    }

    public BankAccount findAccountByNumber(String accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        return null;
    }

    public List<BankAccount> getAllAccounts() {
        return accounts;
    }

    // ✅ NEW: Save single account to database
    public void saveAccountToDatabase(BankAccount account) {
        String sql = "INSERT OR REPLACE INTO accounts (account_number, account_holder_name, balance) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountHolderName());
            pstmt.setDouble(3, account.getBalance());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    // ✅ NEW: Update account balance in database
    public void updateAccountBalance(BankAccount account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, account.getBalance());
            pstmt.setString(2, account.getAccountNumber());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error updating balance: " + e.getMessage());
        }
    }

    // ✅ REPLACED: Load from database instead of file
    public void loadAccountsFromDatabase() {
        String sql = "SELECT * FROM accounts";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String accountNumber = rs.getString("account_number");
                String name = rs.getString("account_holder_name");
                double balance = rs.getDouble("balance");

                BankAccount account = new BankAccount(accountNumber, name, balance);
                accounts.add(account);
            }

            System.out.println("Loaded " + accounts.size() + " accounts from database.");

        } catch (SQLException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    public void saveAllAccountsToDatabase() {
        for (BankAccount acc : accounts) {
            saveAccountToDatabase(acc);
        }
    }


    // ⚠️ KEEPING OLD METHODS FOR BACKWARD COMPATIBILITY (optional)
    // You can remove these if you don't need file support anymore
/*
    public void saveAccountsToFile() {
        // This method is now deprecated - using database instead
        System.out.println("File saving is deprecated. Using database storage.");
    }

    public void loadAccountsFromFile() {
        // This method is now deprecated - using database instead
        System.out.println("File loading is deprecated. Using database storage.");
    }

 */
}