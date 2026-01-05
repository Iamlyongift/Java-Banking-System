package com.lyonscode.com;

import models.BankAccount;
import services.BankServices;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BankServices bankServices = new BankServices();

        // ✅ 1. LOAD ACCOUNTS FROM FILE ON STARTUP
        bankServices.loadAccountsFromFile();

        BankAccount currentAccount = null;

        while (true) {
            System.out.println("\n*************");
            System.out.println("Enter option 1-6: ");
            System.out.println("1: create account");
            System.out.println("2: login");
            System.out.println("3: deposit");
            System.out.println("4: withdraw");
            System.out.println("5: transfer");
            System.out.println("6: exit");
            System.out.println("*************");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter account number:");
                    String accountNumber = scanner.nextLine();

                    System.out.println("Enter full name:");
                    String fullName = scanner.nextLine();

                    System.out.println("Enter initial deposit:");
                    double initialDeposit = scanner.nextDouble();
                    scanner.nextLine();

                    bankServices.createAccount(accountNumber, fullName, initialDeposit);
                    break;

                case 2:
                    System.out.println("Enter your account number:");
                    String loginAccNum = scanner.nextLine();

                    currentAccount = bankServices.findAccountByNumber(loginAccNum);

                    if (currentAccount != null) {
                        System.out.println("Login successful! Welcome " +
                                currentAccount.getAccountHolderName());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    if (currentAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }

                    System.out.println("Enter amount to deposit:");
                    double depositAmount = scanner.nextDouble();
                    scanner.nextLine();

                    currentAccount.deposit(depositAmount);
                    System.out.println("New balance: $" + currentAccount.getBalance());
                    break;

                case 4:
                    if (currentAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }

                    System.out.println("Enter withdrawal amount:");
                    double withdrawalAmount = scanner.nextDouble();
                    scanner.nextLine();

                    currentAccount.withdraw(withdrawalAmount);
                    System.out.println("New balance: $" + currentAccount.getBalance());
                    break;

                case 5:
                    if (currentAccount == null) {
                        System.out.println("Please login first.");
                        break;
                    }

                    System.out.println("Enter recipient account number:");
                    String targetAccNum = scanner.nextLine();

                    BankAccount targetAccount =
                            bankServices.findAccountByNumber(targetAccNum);

                    if (targetAccount == null) {
                        System.out.println("Recipient account not found!");
                        break;
                    }

                    System.out.println("Enter transfer amount:");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine();

                    currentAccount.transfer(targetAccount, transferAmount);

                    System.out.println("Your balance: $" + currentAccount.getBalance());
                    System.out.println("Recipient balance: $" + targetAccount.getBalance());
                    break;

                case 6:
                    // ✅ 2. SAVE ACCOUNTS BEFORE EXIT
                    bankServices.saveAccountsToFile();

                    System.out.println("Accounts saved. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Choose 1-6.");
            }
        }
    }
}
