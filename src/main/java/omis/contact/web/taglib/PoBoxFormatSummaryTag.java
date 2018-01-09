package omis.contact.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import omis.contact.report.PoBoxSummarizable;

/**
 * Post Office box format summary tag.
 * 
 * @author Joel Norris
 * @version 0.1.0 (January 17, 2017)
 * @since OMIS 3.0
 */
public class PoBoxFormatSummaryTag 
	extends SimpleTagSupport {

	/* Tag attributes */
	
	private PoBoxSummarizable value;
	private PoBoxFormat format;
	
	/* String formats. */
	
	private static final String LINE_1_FORMAT = "%s";
	private static final String LINE_2_FORMAT = "%s, %s %s";
	private static final String LINE_2_WITHOUT_STATE_FORMAT = "%s, %s";
	private static final String ZIP_CODE_EXTENSION_FORMAT = "-%s";
	
	/**
	 * Instantiates a default instance of Post Office box format summary tag.
	 */
	public PoBoxFormatSummaryTag() {
		//Default constructor.
	}
		
	/**
	 * Sets the value.
	 * 
	 * @param value post office box summarizable value
	 */
	public void setValue(PoBoxSummarizable value) {
		this.value = value;
	}

	/**
	 * Sets the format to instance of ({@link PoBoxFormat}) with name.
	 * 
	 * @param format post office box format
	 */
	public void setFormat(PoBoxFormat format) {
		this.format = format;
	}

	/* HTML output. */
	
	/*
	 * Returns JSP writer.	
	 * 
	 * @return JSP writer
	 */
	private JspWriter getJspWriter() {
		return getJspContext().getOut();
	}

	/* Handle tag */

	/** {@inheritDoc} */
	@Override
	public void doTag() throws JspException, IOException {
		String line = new String();
		if (PoBoxFormat.LINE1.equals(this.format)) {
			line = String.format(LINE_1_FORMAT, 
					this.value.getPoBoxValue());
			this.getJspWriter().println(line);
		} else if (PoBoxFormat.LINE2.equals(this.format)) {
			if (this.value.getPoBoxStateAbbreviation() == null || 
					this.value.getPoBoxStateAbbreviation().isEmpty()) {
				line = String.format(LINE_2_WITHOUT_STATE_FORMAT,
						this.value.getPoBoxCityName(),
						this.value.getPoBoxZipCodeValue());
			} else {
				line = String.format(LINE_2_FORMAT,
						this.value.getPoBoxCityName(),
						this.value.getPoBoxStateAbbreviation(),
						this.value.getPoBoxZipCodeValue());
			}
			String zipCodeExtension = this.value.getPoBoxZipCodeExtension(); 
			if (zipCodeExtension != null
					&& zipCodeExtension.isEmpty()) {
				line += String.format(ZIP_CODE_EXTENSION_FORMAT,
						zipCodeExtension);
			}
			this.getJspWriter().println(line);
		} else {
			throw new JspException(
					"Unsupported PO Box format");
		}
	}
}