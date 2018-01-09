package omis.beans.factory.impl.hibernate;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import omis.beans.factory.PropertyEditorFactory;
import omis.dao.GenericDao;

import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.PersistentClass;

/**
 * Hibernate implementation of property editor factory.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
public class PropertyEditorFactoryHibernateImpl
		implements PropertyEditorFactory {

	private static final String GETTER_VERB = "get";
	
	private final GenericDao<? extends Serializable> dao;
	
	private final String entityName;
	
	private final Configuration configuration;
	
	/**
	 * Instantiates an Hibernate implementation of property editor factory.
	 * 
	 * @param dao data access object
	 * @param entityName name of entity
	 * @param configuration configuration
	 */
	public PropertyEditorFactoryHibernateImpl(
			final GenericDao<? extends Serializable> dao,
			final String entityName, final Configuration configuration) {
		if (configuration.getClassMapping(entityName) == null) {
			throw new IllegalArgumentException(String.format(
					"Entity with name \'%s\' cannot be found in configuration",
					entityName));
		}
		this.dao = dao;
		this.entityName = entityName;
		this.configuration = configuration;
	}
	
	/** {@inheritDoc} */
	@Override
	public PropertyEditor createPropertyEditor() {
		return new PropertyEditorSupport() {
			
			/** {@inheritDoc} */
			@Override
			public void setAsText(final String text) {
				if (text != null && text.length() > 0) {
					Serializable entity
						= dao.findById(Long.valueOf(text), false);
					setValue(entity);
				} else {
					setValue(null);
				}
			}
			
			/** {@inheritDoc} */
			@Override
			public String getAsText() {
				Serializable object = (Serializable) this.getValue();
				if (object != null) {
					PersistentClass classMapping =
						PropertyEditorFactoryHibernateImpl
							.this.configuration.getClassMapping(
						PropertyEditorFactoryHibernateImpl
									.this.entityName); 
					String idName =
								classMapping.getIdentifierProperty().getName();
					Serializable idValue = (Serializable) invokeMethod(
							object,
							PropertyEditorFactoryHibernateImpl
								.GETTER_VERB + capitalize(idName));
					return String.valueOf(idValue);
				} else {
					return null;
				}
			}
		};
	}
	
	// Upper case the first character of text
	private String capitalize(final String text) {
		if (text != null && text.length() > 0) {
			return text.substring(0, 1).toUpperCase() + (text.length() > 1
					? text.substring(1, text.length()) : "");
		} else {
			return text;
		}
	}
	
	// Reflectively invoke a method
	private Object invokeMethod(final Object object, final String methodName,
			final Object... args) {
		Method m;
		try {
			Class<?>[] argTypes = new Class<?>[args.length];
			for (int i = 0; i < args.length; i++) {
				argTypes[i] = args[i].getClass();
			}
			m = object.getClass().getMethod(methodName, argTypes);
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Security violation: " + e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"No such method: " + e.getMessage(), e);
		}
		try {
			return m.invoke(object, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"Illegal argument: " + e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Illegal access: " + e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(
					"Invocation target error: " + e.getMessage(), e);
		}
	}
}