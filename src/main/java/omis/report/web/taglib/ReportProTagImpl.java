package omis.report.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Report tag implementation that sets the template for report link HTML. 
 * 
 * @author Josh Divine
 * @version 0.1.0 (Mar 24, 2017)
 * @since OMIS 3.0
 */
public class ReportProTagImpl extends SimpleTagSupport {
	
	final private static String REPORT_LINK_HTML_TEMPLATE = "<a href=\"%1$s%2$s" 
			+ "&decorate=%3$s\" class=\"%4$s\" title=\"%5$s\" id=\"%6$s\">";
	final private static String REPORT_END_TAG = "</a>";
	
	final private static String JASPER_SERVER_PATH_PROPERTY 
		= "jasperServerProFlowPath";
	
	private String decorate;
	private String reportPath;
	private String id;
	private String className;
	private String title;
	private String reportServerUrl;
	
	/** Implements a report tag. */
	
	public ReportProTagImpl() {
		this.reportServerUrl = System.getProperty(JASPER_SERVER_PATH_PROPERTY);
	}
	
	/** Sets the decorate on the report tag. 
	 * 
	 * @param decorate report tag decorate
	 */
	public void setDecorate(final String decorate){
		this.decorate = decorate;
	}
	
	/** Sets the report path on the report tag. 
	 * 
	 * @param reportPath report tag report path	 
	 */
	public void setReportPath(final String reportPath) {
		this.reportPath = reportPath;
	}
	
	/** Sets the id on the report tag. 
	 * 
	 * @param id report tag id
	 */
	public void setId(final String id) {
		this.id = id;
	}
	
	/** Sets the class name on the report tag. 
	 * 
	 * @param className report tag class name
	 */
	public void setClassName(final String className) {
		this.className = className;
	}
	
	/** Sets the title on the report tag. 
	 * 
	 * @param title report tag title
	 */
	public void setTitle(final String title) {
		this.title = title;
	}
	
	/** {@inheritDoc} */
	@Override
	public void doTag() throws JspException, IOException {

		String html = String.format(REPORT_LINK_HTML_TEMPLATE, reportServerUrl,
				reportPath, decorate, className, title, id);
		
		try {
	
			JspFragment body = getJspBody();
			JspWriter out = getJspBody().getJspContext().getOut();
			out.write(html);
			body.invoke(null);
			out.write(REPORT_END_TAG);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
