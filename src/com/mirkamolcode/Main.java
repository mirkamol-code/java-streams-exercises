package com.mirkamolcode;

import com.mirkamolcode.models.Customer;
import com.mirkamolcode.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {
   static void addData(List<Transaction> transactions) {
        for (int i = 1; i <= 100; i++) {
            transactions.add(new Transaction(
                    i,
                    Math.random() * 1000, // Random amount between 0 and 1000
                    i % 2 == 0 ? "CREDIT" : "DEBIT", // Alternate between CREDIT and DEBIT
                    "2024-" + String.format("%02d", (i % 12) + 1) + "-" + String.format("%02d", (i % 28) + 1), // Random date
                    new Customer(i, "Customer" + i, "customer" + i + "@example.com")
            ));
        }
    }

    static void main() {
        List<Transaction> transactions = new ArrayList<>();
        addData(transactions);
        transactions.forEach(System.out::println);
    }
}
