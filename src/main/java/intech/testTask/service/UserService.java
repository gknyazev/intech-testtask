package intech.testTask.service;

import java.util.Set;

import intech.testTask.dto.UserDto;
import intech.testTask.entity.UserEntity;
import intech.testTask.util.ServiceResult;

public interface UserService {

	void registerNewUser(UserDto dto);

	void updatePassword(String login, String newPassword);

	ServiceResult addFriend(String userlogin, String friendLogin);

	String getCredentials(String userlogin);

	Set<UserEntity> getFriends(String userlogin);

	void removeFriend(String userLogin, String friendLogin);

	Set<UserDto> getAllUsers();

	Boolean userExists(String login);

	Boolean emailExists(String email);

	boolean deleteUser(Long userId);

	boolean grantAdmin(Long login);

	boolean revokeAdmin(Long login);

}