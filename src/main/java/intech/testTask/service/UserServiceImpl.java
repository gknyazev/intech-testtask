package intech.testTask.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import intech.testTask.dao.UserDao;
import intech.testTask.dao.UserRoleDao;
import intech.testTask.dto.UserDto;
import intech.testTask.entity.UserEntity;
import intech.testTask.entity.UserRoleEntity;
import intech.testTask.util.ServiceResult;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public void registerNewUser(UserDto dto) {
		UserEntity entity = new UserEntity();
		entity.setfName(dto.getfName());
		entity.setmName(dto.getmName());
		entity.setlName(dto.getlName());
		entity.setLogin(dto.getLogin());
		entity.setEmail(dto.getEmail());
		entity.setPassword(BCrypt.hashpw(dto.getPassw1(), BCrypt.gensalt(12)));
		userDao.saveOrUpdate(entity);
		UserRoleEntity role = new UserRoleEntity();
		role.setRole("ROLE_USER");
		role.setUser(entity);
		userRoleDao.saveOrUpdate(role);
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public void updatePassword(String login, String newPassword) {
		UserEntity entity = userDao.getByLogin(login);
		entity.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(12)));
		userDao.saveOrUpdate(entity);
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public ServiceResult addFriend(String userlogin, String friendLogin) {
		if (userlogin.equals(friendLogin)) {
			return ServiceResult.error("cantaddyourself");
		}

		UserEntity user = userDao.getByLogin(userlogin);
		UserEntity friend = userDao.getByLogin(friendLogin);
		if (friend == null) {
			return ServiceResult.error("userisnotfound");
		}

		if (user.getFriends().contains(friend)) {
			return ServiceResult.error("friendexists");
		}
		user.getFriends().add(friend);
		return ServiceResult.ok();
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public String getCredentials(String userlogin) {
		UserEntity user = userDao.getByLogin(userlogin);
		return user.getfName() + " " + user.getlName();
	}

	@Override
	@Transactional
	public Set<UserEntity> getFriends(String userlogin) {
		UserEntity user = userDao.getByLogin(userlogin);
		return user.getFriends();
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public void removeFriend(String userLogin, String friendLogin) {
		userDao.removeFriend(userLogin, friendLogin);
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public Set<UserDto> getAllUsers() {
		Set<UserDto> users = new HashSet<UserDto>();
		userDao.getAll().forEach(entity -> users.add(new UserDto(entity)));
		return users;
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public Boolean userExists(String login) {
		return userDao.getByLogin(login) != null;
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public Boolean emailExists(String email) {
		return userDao.getByEmail(email) != null;
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public boolean deleteUser(Long userId) {
		return userDao.removeById(userId);
	}

	@Override
	@Transactional
	public boolean grantAdmin(Long id) {
		UserEntity user = userDao.getById(id);
		UserRoleEntity role = new UserRoleEntity();
		role.setRole("ROLE_ADMIN");
		role.setUser(user);
		userRoleDao.saveOrUpdate(role);
		return true;
	}

	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public boolean revokeAdmin(Long id) {
		UserEntity user = userDao.getById(id);
		List<UserRoleEntity> adminRoles = user.getUserRole().stream()
				.filter(entityRole -> "ROLE_ADMIN".equals(entityRole.getRole())).collect(Collectors.toList());
		for (UserRoleEntity adminRole : adminRoles) {
			userRoleDao.delete(adminRole);
		}
		return true;
	}

}
