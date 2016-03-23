package intech.testTask.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "USER_TABLE")
public class UserEntity {

	private Long id;

	private String login;
	
	private String fName;

	private String lName;
	
	private String mName;

	private String password;
	
	private String email;
	
	private Set<UserRoleEntity> userRole = new HashSet<UserRoleEntity>(0);
	
	private Set<UserEntity> friends = new HashSet<UserEntity>(0);

	public UserEntity() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	public Long getId() {
		return id;
	}

	@Column(name = "FIRST_NAME")
	public String getfName() {
		return fName;
	}

	@Column(name = "LAST_NAME")
	public String getlName() {
		return lName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Column(name = "LOGIN")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	@Cascade({CascadeType.REMOVE})
	public Set<UserRoleEntity> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRoleEntity> userRole) {
		this.userRole = userRole;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinTable(name = "user_friends", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "friend_id", 
					nullable = false, updatable = false) })
	public Set<UserEntity> getFriends() {
		return this.friends;
	}
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
//	@JoinTable(name = "user_friends", joinColumns = { 
//			@JoinColumn(name = "friend_id", nullable = false, updatable = false) }, 
//			inverseJoinColumns = { @JoinColumn(name = "user_id", 
//					nullable = false, updatable = false) })
//	public Set<UserEntity> getReverseFriends() {
//		return this.friends;
//	}
//	
//	public void setReverseFriends(Set<UserEntity> friends) {
//		this.friends = friends;
//	}

	public void setFriends(Set<UserEntity> friends) {
		this.friends = friends;
	}
	
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "MIDDLE_NAME")
	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}
