package omis.courtcase.domain.component;

import java.io.Serializable;

import omis.person.domain.Person;

/**
 * Personnel associated with a court case. 
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 28, 2013)
 * @since OMIS 3.0
 */
public class CourtCasePersonnel
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Person judge;
	
	private String defenseAttorneyName;
	
	private String prosecutingAttorneyName;

	/** Instantiates a default association of court case personnel. */
	public CourtCasePersonnel() {
		// Default instantiation
	}

	/**
	 * Instantiates association of court case personnel.
	 * 
	 * @param judge judge
	 * @param defenseAttorneyName name of defense attorney
	 * @param prosecutingAttorneyName name of prosecuting attorney
	 */
	public CourtCasePersonnel(
			final Person judge,
			final String defenseAttorneyName,
			final String prosecutingAttorneyName) {
		this.judge = judge;
		this.defenseAttorneyName = defenseAttorneyName;
		this.prosecutingAttorneyName = prosecutingAttorneyName;
	}
	
	/**
	 * Sets the judge.
	 * 
	 * @param judge judge
	 */
	public void setJudge(final Person judge) {
		this.judge = judge;
	}
	
	/**
	 * Returns the judge.
	 * 
	 * @return judge
	 */
	public Person getJudge() {
		return this.judge;
	}

	/**
	 * Sets the defense attorney name.
	 * 
	 * @param defenseAttorneyName defense attorney name
	 */
	public void setDefenseAttorneyName(final String defenseAttorneyName) {
		this.defenseAttorneyName = defenseAttorneyName;
	}
	
	/**
	 * Returns the defense attorney name.
	 * 
	 * @return defense attorney name
	 */
	public String getDefenseAttorneyName() {
		return this.defenseAttorneyName;
	}

	/**
	 * Sets the prosecuting attorney name.
	 * 
	 * @param prosecutingAttorneyName prosecuting attorney name
	 */
	public void setProsecutingAttorneyName(
			final String prosecutingAttorneyName) {
		this.prosecutingAttorneyName = prosecutingAttorneyName;
	}
	
	/**
	 * Returns the prosecuting attorney name.
	 * 
	 * @return prosecuting attorney name
	 */
	public String getProsecutingAttorneyName() {
		return this.prosecutingAttorneyName;
	}
}