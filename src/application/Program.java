package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entities.enums.OrderStatus;

public class Program {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);
		Date today = new Date();
		System.out.println(sdf.format(today));
		System.out.println();
		
		System.out.println("--CLIENT DATA--");
		System.out.print("Name: ");
		String cliName = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Birth date: ");
		Date birthDate = sdf.parse(sc.next());		
		Client client = new Client(cliName, email, birthDate);
		System.out.println();
		
		System.out.println("--ORDER DATA--");
		System.out.print("Status: ");
		OrderStatus status = null;
		String statusSC;
		try {
			statusSC = sc.next();
			boolean ok;
			do {
				if (statusSC != "DELIVERED" || statusSC != "SHIPPED" || statusSC != "PROCESSING"
						|| statusSC != "PENDING_PAYMENT") {
					System.out.println("PENDING_PAYMENT / PROCESSING / SHIPPED / DELIVERED");
					System.out.print("Status: ");

					statusSC = sc.next();
					System.out.print(statusSC);
					ok = false;
				}
				else {
					status = OrderStatus.valueOf(statusSC);
					ok = true;
				}
			}
			while (!ok);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			status = null;
		}
		System.out.println();

		Order order = new Order(today, status, client);
		
		System.out.print("Number of items to include: ");
		int n = sc.nextInt();

		OrderItem items = new OrderItem();
		Product product;
		for(int i=0; i<n; i++) {
			System.out.printf("Enter #%d item data:%n",(i+1));
			System.out.print("Product name: ");
			String prodName = sc.next();
			System.out.print("Product price: ");
			Double prodPrice = sc.nextDouble();
			product = new Product(prodName, prodPrice);
			
			System.out.print("Quantity: ");
			int quantity = sc.nextInt();
			items = new OrderItem(quantity, prodPrice, product);
			order.addItem(items);
		}
		System.out.println();
		System.out.println("--ORDER SUMMARY--");
		System.out.println(order.toString());
		sc.close();
	}

}
