package javaproject_1.dto;

public class User {
	private String name;
	private String email;
	private String password;
	private long phone;
	private String gender;
	private int age;
	private double wallet;
	private double bill;
	
	public User(Double bill) {
		super();
		this.bill = bill;
	}
	
	public User(String name, String email, String password, long phone, String gender, int age, double wallet) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.gender = gender;
		this.age = age;
		this.wallet = wallet;
	}
	
	public User() {
		super();
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", gender="
				+ gender + ", age=" + age + ", wallet=" + wallet + ", bill=" + bill + "]";
	}
	
}
