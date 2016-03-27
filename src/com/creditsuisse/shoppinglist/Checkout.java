package com.creditsuisse.shoppinglist;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


class Checkout {

    private BigDecimal getItemPrice(String item) {
        switch (item) {
            case "Apple": return new BigDecimal("0.35");
            case "Banana": return new BigDecimal("0.20");
            case "Lime": return new BigDecimal("0.15");
            case "Melon": return new BigDecimal("0.50");
            default: return BigDecimal.ZERO;
        }
    }

    private List<String> applyOffer(final List<String> items, final Predicate<String> filterEligable, final Function<Integer, Integer> getCountAfterOffer) {
        final Map<Boolean, List<String>> eligableIneligable = items.stream().collect(Collectors.partitioningBy(filterEligable));
        final List<String> eligable = eligableIneligable.get(true);
        final List<String> ineligable = eligableIneligable.get(false);
        final long itemCountAfterOffer = getCountAfterOffer.apply(eligable.size());
        final Stream<String> itemsAfterOffer = eligable.stream().limit(itemCountAfterOffer);
        return Stream.concat(itemsAfterOffer, ineligable.stream()).collect(Collectors.toList());
    }

    private List<String> buyOneGetOneFree(final List<String> items) {
        return applyOffer(items, i -> i.equals("Melon"), i -> i / 2 + i % 2);
    }

    private List<String> threeForThePriceOfTwo(final List<String> items) {
        return applyOffer(items, i -> i.equals("Lime"), i -> ((i / 3) * 2) + (i % 3));
    }


    BigDecimal calculateBasketValue(final List<String> items) {
        final List<String> itemsAfterBogof = buyOneGetOneFree(items);
        final List<String> itemsAfter3for2 = threeForThePriceOfTwo(itemsAfterBogof);
        return itemsAfter3for2.stream().map(this::getItemPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
