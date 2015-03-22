package com.avab.meals;

public class KitchenService {

	public Meal prepareMeal(String mealName, boolean vegetarian) {
		return new Meal(vegetarian);
	}
}
