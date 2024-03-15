package javaproject_1.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

import javaproject_1.dto.User;

public class FoodCrud {
	public void register(User user) throws SQLException, IOException {
		DriverManager.registerDriver(new Driver());//SQLException
		FileInputStream stream=new FileInputStream("foodConfiguration.properties");//FileNotFoundException
		Properties pro=new Properties();
		pro.load(stream);//IOException
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?createDatabaseIfNotExist=true", pro);
		Statement sta = con.createStatement();
		sta.execute("create table if not exists users(name varchar(45),email varchar(45) primary key ,password varchar(30) not null,phone long,gender varchar(10),age int,wallet double)");
		PreparedStatement ps = con.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
		ps.setString(1,user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3,user.getPassword());
		ps.setLong(4, user.getPhone());
		ps.setString(5,user.getGender());
		ps.setInt(6, user.getAge());
		ps.setDouble(7, user.getWallet());
		ps.close();
		sta.close();
		con.close();
		System.out.println("Inserted..");
	}
	
	public boolean login(User user) {
		try {
			DriverManager.registerDriver(new Driver());
			FileInputStream stream=new FileInputStream("foodConfiguration.properties");
			Properties pro=new Properties();
			pro.load(stream);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", pro);

			PreparedStatement ps = con.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ResultSet res = ps.executeQuery();
			if (res.next()) {
				ps.close();
				con.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	public void foodItems(String choice) {

		try {
			DriverManager.registerDriver(new Driver());

			FileInputStream stream = new FileInputStream("foodconfigure.properties");
			Properties properties = new Properties();
			properties.load(stream);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDatabase", properties);

			PreparedStatement ps;
			if (choice.equalsIgnoreCase("all")) {
				ps = con.prepareStatement("select * from fooditems");
			} else {
				ps = con.prepareStatement("select * from fooditems where type=?");
				ps.setString(1, choice);
			}

			ResultSet res = ps.executeQuery();
			while (res.next()) {
				System.out.println(res.getInt(1) + ". " + res.getString(2) + " - " + res.getDouble(3));
			}
			ps.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	double[] payment = new double[20];

	public void billToPay(int[] count, String email) {
		try {
			DriverManager.registerDriver(new Driver());

			FileInputStream stream = new FileInputStream("foodconfigure.properties");
			Properties properties = new Properties();
			properties.load(stream);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDatabase", properties);

			PreparedStatement ps = con.prepareStatement("select * from fooditems");

			PreparedStatement ps1 = con.prepareStatement("select wallet from user where email = ?");
			ps1.setString(1, email);
			ResultSet res = ps.executeQuery();
			ResultSet res1 = ps1.executeQuery();
			int i = 0;
			User user = new User();
			for (int j = 0; j < payment.length; j++) {
				if (res.next()) {
					payment[i++] = res.getDouble(3);
				}
				if (count[j] != 0) {
					System.out.println(res.getString(2) + " " + count[j] + " = " + (payment[j] * count[j]));
				}
			}
			while (res1.next()) {
				user.setWallet(res1.getDouble(1));
			}
			ps.close();
			con.close();

			for (int j = 0; j < payment.length; j++) {
				user.setBill(user.getBill() + (payment[j] * count[j]));
			}
			Scanner scan = new Scanner(System.in);
			System.out.println("your total bill is = " + user.getBill());
			user.setWallet(user.getWallet() - user.getBill());
			System.out.println(user.getBill());
			System.out.println(user.getWallet());
			while (user.getWallet() < 0) {
				System.out.println("You wallet balance is " + user.getWallet() + " . So, kindly recharge your wallet.");
				System.out.println("Enter amount to add to your wallet");
				user.setWallet(user.getWallet() + scan.nextDouble());
			}
			System.out.println("Your remaining wallet balance is : " + user.getWallet());
			wallet(email, user.getWallet());
			scan.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void wallet(String email, Double wallet) {
		try {
			DriverManager.registerDriver(new Driver());

			FileInputStream stream = new FileInputStream("foodconfigure.properties");
			Properties properties = new Properties();
			properties.load(stream);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDatabase", properties);

			CallableStatement cl = con.prepareCall("call wallet(?,?)");
			cl.setString(1, email);
			cl.setDouble(2, wallet);
			cl.execute();
			
			cl.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
