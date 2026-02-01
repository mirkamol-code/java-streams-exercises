package com.mirkamolcode;

import com.mirkamolcode.models.Customer;
import com.mirkamolcode.models.Transaction;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        filterTransactionByCreditType(transactions).forEach(System.out::println);

        // Task 2
        System.out.println(getTotalAmountOfAllTransactions(transactions));

        // Task 3
        System.out.println(getMaximumTransactionAmount(transactions));

        // Task 4
        System.out.println(countTransactionsByDebit(transactions));
        System.out.println(countTransactionsByCredit(transactions));

        // Task 5
        getDistinctCustomers(transactions).forEach(System.out::println);

        // Task 6
        getTransactionsAboveThreshold(transactions).forEach(System.out::println);

        // Task 7
        groupTransactionsByCustomer(transactions)
                .forEach((customer, transactions1) -> {
                    System.out.println("Customer id: " + customer);
                    transactions1.forEach(System.out::println);
                    System.out.println("-------------------");
                });

        // Task 8
        System.out.println(getFirstTransaction(transactions));

        // Task 9
        System.out.println(checkForAnyHighValueTransaction(transactions));

        // Task 10
        sortTransactionByAmount(transactions).forEach(System.out::println);

        // Task 11
        System.out.println(calculateAverageTransactionAmount(transactions));

        // Task 12
        getTransactionsByCustomer(transactions, "Customer2").forEach(System.out::println);

        // Task 13
        getDistinctTransactions(transactions).forEach(System.out::println);

        // Task 14
        System.out.println(getCustomerNames(transactions));

        // Task 15
        System.out.println(getEarliestTransaction(transactions));

        // Task 16
        getTransactionsFrom2024(transactions).forEach(System.out::println);

        // Task 17
        calculateTotalAmountByCustomer(transactions).forEach(
                (customer, doubleSummaryStatistics) -> {
                    System.out.println(customer);
                    System.out.println(doubleSummaryStatistics.getSum());
                    System.out.println("------");
                }
        );

        // Task 18
        filterTransactionByDebitType(transactions).forEach(System.out::println);

        // Task 19
        groupTransactionsByType(transactions).forEach(
                (type, transactionList) -> {
                    System.out.println(type);
                    transactionList.forEach(System.out::println);
                    System.out.println("---------");
                }
        );
    }

    private static Map<String, List<Transaction>> groupTransactionsByType(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    private static List<Transaction> filterTransactionByDebitType(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.type().equals("DEBIT"))
                .toList();
    }

    private static Map<Customer, DoubleSummaryStatistics> calculateTotalAmountByCustomer(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::customer,
                        Collectors.summarizingDouble(Transaction::amount)
                ));
    }

    private static List<Transaction> getTransactionsFrom2024(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.date().startsWith("2024"))
                .toList();
    }

    private static Transaction getEarliestTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::date))
                .findFirst()
                .get();
    }

    private static String getCustomerNames(List<Transaction> transactions) {
        return transactions.stream()
                .map(transaction -> transaction.customer().name())
                .collect(Collectors.joining(", "));
    }

    private static List<Transaction> getDistinctTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .distinct()
                .toList();
    }

    private static List<Transaction> getTransactionsByCustomer(List<Transaction> transactions, String name) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.customer().name().equals(name))
                .toList();
    }

    private static Double calculateAverageTransactionAmount(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(Transaction::amount)
                .average()
                .orElse(0);
    }

    private static List<Transaction> sortTransactionByAmount(List<Transaction> transactions) {
        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::amount))
                .toList();
    }

    private static boolean checkForAnyHighValueTransaction(List<Transaction> transactions) {
        return transactions.stream()
                .anyMatch(transaction ->
                        transaction.amount() > 1000);
    }

    private static Transaction getFirstTransaction(List<Transaction> transactions) {
        return transactions.stream().findFirst().get();
    }

    private static Map<Integer, List<Transaction>> groupTransactionsByCustomer(List<Transaction> transactions) {
        return transactions
                .stream()
                .collect(Collectors.groupingBy(transaction -> transaction.customer().id()));
    }

    private static List<Transaction> getTransactionsAboveThreshold(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.amount() > 300)
                .toList();
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

    private static List<Transaction> filterTransactionByCreditType(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction ->
                        transaction.type().equals("CREDIT"))
                .toList();
    }
}
