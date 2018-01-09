package omis.report.web.delegate.impl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import omis.report.web.delegate.DoReportTagDelegate;

/**
 * Implementation of delegate to do report tag that does nothing.
 * 
 * <p>This is to be used when reporting is not supported but the report
 * tag is still invoked in views (thereby preventing the tag from throwing
 * an {@code UnsupportedOperationException}.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class DoNothingReportTagDelegateImpl
		implements DoReportTagDelegate {

	/**
	 * Does tag.
	 * 
	 * @param jspBody ignored
	 * @param id ignored
	 * @param decorate ignored
	 * @param reportPath ignored
	 * @param className ignored
	 * @param title ignored
	 * @throws JspException never
	 * @throws IOException never
	 */
	@Override
	public void doTag(final JspFragment jspBody, final String id,
			final String decorate, final String reportPath,
			final String className, final String title)
					throws JspException, IOException {
		
		// Do nothing
	}
}