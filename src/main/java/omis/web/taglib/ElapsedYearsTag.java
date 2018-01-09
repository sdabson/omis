package omis.web.taglib;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import omis.util.TimeComparison;

/** Tag calculates elapsed years.
 * @author Ryan Johns
 * @version 0.1.0 (Jan 15, 2016)
 * @since OMIS 3.0 */
public class ElapsedYearsTag extends SimpleTagSupport {
	private static final String ILLEGAL_STATE_EXCEPTION_MSG = 
			"%s cannot be null";
	private static final String START_DATE_MSG = "Start date";
	private static final String END_DATE_MSG = "End date";
	private Date start;
	private Date end;
	
	/** Constructor.  */
	public ElapsedYearsTag() {
		super();
	}
		
	/** Sets start date.
	 * @param start - start date. */
	public void setStart(final Date start) {
		this.start = start;
	}
	
	/** Sets end date.
	 * @param end - end date. */
	public void setEnd(final Date end) {
		this.end = end;
	}
	
	/** Calculates elapsed years.
	 * @throws JspException - When first or second date is null.  */
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext) this.getJspContext();
		JspWriter jspWriter = pageContext.getOut();
		if (this.start == null) {
			throw new JspException(new IllegalStateException(String.format(
					ILLEGAL_STATE_EXCEPTION_MSG, START_DATE_MSG)));
		}
		if (this.end == null) {
			throw new JspException(new IllegalStateException(String.format(
					ILLEGAL_STATE_EXCEPTION_MSG, END_DATE_MSG)));
		}
		jspWriter.write(String.valueOf(TimeComparison.elapsedYears(
				this.start, this.end)));
	}
}
