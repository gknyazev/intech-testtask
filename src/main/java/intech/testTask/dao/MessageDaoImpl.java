package intech.testTask.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import intech.testTask.entity.MessageEntity;
import intech.testTask.entity.UserEntity;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<MessageEntity> implements MessageDao {
	public MessageDaoImpl() {
		super(MessageEntity.class);
	}

	@Override
	public List<MessageEntity> getByRecipient(UserEntity login) {
		return (List<MessageEntity>) sessionFactory.getCurrentSession().createQuery("from MessageEntity where recipient= :recipient").setParameter("recipient", login).list();
	}
}
