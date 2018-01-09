package omis.address.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import omis.address.report.AddressSummarizable;

/**
 * Displays formatted address summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Mar 19, 2015)
 * @since OMIS 3.0
 */
public class AddressFormatSummaryTag 
	extends SimpleTagSupport {

	/* Tag attributes. */
	
	private AddressSummarizable value;
	
	private AddressFormat format;
	
	/* String formats. */
	
	private static final String LINE_1_FORMAT = "%s";

	private static final String LINE_2_FORMAT = "%s, %s %s";
	
	private static final String LINE_2_WITHOUT_STATE_FORMAT = "%s, %s";
	
	private static final String ZIP_CODE_EXTENSION_FORMAT = "-%s";
	
	/**
	 * Instantiates a default instance of address format summary tag.
	 */
	public AddressFormatSummaryTag() {
		//Default constructor.
	}

	/**
	 * Sets the value.
	 * 
	 * @param value address summarizable value
	 */
	public void setValue(final AddressSummarizable value) {
		this.value = value;
	}

	/**
	 * Sets the format to instance of ({@link AddressFormat}) with name.
	 * 
	 * @param format address format instance name
	 */
	public void setFormat(final String format) {
		this.format = AddressFormat.valueOf(format);
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
		if (AddressFormat.LINE1.equals(this.format)) {
			line = String.format(LINE_1_FORMAT, 
					this.value.getAddressValue());
			
			this.getJspWriter().println(line);
		} else if (AddressFormat.LINE2.equals(this.format)) {
			if (this.value.getAddressStateAbbreviation() == null ||
					this.value.getAddressStateAbbreviation().isEmpty()) {
				line = String.format(LINE_2_WITHOUT_STATE_FORMAT, 
						this.value.getAddressCityName(),
						this.value.getAddressZipCodeValue());
			} else {
				line = String.format(LINE_2_FORMAT, 
						this.value.getAddressCityName(),
						this.value.getAddressStateAbbreviation(), 
						this.value.getAddressZipCodeValue());
			}
			String zipCodeExtension = this.value.getAddressZipCodeExtension();
			if (zipCodeExtension != null && zipCodeExtension.length() > 0) {
				line += String.format(ZIP_CODE_EXTENSION_FORMAT, 
						zipCodeExtension);
			}
			this.getJspWriter().println(line);
		} else {
			throw new JspException(
					"Unsupported address format");
		}
	}
}