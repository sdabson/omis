package omis.person.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Displays formatted person social security number.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (May 27, 2016)
 * @since OMIS 3.0
 */

public class PersonFormatSsnTag extends SimpleTagSupport {

	// Tag attributes. 
	
	//pattern to look for
	private static final String SSN_PATTERN = "^(\\d{3})(\\d{2})(\\d{4})$";

	//masked SSN format
	private static final String MASKED_SSN = "XXX-XX-$3";
	
	//unmasked SSN format
	private static final String UNMASKED_SSN = "$1-$2-$3";
	
	//the value of the SSN
	private Integer value;
	
	//boolean to decide what format to output
	private Boolean masked;

	/**
	 * Sets the value of the social security number.
	 * 
	 * @param value value
	 */
	public void setValue(final Integer value) {
		this.value = value;
	}

	/**
	 * Sets whether a social security number is masked.
	 * 
	 * @param masked masked
	 */
	public void setMasked(final Boolean masked) {
		this.masked = masked;
	}

	/*
	 * Returns JSP writer.
	 * 
	 * @return JSP writer
	 */
	private JspWriter getJspWriter() {
		return getJspContext().getOut();
	}
	
	/** {@inheritDoc} */
	@Override	
	public void doTag() throws JspException, IOException {
		if (this.value != null) {
			String ssn = String.format("%09d", value);
			if (masked != null) {
				if (masked) {
						 this.getJspWriter().println(ssn
							.replaceAll(SSN_PATTERN, MASKED_SSN));
				} else if (!masked) {
					this.getJspWriter().println(ssn
							.replaceAll(SSN_PATTERN, UNMASKED_SSN));
				} else {
					this.getJspWriter().println(
							"Social security view role not provided");
				}
			} else {
				this.getJspWriter().println("");	
			}
		}
	} 
}