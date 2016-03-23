package intech.testTask.dao;

import org.springframework.stereotype.Repository;

import intech.testTask.entity.UserEntity;


@Repository
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {

	public UserDaoImpl() {
		super(UserEntity.class);
	}

	@Override
	public UserEntity getById(Long id) {
		return (UserEntity) sessionFactory.getCurrentSession().createQuery("from UserEntity where id= :userid").setParameter("userid", id).uniqueResult();
	}
	
	@Override
	public boolean removeById(Long id) {
		UserEntity entity = (UserEntity) sessionFactory.getCurrentSession().createQuery("from UserEntity where id= :userid").setParameter("userid", id).uniqueResult();
		if (entity !=null){
			sessionFactory.getCurrentSession().createSQLQuery("delete from user_friends where user_id=:userId or friend_id=:userId").setParameter("userId", id).executeUpdate();
			sessionFactory.getCurrentSession().createSQLQuery("delete from message_table where recipient_id=:userId or sender_id=:userId").setParameter("userId", id).executeUpdate();
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().delete(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserEntity getByLogin(String login) {
		return (UserEntity) sessionFactory.getCurrentSession().createQuery("from UserEntity where login= :userlogin").setParameter("userlogin", login).uniqueResult();
	}
	
	@Override
	public UserEntity getByEmail(String email) {
		return (UserEntity) sessionFactory.getCurrentSession().createQuery("from UserEntity where email= :userEmail").setParameter("userEmail", email).uniqueResult();
	}
	
	@Override
	public void removeFriend(String userLogin, String friendLogin) {
		UserEntity user = (UserEntity) sessionFactory.getCurrentSession().createQuery("from UserEntity where login= :userlogin").setParameter("userlogin", userLogin).uniqueResult();
		user.getFriends().removeIf(entity -> entity.getLogin().equals(friendLogin));
		sessionFactory.getCurrentSession().merge(user);
	}


}
