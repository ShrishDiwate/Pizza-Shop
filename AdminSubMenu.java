package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Admin;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.ForAllOrders;
import com.dkte.pizzashop.entities.Pizza;

public class AdminSubMenu {
	
	public static int menu(Scanner sc) {
		System.out.println("****************************************************************");
		System.out.println("0.Logout");
		System.out.println("1.Add new pizza");
		System.out.println("2.Update price of the pizza");
		System.out.println("3.Delete pizza");
		System.out.println("4.Display Pizza menu");
		System.out.println("5.Display all customers");
		System.out.println("6.Display all orders");
		System.out.println("7.Calculate total profit");
		System.out.println("****************************************************************");
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		return choice;
	}
	
	private static void addPizza(Scanner sc) {
		Pizza pizza = new Pizza();
		pizza.accept(sc);
		try(PizzaDao pizzaDao = new PizzaDao()){
			pizzaDao.insertPizza(pizza);
			System.out.println("New pizza added successfully");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void updatePrice(Scanner sc) {
		try(PizzaDao pizzaDao = new PizzaDao()){
			System.out.println("Enter the menu id of the pizza");
			int mid=sc.nextInt();
			System.out.println("Enter the new price of the pizza");
			double price = sc.nextDouble();
			pizzaDao.updatePricePizza(price,mid);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void deletePizza(Scanner sc) {
		try(PizzaDao pizzaDao = new PizzaDao()){
			System.out.println("Enter the menu id to delete the pizza in the menu");
			int mid=sc.nextInt();
			pizzaDao.deleteMenu(mid);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void displayPizza() {
		try(PizzaDao pizzaDao = new PizzaDao()) {
			List<Pizza> pizzalist = pizzaDao.getAllPizza();
			pizzalist.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void displayCustomer() {
		try(CustomerDao customerDao = new CustomerDao()) {
			List<Customer> customerlist = customerDao.getAllCustomer();
			customerlist.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void getAllOrders() {
		try (OrderDao orderDao = new OrderDao()) {
			List<ForAllOrders> pizzaList = orderDao.getOverAllOrders();
			pizzaList.forEach(p -> System.out.println(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void calculateProfit(Scanner sc) {
		System.out.println("Enter the total revenue");
		double totalrevenue = sc.nextDouble();
		System.out.println("Enter the Ingredient costs");
		double ingredientCost=sc.nextDouble();
		System.out.println("Enter the labor costs");
		double laborCost=sc.nextDouble();
		System.out.println("Enter the rent");
		double rent=sc.nextDouble();
		System.out.println("Enter the utilities cost");
		double utilities=sc.nextDouble();
		System.out.println("Enter the cost of other overheads");
		double overheads=sc.nextDouble();
		double TotalCosts= ingredientCost+laborCost+rent+utilities+overheads;
		double profit = totalrevenue-TotalCosts;
		System.out.println("Total profit of the pizza shop :"+profit);
	}
	
	public static void adminSubmenu(Scanner sc , Admin admin) {
		int choice;
		while((choice = menu(sc))!=0) {
			switch(choice) {
			case 1:
				addPizza(sc);
				break;
			case 2:
				updatePrice(sc);
				System.out.println("Price successfully updated");
				break;
			case 3:
				deletePizza(sc);
				System.out.println("Pizza successfully deleted");
				break;
			case 4:
				displayPizza();
				break;
			case 5:
				displayCustomer();
				break;
			case 6:
				getAllOrders();
				break;
			case 7:
				calculateProfit(sc);
				break;
				
			}
		}
	}
}
