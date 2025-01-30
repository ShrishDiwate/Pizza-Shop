import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.AdminDao;
import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Admin;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.main.AdminSubMenu;
import com.dkte.pizzashop.main.SubMenu;

public class MainMenu {

	public static int menu(Scanner sc) {
		System.out.println("****************************************************************");
		System.out.println("0.Exit");
		System.out.println("1.Login");
		System.out.println("2.Register");
		System.out.println("3.Admin Login");		
		System.out.println("****************************************************************");
		System.out.println("Enter the choice");
		int choice=sc.nextInt();
		return choice;
	}
	
	private static Customer loginCustomer(Scanner sc)  {
		try(CustomerDao customerDao = new CustomerDao()) {
			System.out.println("Enter the email");
			String email =sc.next();
			System.out.println("Enter the password");
			String password = sc.next();
			return customerDao.getCustomer(email, password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void registerCustomer(Scanner sc)  {
		Customer customer =new Customer();
		customer.accept(sc);
		try(CustomerDao customerDao = new CustomerDao()) {
			customerDao.insertCustomer(customer);
			System.out.println("Registration successful.");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private static Admin loginAdmin(Scanner sc) {
		try(AdminDao adminDao = new AdminDao()) {
			System.out.println("Enter the admin emailID");
			String email = sc.next();
			System.out.println("Enter the password");
			String password = sc.next();
			return adminDao.getAdmin(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int choice;
		while((choice = menu(sc))!=0) {
			switch (choice) {
			
			case 1:
				Customer customer = loginCustomer(sc);
				if(customer != null) 
					SubMenu.submenu(sc, customer);
				else
						System.out.println("Invalid Credentials");
					
				
				break;
				
			case 2:
				registerCustomer(sc);
				break;
				
			case 3:
				Admin admin =loginAdmin(sc);
				if(admin!=null) {
					AdminSubMenu.adminSubmenu(sc, admin);
				}else {
					System.out.println("Invalid credentials , you not a admin");
				}
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		}
		System.out.println("Thanks for the using Application.....");

	}

}
