package employee;

public class Person {
	private String name = new String();
	private String birth = new String();
	private int age = 0;
	private String gender = new String();
	private String address = new String();
	private String ethnic = new String();
	public Person(String name, String birth, int age, String gender, String address, String ethnic) {
		super();
		this.name = name;
		this.birth = birth;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.ethnic = ethnic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEthnic() {
		return ethnic;
	}
	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}
	
}
