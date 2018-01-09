package omis.offenseterm.web.form;

import java.io.Serializable;

import omis.court.domain.Court;

public class OffenseTermDocketForm 
	implements Serializable {

	private static final long serialVersionUID = 1L;

	private Court court;
	
	private String docketValue;
	
	/** Instantiates form for a docket. */
	public OffenseTermDocketForm() {
		// Default instantiation
	}

	/**
	 * @return the court
	 */
	public Court getCourt() {
		return court;
	}

	/**
	 * @param court the court to set
	 */
	public void setCourt(Court court) {
		this.court = court;
	}

	/**
	 * @return the docketValue
	 */
	public String getDocketValue() {
		return docketValue;
	}

	/**
	 * @param docketValue the docketValue to set
	 */
	public void setDocketValue(String docketValue) {
		this.docketValue = docketValue;
	}
}
