package omis.instance.factory;

import java.io.Serializable;

/**
 * Instance factory.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 3, 2013)
 * @since OMIS 3.0
 * @param <T> type to instantiate
 */
public interface InstanceFactory<T extends Serializable> {

	/**
	 * Instantiates and returns and instance of {@code T}.
	 * 
	 * @return new instance of {@code T}
	 */
	T createInstance();
}