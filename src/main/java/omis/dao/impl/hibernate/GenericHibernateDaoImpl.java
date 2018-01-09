package omis.dao.impl.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import omis.dao.GenericDao;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.type.Type;

/**
 * Hibernate implementation of generic data access object.
 * 
 * <p>The entity to which data access is provided by the object can be
 * stated explicitly in the constructor for the entity name property or
 * can be inferred from the parameter {@code <T>}.
 * 
 * @author Stephen Abson
 * @version 0.1.2 (June 19, 2013)
 * @since OMIS 3.0
 * @see GenericDao
 * @param <T> type of entity object
 */
public abstract class GenericHibernateDaoImpl<T extends Serializable>
		implements GenericDao<T> {
	
	private final SessionFactory sessionFactory;
	
	private final String entityName;
	
	/**
	 * Instantiates an Hibernate implementation of generic data access
	 * object with the specified session factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	protected GenericHibernateDaoImpl(final SessionFactory sessionFactory,
			final String entityName) {
		this.sessionFactory = sessionFactory;
		this.entityName = entityName;
	}
	
	/**
	 * Get the session factory.
	 * 
	 * @return session factory
	 */
	protected final SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	// Returns the persisted class
	private Class<T> getPersistentClass() {
		@SuppressWarnings("unchecked")
		Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return persistentClass;
	}
	
	/**
	 * Returns the entity name.
	 * 
	 * <p>If the entity name is not set, returns the canonical name of the
	 * persistent entity type.
	 * 
	 * @return entity name if set; otherwise canonical name of persistent
	 * entity type
	 */
	protected final String getEntityName() {
		if (this.entityName != null) {
			return this.entityName;
		} else {
			return this.getPersistentClass().getCanonicalName();
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public T findById(final Long id, final boolean lock) {
		Session session = this.getSessionFactory().getCurrentSession();
		if (lock) {
			@SuppressWarnings("unchecked")
			T entity = (T) session.load(this.getPersistentClass(), id,
						LockOptions.UPGRADE);
			return entity;
		} else {
			@SuppressWarnings("unchecked")
			T entity = (T) session.load(this.getPersistentClass(), id);
			return entity;
		}
	}

	/** {@inheritDoc} */
	@Override
	public List<T> findAll() {
		return findByCriteria();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<T> findByExample(final T exampleInstance,
			final String[] excludeProperty) {
		Session session = this.getSessionFactory().getCurrentSession();
		Criteria crit = session.createCriteria(this.getEntityName());
		Example example = Example.create(exampleInstance);
		if (excludeProperty != null) {
			for (String exclude : excludeProperty) {
				example.excludeProperty(exclude);
			}
		}
		crit.add(example);
		@SuppressWarnings("unchecked")
		List<T> result = crit.list();
		return result;
	}
	
	/**
	 * Finds by criteria.
	 * 
	 * @param criterion criterion by which to find
	 * @return entities matching criteria
	 */
	protected List<T> findByCriteria(final Criterion... criterion) {
		Session session = this.getSessionFactory().getCurrentSession();

		Criteria crit = session.createCriteria(this.getEntityName());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) crit.list();
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public T makePersistent(final T entity) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.saveOrUpdate(this.getEntityName(), entity);
		return entity;
	}
	
	/** {@inheritDoc} */
	@Override
	public void makeTransient(final T entity) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(this.getEntityName(), entity);
	}
	
	/** Flush the persistence context. */
	public void flush() {
		Session session = this.getSessionFactory().getCurrentSession();
		session.flush();
	}
	
	/** Clear to persistence context. */
	public void clear() {
		Session session = this.getSessionFactory().getCurrentSession();
		session.clear();
	}
	
	/**
	 * Returns type of property.
	 * 
	 * @param propertyName name of property
	 * @return type of property
	 */
	protected Type getEntityPropertyType(final String propertyName) {
		return this.getSessionFactory().getClassMetadata(
				this.getEntityName()).getPropertyType(propertyName);
	}
}