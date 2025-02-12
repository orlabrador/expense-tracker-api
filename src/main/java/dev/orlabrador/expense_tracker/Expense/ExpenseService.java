package dev.orlabrador.expense_tracker.expense;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense findByIdAndUsername (Long requestedId, String username) {
        return expenseRepository.findByIdAndUsername(requestedId, username);
    }

    public List<Expense> findAll(Pageable pageable, String username) {
        Page<Expense> page = expenseRepository.findByUsername(username,
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
                ));
        return page.getContent();
    }

    public Expense createExpense(Expense newExpenseRequest, String username) {
        Expense expenseWithUsername = new Expense(null, newExpenseRequest.getDescription(), newExpenseRequest.getAmount(), LocalDate.now(), username);
        return expenseRepository.save(expenseWithUsername);
    }

    public Expense updateExpense(Long requestedId, Expense expenseUpdate, String username) {
        Expense existingExpense = expenseRepository.findByIdAndUsername(requestedId, username);
        if (existingExpense != null) {
            Expense updatedExpense = new Expense(
                    requestedId,
                    expenseUpdate.getDescription(),
                    expenseUpdate.getAmount(),
                    existingExpense.getCreatedAt(), // Preserve the original creation date
                    username
            );
            return expenseRepository.save(updatedExpense);
        }
        return null;
    }

}
