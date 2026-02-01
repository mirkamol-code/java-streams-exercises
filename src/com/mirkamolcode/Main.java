package com.mirkamolcode;

import com.mirkamolcode.models.Customer;
import com.mirkamolcode.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Transaction> addData() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            transactions.add(new Transaction(
                    i,
                    Math.random() * 1000, // Random amount between 0 and 1000
                    i % 2 == 0 ? "CREDIT" : "DEBIT", // Alternate between CREDIT and DEBIT
                    "2024-" + String.format("%02d", (i % 12) + 1) + "-" + String.format("%02d", (i % 28) + 1), // Random date
                    new Customer(i, "Customer" + i, "customer" + i + "@example.com")
            ));
        }
        return transactions;
    }

    static void main() {
        List<Transaction> transactions = addData();

        // Task 1
        filterTransactionByType(transactions).forEach(System.out::println);

        // Task 2
        System.out.println(getTotalAmountOfAllTransactions(transactions));

        // Task 3
        System.out.println(getMaximumTransactionAmount(transactions));

        // Task 4
        System.out.println(countTransactionsByDebit(transactions));
        System.out.println(countTransactionsByCredit(transactions));

        // Task 5
        getDistinctCustomers(transactions).forEach(System.out::println);
    }

    private static List<Customer> getDistinctCustomers(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::customer)
                .distinct()
                .toList();
    }

    private static long countTransactionsByCredit(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.type().equals("CREDIT"))
                .count();
    }

    private static long countTransactionsByDebit(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.type().equals("DEBIT"))
                .count();
    }

    private static Double getMaximumTransactionAmount(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .max()
                .orElse(0);
    }

    private static Double getTotalAmountOfAllTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .sum();
    }

    private static List<Transaction> filterTransactionByType(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.type().equals("CREDIT"))
                .toList();
    }
}
