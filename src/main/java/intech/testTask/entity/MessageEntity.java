package intech.testTask.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE_TABLE")
public class MessageEntity {
	
	private Long id;
	private UserEntity sender;
	private UserEntity recipient;
	private String topic;
	private String text;
	private Date sentDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SENDER_ID", nullable = false)
	public UserEntity getSender() {
		return sender;
	}
	public void setSender(UserEntity sender) {
		this.sender = sender;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RECIPIENT_ID", nullable = false)
	public UserEntity getRecipient() {
		return recipient;
	}
	public void setRecipient(UserEntity recepient) {
		this.recipient = recepient;
	}
	
	@Column(name = "TEXT")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Column(name = "DATE")
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@Column(name = "TOPIC")
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MESSAGE_ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
