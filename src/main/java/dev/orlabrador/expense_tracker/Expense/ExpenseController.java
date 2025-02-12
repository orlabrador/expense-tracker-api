package dev.orlabrador.expense_tracker.expense;

import java.net.URI;
import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping(path = "/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> findAll(Pageable pageable, Principal principal) {
        List<Expense> expenses = expenseService.findAll(pageable, principal.getName());
        return ResponseEntity.ok(expenses);
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Expense> findById(@PathVariable Long requestedId, Principal principal) {
        Expense expense = expenseService.findByIdAndUsername(requestedId, principal.getName());
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createExpense(@RequestBody Expense newExpenseRequest, UriComponentsBuilder ucb, Principal principal) {
        Expense savedExpense = expenseService.createExpense(newExpenseRequest, principal.getName());
        URI locationOfNewExpense = ucb
                .path("expenses/{id}")
                .buildAndExpand(savedExpense.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewExpense).build();
    }

    @PutMapping("/{requestedId}")
    public ResponseEntity<Void> putExpense(@PathVariable Long requestedId, @RequestBody Expense expenseUpdate, Principal principal) {
        Expense updatedExpense = expenseService.updateExpense(requestedId, expenseUpdate, principal.getName());
        if (updatedExpense != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
