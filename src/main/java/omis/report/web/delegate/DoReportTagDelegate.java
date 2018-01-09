package omis.report.web.delegate;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

/**
 * Delegate to do report tag.
 * 
 * @author Stephen Abson
 * @version 0.0.1 (Nov 1, 2017)
 * @since OMIS 3.0
 */
public interface DoReportTagDelegate {

	/**
	 * Do report tag.
	 * 
	 * @param jspBody JSP body
	 * @param id {@code id} attribute
	 * @param decorate {@code decorate} attribute
	 * @param reportPath {@code reportPath} attribute
	 * @param className {@code className} attribute
	 * @param title {@code title} attribute
	 * @throws JspException thrown on exception in JSP
	 * @throws IOException thrown on IO exception
	 */
	void doTag(JspFragment jspBody, String id, String decorate,
			String reportPath, String className, String title)
				throws JspException, IOException;
}