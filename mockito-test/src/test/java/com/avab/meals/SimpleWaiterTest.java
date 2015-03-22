package com.avab.meals;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.hamcrest.*;

public class SimpleWaiterTest {

	SimpleWaiterImpl objectUnderTest;
	
	KitchenService kitchenServiceMock = mock(KitchenService.class);
	
	@Before
	public void setUp() {
		objectUnderTest = new SimpleWaiterImpl(kitchenServiceMock);
	}
	
	@Test
	public void shouldReturnAFirstCourseMealWhenOrderedHamburger() throws Exception {
		// given
		String mealName = "Hamburger";
		boolean vegetarian = false;
		Meal meal = new Meal(vegetarian);
		
		Mockito.when(kitchenServiceMock.prepareMeal(mealName, vegetarian)).thenReturn(meal);
		
		// when
		Meal orderedMeal = objectUnderTest.bringOrderedMeal(mealName, vegetarian);
		
		// then 
		assertThat(orderedMeal, is(meal));
		Mockito.verify(kitchenServiceMock).prepareMeal(mealName, vegetarian);

	}
	
	@Test
	public void shouldThrowAnExceptionWhenAMealOfImproperTypeIsOrdered() throws Exception {
		// given
		String mealName = "Hamburger";
		boolean vegetarian = false;
		Meal meal = new Meal(vegetarian);
		
		Mockito.when(kitchenServiceMock.prepareMeal(mealName, vegetarian)).thenReturn(meal);
		
		// when
		Meal orderedMeal = objectUnderTest.bringOrderedMeal(mealName, vegetarian);
		
		// then		
		Mockito.verify(kitchenServiceMock).prepareMeal(mealName, vegetarian);
		
	}
	
	
	@Test
	public void shouldReturnAVeggieMeal() throws Exception {
		// given
		String mealName = "Vegetarian meal";
		boolean VEGETARIAN_MEAL = true;
		Mockito.when(kitchenServiceMock.prepareMeal(Matchers.startsWith("Vegetarian"), Matchers.eq(VEGETARIAN_MEAL))).thenReturn(new Meal(true));
		
		//when
		Meal meal = objectUnderTest.bringOrderedMeal(mealName, VEGETARIAN_MEAL);
		
		//then
		
		
		
		assertThat(meal.isVegetarian(), is(true));
		Mockito.verify(kitchenServiceMock).prepareMeal(mealName, VEGETARIAN_MEAL);
		//Mockito.verify(kitchenServiceMock).prepareMeal(AdditionalMatchers.and(Mockito.contains("Ham"),Matchers.endsWith("Hamburger")), Matchers.eq(VEGETARIAN_MEAL));
		Mockito.verify(kitchenServiceMock).prepareMeal(Matchers.matches("V.*n H.*r"),Matchers.anyBoolean());		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
