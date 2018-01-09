package omis.beans.factory;

import java.beans.PropertyEditor;

/**
 * Factory for creating property editors.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
public interface PropertyEditorFactory {

	/**
	 * Returns a new property editor factory.
	 * 
	 * @return new property editor factory
	 */
	PropertyEditor createPropertyEditor();
}