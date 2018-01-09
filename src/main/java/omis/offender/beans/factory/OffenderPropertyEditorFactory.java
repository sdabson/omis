package omis.offender.beans.factory;

import java.beans.PropertyEditor;

/**
 * Produces property editors for offenders.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Nov 17, 2012)
 * @since OMIS 3.0
 */
public interface OffenderPropertyEditorFactory {

	/**
	 * Instantiates and returns a property editor for offenders.
	 * 
	 * @return newly instantiated property editor for offenders
	 */
	PropertyEditor createOffenderPropertyEditor();
}