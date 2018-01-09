package omis.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag the body of which will be processed if a given variable is of an instance
 * of a specified type.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 8, 2012)
 * @since OMIS 3.0
 */
public class InstanceofTag
		extends SimpleTagSupport {

	private Object var;
	
	private String className;
	
	/** Instantiates a default instance of tag. */
	public InstanceofTag() {
		// Default instance
	}
	
	/**
	 * Sets the variable of which to test the instance type.
	 * 
	 * @param var variable of which to test instance type
	 */
	public void setVar(final Object var) {
		this.var = var;
	}
	
	/**
	 * Sets the class name.
	 * 
	 * @param className class name
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	
	/** {@inheritDoc} */
	@Override
	public void doTag() throws JspException, IOException {
		Class<?> clazz;
		try {
			clazz = Class.forName(this.className);
		} catch (ClassNotFoundException e) {
			throw new JspException("Class not found: " + this.className, e);
		}
		if (clazz.isAssignableFrom(this.var.getClass())) {
			getJspBody().invoke(null);
		}
	}
}