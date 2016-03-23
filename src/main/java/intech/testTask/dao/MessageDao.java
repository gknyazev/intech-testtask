package intech.testTask.dao;

import java.util.List;

import intech.testTask.entity.MessageEntity;
import intech.testTask.entity.UserEntity;

public interface MessageDao extends BaseDao<MessageEntity> {

	List<MessageEntity> getByRecipient(UserEntity login);
}
