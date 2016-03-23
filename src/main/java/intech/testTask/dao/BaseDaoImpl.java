package intech.testTask.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	private final Class<T> type;

	public BaseDaoImpl(final Class<T> type) {
		this.type = type;
	}

	@Autowired
	protected SessionFactory sessionFactory;

	public void saveOrUpdate(final T object) {
		sessionFactory.getCurrentSession().saveOrUpdate(object);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(type).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T getById(final Long id) {
		return (T) sessionFactory.getCurrentSession().get(type, id);
	}

	@Override
	public void delete(final T object) {
		sessionFactory.getCurrentSession().delete(object);
	}
}
