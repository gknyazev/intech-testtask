package intech.testTask.dao;

import intech.testTask.entity.UserEntity;

public interface UserDao extends BaseDao<UserEntity> {
	public UserEntity getById(Long id);
	public UserEntity getByLogin(String login);
	void removeFriend(String userLogin, String friendLogin);
	UserEntity getByEmail(String email);
	/**
	 * Removes user by id, removing linked objects first
	 * @param {@link Long} id
	 * @return
	 */
	boolean removeById(Long id);
}
