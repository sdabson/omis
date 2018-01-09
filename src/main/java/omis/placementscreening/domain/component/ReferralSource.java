package omis.placementscreening.domain.component;

import omis.facility.domain.Facility;
import omis.person.domain.Person;

/** Referral Source type.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public class ReferralSource {
	private static final String FACILITY_REQUIRED = "Facility required";
	private static final String PERSON_REQUIRED = "Person required";
	
	private Facility facility;
	private Person person;
//--------------------------------Constructors----------------------------------
	/** Default Constructor. */
	public ReferralSource() { /* Do nothing. */ }
	
	/** Constructor.
	 * @param facility facility
	 * @param person person */
	public ReferralSource(final Facility facility, final Person person) {
		this.facility = facility;
		this.person = person;
	}
//-----------------------------------Getters------------------------------------
	/** Gets facility.
	 * @return facility facility. */
	public Facility getFacility() { 
		return this.facility; 
	}
	
	/** Gets person.
	 * @return person person. */
	public Person getPerson() { 
		return this.person; 
	}
//-----------------------------------Setters------------------------------------
	/** Sets facility.
	 * @param facility facility. */
	public void setFacility(final Facility facility) { 
		this.facility = facility; 
	}
	
	/** Sets person.
	 * @param person person. */
	public void setPerson(final Person person) { 
		this.person = person; 
	}
//------------------------------------------------------------------------------
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * 
	 * <p>Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * 
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof ReferralSource) {
				ReferralSource that = (ReferralSource) obj;
				this.checkState();
				if (this.getPerson().equals(that.getPerson()) 
						&& this.getFacility().equals(that.getFacility())) {
					result = true;
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns a hash code for {@code this}.
	 * 
	 * <p>Any mandatory property of {@code this} may be used in the hash code.
	 * If a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * 
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	public int hashCode() {
		final int seven = 7;
		final int eleven = 11;
		final int thirteen = 13;
		
		this.checkState();
		return seven + eleven * this.getFacility().hashCode() 
				+ thirteen * this.getPerson().hashCode();
	}
	
	/* Checks state. */
	private void checkState() {
		if (this.getPerson() == null) {
			throw new IllegalStateException(PERSON_REQUIRED);
		} 
		
		if (this.getFacility() == null) {
			throw new IllegalStateException(FACILITY_REQUIRED);
		}
	}
}
