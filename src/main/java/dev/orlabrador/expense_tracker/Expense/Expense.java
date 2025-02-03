package dev.orlabrador.expense_tracker.Expense;


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
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
    private float amount;
    private LocalDate date;

}
