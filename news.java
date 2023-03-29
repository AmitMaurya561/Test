package com.newspapersubscription;

import java.util.*;
public class NewspaperSubscription {
private static final Map<String, List<Double>> newspaperPrices = new HashMap<>();
    
    static {
        newspaperPrices.put("TOI", Arrays.asList(3.0, 3.0, 3.0, 3.0, 3.0, 5.0, 6.0));
        newspaperPrices.put("Hindu", Arrays.asList(2.5, 2.5, 2.5, 2.5, 2.5, 4.0, 4.0));
        newspaperPrices.put("ET", Arrays.asList(4.0, 4.0, 4.0, 4.0, 4.0, 4.0, 10.0));
        newspaperPrices.put("BM", Arrays.asList(1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5));
        newspaperPrices.put("HT", Arrays.asList(2.0, 2.0, 2.0, 2.0, 2.0, 4.0, 4.0));
    }
    
    public static void main(String[] args) {
        double weeklyBudget = 40.0;
        List<List<String>> subscriptionCombinations = getSubscriptionCombinations(weeklyBudget);
        for (List<String> combination : subscriptionCombinations) {
            System.out.println(combination);
        }
    }
    
    public static List<List<String>> getSubscriptionCombinations(double weeklyBudget) {
        List<List<String>> result = new ArrayList<>();
        getSubscriptionCombinationsHelper(new ArrayList<>(), 0.0, weeklyBudget, result);
        return result;
    }
    
    private static void getSubscriptionCombinationsHelper(List<String> currentCombination, 
                                                           double currentPrice, 
                                                           double weeklyBudget,
                                                           List<List<String>> result) {
        if (currentPrice > weeklyBudget) {
            return;
        }
        if (currentPrice == weeklyBudget) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (String newspaper : newspaperPrices.keySet()) {
            List<Double> prices = newspaperPrices.get(newspaper);
            for (int i = 0; i < prices.size(); i++) {
                double price = prices.get(i);
                if (currentPrice + price <= weeklyBudget) {
                    currentCombination.add(newspaper);
                    getSubscriptionCombinationsHelper(currentCombination, currentPrice + price, weeklyBudget, result);
                    currentCombination.remove(currentCombination.size() - 1);
                }
            }
        }
    }
}
