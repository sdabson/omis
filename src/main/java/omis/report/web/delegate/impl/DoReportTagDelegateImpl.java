package omis.report.web.delegate.impl;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

import omis.report.config.ReportProperties;
import omis.report.web.delegate.DoReportTagDelegate;

/**
 * Implementation of delegate to do report tag.
 * 
 * <p>Implementation outputs a link to the report on the report server.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public class DoReportTagDelegateImpl
		implements DoReportTagDelegate {
	
	private final ReportProperties reportProperties;
	
	final private static String REPORT_LINK_HTML_TEMPLATE = "<a href=\"%1$s%2$s" 
			+ "&j_username=%3$s&j_password=%4$s&decorate=%5$s\" class=\"%6$s\" "
			+ "title=\"%7$s\" id=\"%8$s\">";
	
	final private static String REPORT_END_TAG = "</a>";

	/**
	 * Instantiates delegate to do report tag.
	 * 
	 * @param reportProperties report properties
	 */
	public DoReportTagDelegateImpl(
			final ReportProperties reportProperties) {
		this.reportProperties = reportProperties;
	}
	
	/** {@inheritDoc} */
	@Override
	public void doTag(
			final JspFragment jspBody,
			final String id, final String decorate, final String reportPath,
			final String className, final String title)
					throws JspException, IOException {
		String html = String.format(REPORT_LINK_HTML_TEMPLATE,
				reportProperties.getBaseUrl(),
				reportPath, reportProperties.getUsername(),
				reportProperties.getPassword(), decorate, className, title, id);
		jspBody.getJspContext().getOut().write(html);
		jspBody.invoke(null);
		jspBody.getJspContext().getOut().write(REPORT_END_TAG);
	}
}