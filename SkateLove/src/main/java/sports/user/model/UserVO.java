package sports.user.model;

public class UserVO {
	
	private String id;
	private String pw;
	private String name;
	private String age ;
	private String phoneno;
	private String gender;
	
	public UserVO() {}

	public UserVO(String id, String pw, String name, String age, String phoneno, String gender) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.phoneno = phoneno;
		this.gender = gender;
	}

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
