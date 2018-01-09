package omis.offense.domain;

/**
 * Offense classification.
 * 
 * @author Josh Divine
 * @version 0.1.0 (Feb 9, 2017)
 * @since OMIS 3.0
 */
public enum OffenseClassification {

	/* Offense classification is vehicle */
	VEHICLE(false, (short)2),
	/* Offense classification is influence */
	INFLUENCE(true, (short)6),
	/* Offense classification is violent */
	VIOLENT(false, (short)10),
	/* Offense classification is property */
	PROPERTY(false, (short)7),
	/* Offense classification is person */
	PERSON(false, (short)5),
	/* Offense classification is drug */
	DRUG(false, (short)8),
	/* Offense classification is sex */
	SEX(false, (short)9),
	/* Offense classification is public order */
	PUBLIC_ORDER(false, (short)3),
	/* Offense classification is weapon */
	WEAPON(false, (short)4);
	
	private final Boolean substance;
	
	private final Short severity;
	
	private OffenseClassification(Boolean substance, Short severity) {
		this.substance = substance;
		this.severity = severity;
	}
	
	/**
	 * Returns <code>this.name()</code>.
	 * 
	 * @return <code>this.name()</code>
	 */
	public String getName() {
		return this.name();
	}
	
	/**
	 * Returns substance.
	 * 
	 * @return substance
	 */
	public Boolean getSubstance() {
		return this.substance;
	}
	
	/**
	 * Returns the severity.
	 * 
	 * @return severity
	 */
	public Short getSeverity() {
		return this.severity;
	}
}
