package com.creditsuisse.shoppinglist;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;


public class CheckoutTest {

    @Test
    public void correctPriceReturnedForTwoApples() {
        final Checkout checkout = new Checkout();
        final BigDecimal expected = new BigDecimal("0.70");
        final BigDecimal actual = checkout.calculateBasketValue(Arrays.asList("Apple", "Apple"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void correctPriceReturnedForThreeBananasAndAnApple() {
        final Checkout checkout = new Checkout();
        final BigDecimal expected = new BigDecimal("0.95");
        final BigDecimal actual = checkout.calculateBasketValue(Arrays.asList("Banana", "Apple", "Banana", "Banana"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void correctPriceReturnedForThreeMelonsAnAppleAndTwoBananas() {
        final Checkout checkout = new Checkout();
        final BigDecimal expected = new BigDecimal("1.75");
        final BigDecimal actual = checkout.calculateBasketValue(Arrays.asList("Melon", "Apple", "Banana", "Melon", "Melon", "Banana"));
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void correctPriceReturnedForSevenLimes() {
        final Checkout checkout = new Checkout();
        final BigDecimal expected = new BigDecimal("0.75");
        final BigDecimal actual = checkout.calculateBasketValue(Arrays.asList("Lime", "Lime", "Lime", "Lime", "Lime", "Lime", "Lime"));
        Assert.assertEquals(expected, actual);
    }

}
