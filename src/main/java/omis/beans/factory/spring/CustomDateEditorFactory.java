package omis.beans.factory.spring;

import org.springframework.beans.propertyeditors.CustomDateEditor;

/**
 * Factory class to create custom date property editors.
 * 
 * <p>Each custom date property editor formats dates to a specified pattern.
 * 
 * <p>Where the factory method does not allow the pattern to be specified,
 * a standard pattern (to be used throughout the application) is used. 
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Nov 17, 2012)
 * @since OMIS 3.0
 */
public interface CustomDateEditorFactory {

	/**
	 * Creates and returns a custom date editor for the specified date, time
	 * or date and time pattern.
	 * 
	 * @param pattern date pattern
	 * @param allowEmpty whether empty strings should be allowed
	 * @return custom date editor for specified date pattern
	 */
	CustomDateEditor createCustomDateEditor(String pattern, boolean allowEmpty);
	
	/**
	 * Creates and returns a custom date only property editor with a standard
	 * date only pattern. The pattern includes the day of month, month and
	 * year.
	 * 
	 * @param allowEmpty whether empty strings should be allowed
	 * @return custom date only property editor
	 */
	CustomDateEditor createCustomDateOnlyEditor(boolean allowEmpty);
	
	/**
	 * Creates and returns a custom time only property editor with a standard
	 * time only pattern. The pattern includes the hour and minute of the time.
	 * 
	 * <p>The hour will be given in 24 military time or and AM/PM indicator will
	 * be specified.
	 * 
	 * @param allowEmpty whether empty strings should be allowed
	 * @return custom time only property editor
	 */
	CustomDateEditor createCustomTimeOnlyEditor(boolean allowEmpty);
	
	/**
	 * Creates and returns a custom date and time property editor with a
	 * standard date and time pattern. The pattern includes the day of month,
	 * month, year, hour and minute of the date.
	 * 
	 * <p>The hour will be given in 24 military time or and AM/PM indicator will
	 * be specified.
	 * 
	 * @param allowEmpty whether empty strings should be allowed
	 * @return custom date and time property editor 
	 */
	CustomDateEditor createCustomDateTimeEditor(boolean allowEmpty);
}