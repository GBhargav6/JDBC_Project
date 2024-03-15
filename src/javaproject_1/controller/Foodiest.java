package javaproject_1.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javaproject_1.dao.FoodCrud;
import javaproject_1.dto.User;



public class Foodiest {

	public static void main(String[] args) throws SQLException, IOException {
		Scanner sc=new Scanner(System.in);
		FoodCrud food = new FoodCrud();
		boolean choice1 = true;
		int i = 1;
		int[] count = new int[20];

		do {
			int choose = 0;
			if (i == 1) {
				System.out.println("1. Register \n2. Login \n3. Exit");
				choose = sc.nextInt();
			}
			switch (choose) {
			case 1: {
				System.out.println("Enter your name : ");
				String name = sc.next();
				System.out.println("Enter your Email id : ");
				String email = sc.next();
				System.out.println("Enter password : ");
				String pwd = sc.next();
				System.out.println("Enter mobile number : ");
				long phone = sc.nextLong();
				System.out.println("Enter your gender : ");
				String gender = sc.next();
				System.out.println("Enter your age : ");
				int age = sc.nextInt();
				System.out.println("Amount you want to add to walllet(Minimum of 100)");
				double wallet = sc.nextDouble();

				User user = new User(name, email, pwd, phone, gender, age, wallet);
				food.register(user);
			}
				break;

			case 2: {
				User user = new User();
				System.out.println("Enter your Email id : ");
				user.setEmail(sc.next());
				System.out.println("Enter password : ");
				user.setPassword(sc.next());

				if (food.login(user)) {
					i = 2;
					System.out.println("Hey dear..! \nGreeting from Food court \nSo, How you want start ?");
					boolean choose2 = true;
					do {
						System.out.println(
								"\n1.Breakfast \n2.Veg Starters \n3.Non-Veg Starters \n4.chineese \n5.Biryani \n6.roti \n7.Dessert \n8.All \n9.Bill");
						int choice2 = sc.nextInt();

						switch (choice2) {
						case 1: {
							food.foodItems("breakfast");
						}
							break;
						case 2: {
							food.foodItems("veg_starters");
						}
							break;
						case 3: {
							food.foodItems("non_veg_starters");
						}
							break;
						case 4: {
							food.foodItems("chinese food");
						}
							break;
						case 5: {
							food.foodItems("biryani");
						}
							break;
						case 6: {
							food.foodItems("roti");

						}
							break;
						case 7: {
							food.foodItems("dessert");
						}
							break;
						case 8: {
							food.foodItems("all");
						}
							break;
						case 9: {
							choose2 = false;
						}
							break;
						}
						if (choose2) {
							boolean inBfChoice = true;
							do {
								System.out.println("Enter your choice... \nEnter 404 to back");
								int inBfChoose = sc.nextInt();
								inBfChoose--;
								if (inBfChoose <= 20) {
									count[inBfChoose]++;
								} else {
									inBfChoice = false;
								}

							} while (inBfChoice);
						}
					} while (choose2);
					food.billToPay(count,user.getEmail());
					
					System.out.println("Your Payment is done..!");
					System.out.println("==================================================\nFood is symbolic of love when words are inadequate.\n==================================================");
					System.out.println("Thank you for your visit");
					System.out.println("	Visit again...!");
				} else {
					System.out.println("Enter valid email and password");
				}
			}
				break;
			case 3: {
				choice1 = false;
			}
				break;
			}

		} while (choice1);

	}

}
