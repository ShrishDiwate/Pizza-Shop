package com.dkte.pizzashop.tester;

import java.sql.SQLException;

import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Pizza;

public class PizzaTest {

	public static void main(String[] args) throws Exception {
		Pizza pizza = new Pizza();
		try(PizzaDao pizzaDao = new PizzaDao()){
			pizzaDao.getAllPizza();
		}

	}

}
