package intech.testTask.dao;

import intech.testTask.entity.UserEntity;

public interface UserDao extends BaseDao<UserEntity> {
	public UserEntity getById(Long id);
	public UserEntity getByLogin(String login);
	void removeFriend(String userLogin, String friendLogin);
	UserEntity getByEmail(String email);
	boolean removeById(Long id);
}
