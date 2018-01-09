package omis.adaaccommodation.web.form;

import java.util.Date;

import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.report.AccommodationSummary;
import omis.person.domain.Person;

/**
 * Accommodation issuance form.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 29, 2015)
 * @since OMIS 3.0
 */
public class AccommodationIssuanceForm {

	private Accommodation accommodation;
	
	private Date day;
	
	private Date time;
	
	private Person issuer;
	
	private String text;
	
	private AccommodationSummary summarize;
	
	/** Instantiates a default instance of accommodation issuance form. */
	public AccommodationIssuanceForm() {
		//Default constructor.
	}

	/**
	 * Returns the accommodation issuance of ADA accommodation.
	 *
	 * @return the accommodation
	 */
	public Accommodation getAccommodation() {
		return this.accommodation;
	}

	/**
	 * Sets the accommodation issuance of the ADA accommodation.
	 *
	 * @param accommodation the accommodation to set
	 */
	public void setAccommodation(final Accommodation accommodation) {
		this.accommodation = accommodation;
	}

	/**
	 * Returns the issuance day.
	 * 
	 * @return the day
	 */
	public Date getDay() {
		return this.day;
	}
	
	/**
	 * Sets the issuance day.
	 * 
	 * @param day day
	 */
	public void setDay(final Date day) {
		this.day = day;
	}
	
	/**
	 * Returns the accommodation issuance time.
	 *
	 * @return the time
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * Sets the accommodation issuance time.
	 *
	 * @param time the time to set
	 */
	public void setTime(final Date time) {
		this.time = time;
	}

	/**
	 * Returns the accommodation issuance issuer.
	 * 
	 * @return the issuer
	 */
	public Person getIssuer() {
		return this.issuer;
	}

	/**
	 * Sets the accommodation issuance issuer.
	 * 
	 * @param issuer issuer
	 */
	public void setIssuer(final Person issuer) {
		this.issuer = issuer;
	}

	/**
	 * Returns the accommodation issuance text.
	 *
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * Sets the accommodation issuance text.
	 *
	 * @param text the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

	/**
	 * Returns the accommodation summarization.
	 * 
	 * @return the summarize
	 */
	public AccommodationSummary getSummarize() {
		return this.summarize;
	}

	/**
	 * Sets the accommodation summarization.
	 * 
	 * @param summarize the summarize to set
	 */
	public void setSummarize(AccommodationSummary summarize) {
		this.summarize = summarize;
	}	
}