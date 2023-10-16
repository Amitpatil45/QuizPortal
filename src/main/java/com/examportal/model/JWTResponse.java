package com.examportal.model;

public class JWTResponse {
	private String token;
	private String userName;
	private String fName;
	private String lname;
	private String email;
	private String phone;
	private String profile;

	public JWTResponse() {
		super();
	}

	public JWTResponse(String token, String userName, String fName, String lname, String email, String phone,
			String profile) {
		super();
		this.token = token;
		this.userName = userName;
		this.fName = fName;
		this.lname = lname;
		this.email = email;
		this.phone = phone;
		this.profile = profile;

	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

}
