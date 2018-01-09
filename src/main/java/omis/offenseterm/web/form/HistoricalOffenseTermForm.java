package omis.offenseterm.web.form;

import java.io.Serializable;

import omis.sentence.web.form.SentenceFields;

/**
 * Form for historical offense terms.
 *
 * <p>Historical offense terms are represented as inactive sentences.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
public class HistoricalOffenseTermForm
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SentenceFields sentenceFields = new SentenceFields();
	
	/** Instantiates form for historical offense terms. */
	public HistoricalOffenseTermForm() {
		// Default instantiation
	}
	
	/**
	 * Sets sentence fields.
	 * 
	 * @param sentenceFields sentence fields
	 */
	public void setSentenceFields(final SentenceFields sentenceFields) {
		this.sentenceFields = sentenceFields;
	}
	
	/**
	 * Returns sentence fields.
	 * 
	 * @return sentence fields
	 */
	public SentenceFields getSentenceFields() {
		return this.sentenceFields;
	}
}