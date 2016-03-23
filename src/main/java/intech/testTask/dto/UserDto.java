package intech.testTask.dto;

import intech.testTask.entity.UserEntity;

public class UserDto {
	private Long id;

	private String login;
	
	private String fName;
	
	private String mName;

	private String lName;

	private String email;

	private String passw1;

	private String passw2;
	
	private String role;
	
	public UserDto(UserEntity entity){
		this.login = entity.getLogin();
		this.id = entity.getId();
		this.fName = entity.getfName();
		this.mName = entity.getmName();
		this.lName = entity.getlName();
		this.passw1 = entity.getPassword();
		this.passw2 = entity.getPassword();
		this.email = entity.getEmail();
		if (entity.getUserRole().stream().filter(entityRole -> "ROLE_ADMIN".equals(entityRole.getRole())).count()>0){
			this.setRole("ROLE_ADMIN");
		} else {
			this.setRole("ROLE_USER");
		}
		
	}
	
	public UserDto(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassw1() {
		return passw1;
	}

	public void setPassw1(String passw1) {
		this.passw1 = passw1;
	}

	public String getPassw2() {
		return passw2;
	}

	public void setPassw2(String passw2) {
		this.passw2 = passw2;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
