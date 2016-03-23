package intech.testTask.dto;

import java.util.Date;

import intech.testTask.entity.MessageEntity;

public class MessageDto {
	private String sender;
	private String recipient;
	private String topic;
	private String text;
	private Long id;
	private Date sentDate;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessageDto() {

	}

	public MessageDto(MessageEntity entity) {
		this.sender = entity.getSender().getLogin();
		this.recipient = entity.getRecipient().getLogin();
		this.topic = entity.getTopic();
		this.text = entity.getText();
		this.sentDate = entity.getSentDate();
		this.id = entity.getId();
	}

}
