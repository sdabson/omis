package omis.courtcase.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;

/**
 * Court case note.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Jan 24, 2017)
 * @since OMIS 3.0
 */
public interface CourtCaseNote extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Sets the court case.
	 * 
	 * @param courtCase court case
	 */
	void setCourtCase(CourtCase courtCase);
	
	/**
	 * Returns the court case.
	 * 
	 * @return court case
	 */
	CourtCase getCourtCase();
	
	/**
	 * Sets the note date.
	 * 
	 * @param date note date
	 */
	void setDate(Date date);
	
	/**
	 * Returns the note date.
	 * 
	 * @return note date
	 */
	Date getDate();
	
	/**
	 * Sets the note value.
	 * 
	 * @param value note value
	 */
	void setValue(String value);
	
	/**
	 * Returns the note value.
	 * 
	 * @return note value
	 */
	String getValue();
}
