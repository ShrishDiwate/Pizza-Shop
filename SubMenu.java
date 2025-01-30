package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class SubMenu {
	
	public static int menu(Scanner sc) {
		System.out.println("****************************************************************");
		System.out.println("0.Logout");
		System.out.println("1.Menu");
		System.out.println("2.Order a Pizza (Only enter the id of pizza to place order)");
		System.out.println("3.Order History ");
		System.out.println("****************************************************************");
		System.out.println("Enter the choice");
		int choice = sc.nextInt();
		return choice;
	}
	
	private static void displayPizza() {
		try(PizzaDao pizzaDao = new PizzaDao()) {
			List<Pizza> pizzalist = pizzaDao.getAllPizza();
			pizzalist.forEach(p -> System.out.println(p));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void orderPizza(Scanner sc ,int cid) {
		try(OrderDao orderDao = new OrderDao()){
			System.out.println("Enter the menu id :");
			int mid=sc.nextInt();
			orderDao.placeOrder(cid, mid);
			System.out.println("Order successfully placed");
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	private static void getOrders(int cid) {
		try (OrderDao orderDao = new OrderDao()) {
			List<Pizza> pizzaList = orderDao.getAllOrder(cid);
			pizzaList.forEach(p -> System.out.println(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	public static void submenu(Scanner sc , Customer customer) {
		int choice ;
		System.out.println("Welcome -"+customer.getName());
		while((choice = menu(sc))!=0) {
			switch (choice) {
			case 1:
				displayPizza();
				break;
				
			case 2:
				orderPizza(sc,customer.getCid());
				break;
				
			case 3 :
				getOrders(customer.getCid());
				break;
				
			default:
				System.out.println("Invalid choice");

			}
		}
		System.out.println("Logout successfully");
		
		
	}
	
	
}
