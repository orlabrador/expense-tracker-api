package dev.orlabrador.expense_tracker;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXPENSES")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "Description", nullable = false, unique = false)
    private String description;

    @Column(name = "Amount", nullable = false, unique = false)
    private Long amount;

    @Column(name = "Date")
    private LocalDate date;
}
