package tp.bar;

import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import tp.bar.Bar;

import java.util.*;

public class BarSteps {

    private Bar bar;
    private int peopleInBar;
    private List<String> customers = new ArrayList<>();
    private Map<String, Integer> orders = new HashMap<>();
    private int billTotal = 0;
    private boolean entryAllowed;
    private String payer;

    @Given("the bar {string} is a cocktail bar")
    public void the_bar_is_a_cocktail_bar(String barName) {
        bar = new Bar(barName, 10);
    }

    @Given("the bar has only {int} seats")
    public void the_bar_has_only_seats(Integer seats) {
        bar.setCapacity(seats);
    }

    @Given("there are already {int} people in the bar")
    public void there_are_already_people_in_the_bar(Integer count) {
        peopleInBar = count;
        for (int i = 1; i <= count; i++) {
            bar.enter("Customer" + i);
        }
    }

    @When("Mr Pignon and Mr Leblanc try to enter the bar")
    public void mr_pignon_and_mr_leblanc_try_to_enter_the_bar() {
        entryAllowed = bar.enter("Mr Pignon") && bar.enter("Mr Leblanc");
    }

    @Then("they are denied entry")
    public void they_are_denied_entry() {
        assertFalse(entryAllowed);
    }

    @When("Mr Pignon and Mr Leblanc enter the bar")
    public void mr_pignon_and_mr_leblanc_enter_the_bar() {
        assertTrue(bar.enter("Mr Pignon"));
        assertTrue(bar.enter("Mr Leblanc"));
    }

    @Then("the person behind them is denied entry")
    public void the_person_behind_them_is_denied_entry() {
        boolean result = bar.enter("Mr Derrière");
        assertFalse(result);
    }

    @When("Mr Pignon orders the cocktail of the month at {int}€")
    public void mr_pignon_orders_the_cocktail_of_the_month(int price) {
        orders.put("Mr Pignon", price);
        billTotal += price;
    }

    @When("Mr Leblanc orders the cocktail of the month at {int}€")
    public void mr_leblanc_orders_the_cocktail_of_the_month(int price) {
        orders.put("Mr Leblanc", price);
        billTotal += price;
    }

    @Then("Mr Leblanc pays the full bill")
    public void mr_leblanc_pays_the_full_bill() {
        payer = "Mr Leblanc";
    }

    @Then("the bill shows {int}€")
    public void the_bill_shows_the_correct_total_amount(int expectedTotal) {
        assertEquals(expectedTotal, billTotal); // 2 drinks at 10€ each
    }

    @Then("Mr Pignon is happy because he only had one drink")
    public void mr_pignon_is_happy_because_he_only_had_one_drink() {
        assertEquals(1, (int) orders.entrySet().stream()
            .filter(e -> e.getKey().equals("Mr Pignon"))
            .count());
    }

    @Then("Mr Pignon pays his own bill")
    public void mr_pignon_pays_his_own_bill() {
        assertTrue(orders.containsKey("Mr Pignon"));
        assertEquals(10, orders.get("Mr Pignon"));
    }

    @Then("Mr Leblanc decides to order 2 more cocktails of the month at {int}€ each")
    public void mr_leblanc_decides_to_order_more_cocktails(int price) {
        int current = orders.getOrDefault("Mr Leblanc", 0);
        orders.put("Mr Leblanc", current + price * 2);
        billTotal += price * 2;
    }

    @Then("Mr Leblanc pays his own bill")
    public void mr_leblanc_pays_his_own_bill() {
        assertTrue(orders.containsKey("Mr Leblanc"));
        assertEquals(30, orders.get("Mr Leblanc"));
    }

    @Then("Mr Pignon is sad because he knows that more than one drink will ruin the night for him")
    public void mr_pignon_is_sad_because_more_than_one_drink_is_bad_for_him() {
        int drinkCount = orders.getOrDefault("Mr Pignon", 0) / 10;
        assertTrue(drinkCount <= 1, "Mr Pignon had more than one drink and will have a bad night.");
    }
}