package omis.search.report;


/** Offender search result report object.
 * @author Ryan Johns
 * @version 0.1.0 (May 29, 2013)
 * @since OMIS 3.0 */
public class OffenderSearchResult extends PersonSearchResult {
	private static final long serialVersionUID = 1L;

	private final Integer offenderNumber;

	/** constructor for offender search result.
	 * @param nameId id for name.
	 * @param personId id for person.
	 * @param offenderNumber offender number.
	 * @param firstName first name.
	 * @param middleName middle name.
	 * @param lastName last name. */
	public OffenderSearchResult(final Long nameId,
			final Long personId,
			final Integer offenderNumber,
			final String firstName,
			final String middleName,
			final String lastName, 
			final String suffixName) {
		super(nameId, personId, firstName, middleName, lastName, suffixName);
		this.offenderNumber = offenderNumber;
	}

	/** gets the offender number.
	 * @return offender number. */
	public Integer getOffenderNumber() { return this.offenderNumber; }

	@Override
	public String toString() {
		return "\"personId\":" + this.getPersonId() + ",\"offenderNumber\":"
				+ this.offenderNumber +  ",\"firstName\":" + this.getFirstName()
				+ ",\"middleName\":" + this.getMiddleName() + ",\"lastName\":"
				+ this.getLastName() + ",\"suffixName\":"
						+ this.getSuffixName();
	}
}