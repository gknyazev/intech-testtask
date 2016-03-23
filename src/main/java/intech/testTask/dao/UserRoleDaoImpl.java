package intech.testTask.dao;

import org.springframework.stereotype.Repository;

import intech.testTask.entity.UserRoleEntity;


@Repository
public class UserRoleDaoImpl extends BaseDaoImpl<UserRoleEntity> implements UserRoleDao {

	public UserRoleDaoImpl() {
		super(UserRoleEntity.class);
	}

}
