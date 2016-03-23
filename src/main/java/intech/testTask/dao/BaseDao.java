package intech.testTask.dao;

import java.util.List;

import intech.testTask.entity.UserEntity;

public interface BaseDao<T> {
	void saveOrUpdate(T object);

	List<T> getAll();

	T getById(Long id);

	void delete(T object);

}
