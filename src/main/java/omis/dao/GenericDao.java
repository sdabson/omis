package omis.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Generic data access object.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (June 12, 2013)
 * @since OMIS 3.0
 *
 * @param <T> type of entity object
 */
public interface GenericDao<T extends Serializable> {

	/**
	 * Returns an entities by ID.
	 * 
	 * @param id ID of entity to be found
	 * @param lock whether or the selected record should be locked using
	 * pessimistic locking
	 * @return entity with specified ID
	 */
	T findById(Long id, boolean lock);
	
	/**
	 * Returns all entities.
	 * 
	 * <p>This method maybe overridden to exclude invalid entities and
	 * specify sort order.
	 * 
	 * <p>This should also throw {@code UnsupportedOperationException} if an
	 * unmanagable number of entities would be returned.
	 * 
	 * @return all persisted entities
	 */
	List<T> findAll();
	
	/**
	 * Returns all entities that match the given example.
	 * 
	 * @param exampleInstance example entity to match
	 * @param excludeProperty properties to exclude
	 * @return all entities matching the example
	 */
	List<T> findByExample(T exampleInstance, String[] excludeProperty);
	
	/**
	 * Makes an entity persistent.
	 * 
	 * @param entity entity to make persistent
	 * @return persisted entity
	 */
	T makePersistent(T entity);

	/**
	 * Makes an entity transient.
	 * 
	 * @param entity entity to make transient
	 */
	void makeTransient(T entity);
}