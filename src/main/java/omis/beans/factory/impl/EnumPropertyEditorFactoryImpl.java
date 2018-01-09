package omis.beans.factory.impl;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import omis.beans.factory.PropertyEditorFactory;

/**
 * Property editor factories for enums the resolution of which to be determined
 * by a specified property rather than instance name.
 * 
 * <p>The string representation {@code toString()} of the property value will
 * be compared to the string value passed to the property editor.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jul 30, 2013)
 * @since OMIS 3.0
 */
public class EnumPropertyEditorFactoryImpl
		implements PropertyEditorFactory {
	
	private final Method getter;
	
	private final Method values;
	
	/**
	 * Instantiates a enum property editor factory for the enum with the
	 * specified property.
	 * 
	 * @param enumName name of enum
	 * @param enumPropertyName name of enum property
	 */
	public EnumPropertyEditorFactoryImpl(
			final String enumName, final String enumPropertyName) {
		Class<?> clazz;
		try {
			clazz = Class.forName(enumName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found: " + e.getMessage(), e);
		}
		String methodName = "get"
				+ enumPropertyName.substring(0, 1).toUpperCase()
				+ (enumPropertyName.length() > 1
						? enumPropertyName.substring(1,
								enumPropertyName.length()) : "");
		try {
			this.getter = clazz.getMethod(methodName);
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Security violation: " + e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No such method: " + e.getMessage(), e);
		}
		try {
			this.values = clazz.getMethod("values");
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Security violation: " + e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("No such method: " + e.getMessage(), e);
		}
	}

	/** {@inheritDoc} */
	@Override
	public PropertyEditor createPropertyEditor() {
		return new PropertyEditorSupport() {
			
			/** {@inheritDoc} */
			@Override
			public void setAsText(final String text) {
				if (text == null || text.length() == 0) {
					this.setValue(null);
				} else {
					Object[] values;
					try {
						values = (Object[]) EnumPropertyEditorFactoryImpl
								.this.values.invoke(null);
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(
								"Illegal argument: " + e.getMessage(), e);
					} catch (IllegalAccessException e) {
						throw new RuntimeException(
								"Illegal access: " + e.getMessage(), e);
					} catch (InvocationTargetException e) {
						throw new RuntimeException(
								"Invocation error: " + e.getMessage(), e); 
					}
					Object found = null;
					for (Object o : values) {
						try {
							if (EnumPropertyEditorFactoryImpl.this.getter
									.invoke(o).toString().equals(text)) {
								found = o;
							}
						} catch (IllegalArgumentException e) {
							throw new RuntimeException(
									"Illegal argument: " + e.getMessage(), e);
						} catch (IllegalAccessException e) {
							throw new RuntimeException(
									"Illegal access: " + e.getMessage(), e);
						} catch (InvocationTargetException e) {
							throw new RuntimeException(
									"Invocation error: " + e.getMessage(), e);
						}
					}
					if (found != null) {
						this.setValue(found);
					} else {
						throw new IllegalArgumentException("Instance of enum"
								+ " with specified property value not found");
					}
				}
			}
			
			/** {@inheritDoc} */
			@Override
			public String getAsText() {
				Object o = this.getValue();
				if (this.getValue() == null) {
					return null;
				}
				try {
					return EnumPropertyEditorFactoryImpl.this.getter.invoke(o)
							.toString();
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(
							"Illegal argument: " + e.getMessage(), e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(
							"Illegal acces: " + e.getMessage(), e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException(
							"Invovation target error: " + e.getMessage(), e);
				}
			}
		};
	}
}