package intech.testTask.service;

import java.util.Set;

import intech.testTask.dto.MessageDto;

public interface MessageService {

	/**
	 * Sends message from one user to another
	 * @param {@link MessageDto} dto
	 * @return
	 */
	MessageDto sendMessage(MessageDto dto);

	/**
	 * Get list of messages for a specified user
	 * @param {@link String} recipient - a login of user.
	 * @return {@link Set} of {@link MessageDto}
	 */
	Set<MessageDto> getMessages(String recipient);

	/**
	 * Checking recipient here to prevent messages hijacking by modifying request parameters.
	 */
	MessageDto getMessageById(Long id, String login);

}