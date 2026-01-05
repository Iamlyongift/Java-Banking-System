package services;

import models.BankAccount;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankServices {

    private List<BankAccount> accounts;

    public BankServices() {
        this.accounts = new ArrayList<>();
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

    public void saveAccountsToFile() {
        try (FileWriter writer = new FileWriter("accounts.txt")) {

            for (BankAccount account : accounts) {
                writer.write(
                        account.getAccountNumber() + "," +
                                account.getAccountHolderName() + "," +
                                account.getBalance()
                );
                writer.write("\n");
            }

            System.out.println("Accounts saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public void loadAccountsFromFile() {
        File file = new File("accounts.txt");

        if (!file.exists()) {
            System.out.println("No accounts file found. Starting fresh.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String accountNumber = parts[0].trim();
                String accountHolderName = parts[1].trim();
                double balance = Double.parseDouble(parts[2].trim());

                BankAccount account = new BankAccount(
                        accountNumber,
                        accountHolderName,
                        balance
                );

                accounts.add(account);
            }

            System.out.println("Successfully loaded " + accounts.size() + " accounts from file.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format in file: " + e.getMessage());
        }
    }
}
