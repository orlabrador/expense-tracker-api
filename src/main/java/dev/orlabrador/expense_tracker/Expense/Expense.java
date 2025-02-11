package dev.orlabrador.expense_tracker.expense;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table
public class Expense {
    @Id
    @SequenceGenerator(
        name = "expense_sequence",
        sequenceName = "expense_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "expense_sequence")
    private Long id;
    private String expenseDescription;
    private Double expenseAmount;
    private LocalDate createdAt;
    private String username;
}
