package dev.orlabrador.expense_tracker.expense;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, Long>, PagingAndSortingRepository<Expense, Long> {

    Boolean existsByIdAndUsername(Long id, String username);

    Expense findByIdAndUsername(Long id, String username);
    
    Page<Expense> findByUsername(String username, PageRequest pageRequest);
    
}
