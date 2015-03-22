package com.avab.meals;

public class SimpleWaiterImpl implements Waiter {
	private final KitchenService kitchenService;

	public SimpleWaiterImpl(final KitchenService kitchenService) {
		this.kitchenService = kitchenService;
	}

	@Override
	public Meal bringOrderedMeal(String mealName, boolean vegetarian) {
			Meal preparedMeal = kitchenService.prepareMeal(mealName,vegetarian);
			if (preparedMeal.isVegetarian() != vegetarian) {
					throw new IllegalStateException("Wrong meal");
			}
			return preparedMeal;
	}

	public KitchenService getKitchenService() {
		return kitchenService;
	}
}
