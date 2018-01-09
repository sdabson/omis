package omis.condition.domain;

/**
 * Agreement Category.
 * 
 *@author Annie Wahl 
 *@version 0.1.0 (Nov 27, 2017)
 *@since OMIS 3.0
 *
 */
public enum AgreementCategory {
	
	/**
	 * Court Case.
	 */
	COURT_CASE,
	
	/**
	 * Board of Pardons and Parole.
	 */
	BOARD_PARDONS_PAROLE;
	
	/**
	 * Returns the instance name.
	 * 
	 * @return instance name
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	
	@Override
	public String toString() {
		return this.name();
	}
}
