package omis.victim.report;

import java.io.Serializable;
import java.util.Date;

import omis.demographics.domain.Sex;
import omis.offender.domain.Offender;
import omis.person.domain.Person;

/**
 * Summary of victim.
 * 
 * <p>To be a victim, a person must have at least one victim association
 * with an offender.
 *
 * @author Stephen Abson
 * @author Joel Norris
 * @version 0.0.2 (Aug 14, 2017)
 * @since OMIS 3.0
 */
public class VictimSummary
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	private final String lastName;
	private final String firstName;
	private final String middleName;
	private final String suffix;
	private final Sex sex;
	private final Date birthDate;
	private final Boolean offender;
	private final Integer offenderNumber;
	private final Long associationCount;
	private final Long noteCount;
	private final Date lastContactDate;
	private final Long documentCount;

	/**
	 * Instantiates summary of victim.
	 * 
	 * @param victim victim
	 * @param offender offender if victim is an offender
	 * @param associationCount count of associations
	 * @param noteCount count of notes
	 */
	public VictimSummary(
			final Person victim,
			final Offender offender,
			final Long associationCount,
			final Long noteCount,
			final Date lastContactDate,
			final Long documentCount) {
		this.id = victim.getId();
		this.lastName = victim.getName().getLastName();
		this.firstName = victim.getName().getFirstName();
		this.middleName = victim.getName().getMiddleName();
		this.suffix = victim.getName().getSuffix();
		if (victim.getIdentity() != null) {
			this.sex = victim.getIdentity().getSex();
			this.birthDate = victim.getIdentity().getBirthDate();
		} else {
			this.sex = null;
			this.birthDate = null;
		}
		if (offender != null) {
			this.offender = true;
			this.offenderNumber = offender.getOffenderNumber();
		} else {
			this.offender = false;
			this.offenderNumber = null;
		}
		this.associationCount = associationCount;
		this.noteCount = noteCount;
		this.lastContactDate = lastContactDate;
		this.documentCount = documentCount;
	}

	/**
	 * Returns ID.
	 * 
	 * @return ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns last name.
	 * 
	 * @return last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Returns first name.
	 * 
	 * @return first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Returns middle name.
	 * 
	 * @return middle name
	 */
	public String getMiddleName() {
		return this.middleName;
	}

	/**
	 * Returns suffix.
	 * 
	 * @return suffix
	 */
	public String getSuffix() {
		return this.suffix;
	}

	/**
	 * Returns sex.
	 * 
	 * @return sex
	 */
	public Sex getSex() {
		return this.sex;
	}

	/**
	 * Returns birth date.
	 * 
	 * @return birth date
	 */
	public Date getBirthDate() {
		return this.birthDate;
	}

	/**
	 * Returns whether victim is an offender.
	 * 
	 * @return whether victim is an offender
	 */
	public Boolean getOffender() {
		return this.offender;
	}
	
	/**
	 * Returns offender number if victim is an offender.
	 * 
	 * <p>Will return {@code null} if {@code offender} property is {@code false}
	 * or {@code null}.
	 * 
	 * @return offender number if victim is an offender; {@code null}
	 * if {@code offender} property is {@code false} or {@code null}
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns count of associations.
	 * 
	 * @return count of associations
	 */
	public Long getAssociationCount() {
		return this.associationCount;
	}
	
	/**
	 * Returns count of notes.
	 * 
	 * @return count of notes
	 */
	public Long getNoteCount() {
		return this.noteCount;
	}

	/**
	 * Returns the last contact date.
	 * 
	 * @return last contact date
	 */
	public Date getLastContactDate() {
		return this.lastContactDate;
	}
	
	/**
	 * Returns the document count.
	 * 
	 * @return document count
	 */
	public Long getDocumentCount() {
		return this.documentCount;
	}
}