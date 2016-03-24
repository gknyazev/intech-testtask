package intech.testTask.service;

import java.util.Set;

import intech.testTask.dto.UserDto;
import intech.testTask.entity.UserEntity;
import intech.testTask.util.ServiceResult;

public interface UserService {

	/**
	 * Register new user
	 * @param dto
	 */
	void registerNewUser(UserDto dto);

	/**
	 * Updates a password for a given user
	 * @param login
	 * @param newPassword
	 */
	void updatePassword(String login, String newPassword);

	/**
	 * Makes two users friends
	 * @param userlogin
	 * @param friendLogin
	 * @return
	 */
	ServiceResult addFriend(String userlogin, String friendLogin);

	/**
	 * Gets name and surname of a specified user
	 * @param userlogin
	 * @return
	 */
	String getCredentials(String userlogin);

	/**
	 * Gets a user's friend list
	 * @param userlogin
	 * @return
	 */
	Set<UserEntity> getFriends(String userlogin);

	/**
	 * Removes friend from user
	 * @param userLogin
	 * @param friendLogin
	 */
	void removeFriend(String userLogin, String friendLogin);

	/**
	 * Gets all users in system
	 * @return
	 */
	Set<UserDto> getAllUsers();

	/**
	 * Checks if user exists
	 * @param login
	 * @return
	 */
	Boolean userExists(String login);

	/**
	 * Checks if email exists
	 * @param email
	 * @return
	 */
	Boolean emailExists(String email);

	/**
	 * Deletes user with specified id
	 * @param userId
	 * @return
	 */
	boolean deleteUser(Long userId);

	/**
	 * Grants admin rights to specified user
	 * @param login
	 * @return
	 */
	boolean grantAdmin(Long login);

	/**
	 * Revokes admin rights from specified user
	 * @param login
	 * @return
	 */
	boolean revokeAdmin(Long login);

}