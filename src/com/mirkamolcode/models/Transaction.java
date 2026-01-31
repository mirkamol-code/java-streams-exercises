package com.mirkamolcode.models;

public record Transaction(int id,
                          double amount,
                          String type,
                          String date,
                          Customer customer
) {
}
