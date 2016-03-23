package intech.testTask.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import intech.testTask.dao.MessageDao;
import intech.testTask.dao.UserDao;
import intech.testTask.dto.MessageDto;
import intech.testTask.entity.MessageEntity;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public MessageDto sendMessage(MessageDto dto){
		MessageEntity entity = new MessageEntity();
		entity.setSender(userDao.getByLogin(dto.getSender()));
		entity.setRecipient(userDao.getByLogin(dto.getRecipient()));
		entity.setSentDate(new Date());
		entity.setTopic(HtmlUtils.htmlEscape(dto.getTopic()));
		entity.setText(HtmlUtils.htmlEscape(dto.getText()));
		messageDao.saveOrUpdate(entity);
		return new MessageDto(entity);
	}
	
	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public Set<MessageDto> getMessages(String recipient){
		Set<MessageDto> messages = new HashSet<MessageDto>();
		messageDao.getByRecipient(userDao.getByLogin(recipient)).forEach(entity -> messages.add(new MessageDto(entity)));
		return messages;
	}
	
	
	/**
	 *  {@inheritDoc} 
	 */
	@Override
	@Transactional
	public MessageDto getMessageById(Long id, String login) {
		MessageEntity entity = messageDao.getById(id);
		//Checking recipient here to prevent messages hijacking by modifying request parameters.
		if (entity.getRecipient().getLogin().equals(login)){
			return new MessageDto(entity);
		} else {
			throw new SecurityException("Trying to get other user message!");
		}
	}
}
