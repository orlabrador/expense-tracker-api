package dev.orlabrador.expense_tracker;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import static org.assertj.core.api.Assertions.assertThat;

import dev.orlabrador.expense_tracker.expense.Expense;

@JsonTest
public class ExpenseTrackerJsonTest {

    @Autowired
    private JacksonTester<Expense> json;

    @Autowired
    private JacksonTester<Expense[]> jsonList;

    private ArrayList<Expense> expenses = new ArrayList<>();

    @BeforeEach
    void SetUp() {
        Expense expense1 = new Expense(99L, "cosas varias", 123.45, LocalDate.now());
        Expense expense2 = new Expense(100L, "cosas varias2", 1.00, LocalDate.now());

        expenses.clear();
        expenses.add(expense1);
        expenses.add(expense2);
    }

    @Test
    void expenseSerializationTest() throws IOException {
        Expense expense = expenses.get(0); // Get the first expense from the list

        // Serialize the Expense object to JSON
        JsonContent<Expense> jsonContent = json.write(expense);

        // Verify the JSON output
        assertThat(jsonContent).hasJsonPathNumberValue("@.id");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.id").isEqualTo(99);
        assertThat(jsonContent).hasJsonPathStringValue("@.expenseDescription");
        assertThat(jsonContent).extractingJsonPathStringValue("@.expenseDescription").isEqualTo("cosas varias");
        assertThat(jsonContent).hasJsonPathNumberValue("@.expenseAmount");
        assertThat(jsonContent).extractingJsonPathNumberValue("@.expenseAmount").isEqualTo(123.45);
        assertThat(jsonContent).hasJsonPathStringValue("@.createdAt");
        assertThat(jsonContent).extractingJsonPathStringValue("@.createdAt").isEqualTo(LocalDate.now().toString());
    }

    @Test
    void expenseDeserializationTest() throws Exception {
        // JSON representation of an Expense object
        String jsonContent = """
            {
                "id": 99,
                "expenseDescription": "cosas varias",
                "expenseAmount": 123.45,
                "createdAt": "2023-10-01"
            }
            """;

        // Deserialize the JSON to an Expense object
        Expense expense = json.parse(jsonContent).getObject();

        // Verify the deserialized object
        assertThat(expense.getId()).isEqualTo(99L);
        assertThat(expense.getExpenseDescription()).isEqualTo("cosas varias");
        assertThat(expense.getExpenseAmount()).isEqualTo(123.45);
        assertThat(expense.getCreatedAt()).isEqualTo(LocalDate.of(2023, 10, 1));
    }

    @Test
    void expenseListSerializationTest() throws Exception {
        // Serialize the list of expenses to JSON
        JsonContent<Expense[]> jsonContent = jsonList.write(expenses.toArray(new Expense[0]));

        // Verify the JSON output
        assertThat(jsonContent).extractingJsonPathArrayValue("@").hasSize(2); // Verify the number of items in the array
        assertThat(jsonContent).extractingJsonPathNumberValue("@[0].id").isEqualTo(99);
        assertThat(jsonContent).extractingJsonPathNumberValue("@[1].id").isEqualTo(100);
    }
}
