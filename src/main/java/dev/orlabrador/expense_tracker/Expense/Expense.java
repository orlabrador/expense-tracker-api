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
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private String description;
    private Double amount;
    private LocalDate createdAt;
    private String username;
}
