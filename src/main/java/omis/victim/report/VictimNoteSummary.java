package omis.victim.report;

import java.io.Serializable;
import java.util.Date;

import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.person.domain.PersonName;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.VictimNote;
import omis.victim.domain.VictimNoteCategory;

/**
 * Summary of victim note.
 *
 * @author Stephen Abson
 * @version 0.0.1 (Jul 24, 2015)
 * @since OMIS 3.0
 */
public class VictimNoteSummary
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Long id;
	
	private final String categoryName;
	
	private final Long victimId;
	
	private final String victimLastName;
	
	private final String victimFirstName;
	
	private final String victimMiddleName;
	
	private final String victimSuffix;
	
	private final Boolean victimOffender;
	
	private final Integer victimOffenderNumber;
	
	private final Boolean associationExists;
	
	private final Long associationId;
	
	private final Long offenderId;
	
	private final String offenderLastName;
	
	private final String offenderFirstName;
	
	private final String offenderMiddleName;
	
	private final String offenderSuffix;
	
	private final Integer offenderNumber;
	
	private final Date date;
	
	private final String value;
	
	/**
	 * Instantiates summary of victim note.
	 * 
	 * @param victimNote victim note
	 * @param victim victim
	 * @param victimName name of victim
	 * @param victimOffender victim as offender
	 * @param association note association
	 * @param offender offender
	 * @param offenderName name of offender
	 */
	public VictimNoteSummary(final VictimNote victimNote,
			final VictimNoteCategory category, final Person victim,
			final PersonName victimName, final Offender victimOffender,
			final VictimAssociation association,
			final Offender offender, final PersonName offenderName) {
		this.id = victimNote.getId();
		this.categoryName = category.getName();
		this.victimId = victim.getId();
		this.victimLastName = victimName.getLastName();
		this.victimFirstName = victimName.getFirstName();
		this.victimMiddleName = victimName.getMiddleName();
		this.victimSuffix = victimName.getSuffix();
		if (victimOffender != null) {
			this.victimOffender = true;
			this.victimOffenderNumber = victimOffender.getOffenderNumber();
		} else {
			this.victimOffender = false;
			this.victimOffenderNumber = null;
		}
		if (association != null) {
			this.associationExists = true;
			this.associationId = association.getId();
		} else {
			this.associationExists = false;
			this.associationId = null;
		}
		if (offender != null) {
			this.offenderId = offender.getId();
			this.offenderLastName = offenderName.getLastName();
			this.offenderFirstName = offenderName.getFirstName();
			this.offenderMiddleName = offenderName.getMiddleName();
			this.offenderSuffix = offenderName.getSuffix();
			this.offenderNumber = offender.getOffenderNumber();
		} else {
			this.offenderId = null;
			this.offenderLastName = null;
			this.offenderFirstName = null;
			this.offenderMiddleName = null;
			this.offenderSuffix = null;
			this.offenderNumber = null;
		}
		this.date = victimNote.getDate();
		this.value = victimNote.getValue();
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
	 * Returns category name.
	 * 
	 * @return category name
	 */
	public String getCategoryName() {
		return this.categoryName;
	}
	
	/**
	 * Returns ID of victim.
	 * 
	 * @return ID of victim
	 */
	public Long getVicimId() {
		return this.victimId;
	}
	
	/**
	 * Returns last name of victim.
	 * 
	 * @return last name of victim
	 */
	public String getVictimLastName() {
		return this.victimLastName;
	}
	
	/**
	 * Returns first name of victim.
	 * 
	 * @return first name of victim
	 */
	public String getVictimFirstName() {
		return this.victimFirstName;
	}
	
	/**
	 * Returns middle name of victim.
	 * 
	 * @return middle name of victim
	 */
	public String getVictimMiddleName() {
		return this.victimMiddleName;
	}
	
	/**
	 * Returns suffix of victim.
	 * 
	 * @return suffix of victim
	 */
	public String getVictimSuffix() {
		return this.victimSuffix;
	}
	
	/**
	 * Returns whether victim is an offender.
	 * 
	 * <p>If {@code false} or {@code null},
	 * {@code this#getVictimOffenderNumber() will return {@code null}.
	 * 
	 * @return whether victim is an offender
	 */
	public Boolean getVictimOffender() {
		return this.victimOffender;
	}
	
	/**
	 * Returns offender number of victim if victim is an offender.
	 * 
	 * <p>Returns {@code null} if {@code this#getVictimOffender()} returns
	 * {@code false} or {@code null}.
	 * 
	 * @return offender number of victim if victim is an offender
	 */
	public Integer getVictimOffenderNumber() {
		return this.victimOffenderNumber;
	}
	
	/**
	 * Returns whether association exists for note.
	 * 
	 * <p>If {@code null} or {@code false} is returned,
	 * {@code this#getAssociationCategoryName() will return {@code null}.
	 * 
	 * @return whether association exists for note
	 */
	public Boolean getAssociationExists() {
		return this.associationExists;
	}
	
	/**
	 * Returns ID of association if association exists, otherwise returns
	 * {@code null}.
	 * 
	 * @return ID of association if association exists, otherwise {@code null}
	 */
	public Long getAssociationId() {
		return this.associationId;
	}
	
	/**
	 * Returns ID of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return ID of offender if association exists
	 */
	public Long getOffenderId() {
		return this.offenderId;
	}
	
	/**
	 * Returns last name of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return last name of offender if association exists
	 */
	public String getOffenderLastName() {
		return this.offenderLastName;
	}
	
	/**
	 * Returns first name of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return first name of offender if association exists
	 */
	public String getOffenderFirstName() {
		return this.offenderFirstName;
	}
	
	/**
	 * Returns middle name of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return middle name of offender if association exists
	 */
	public String getOffenderMiddleName() {
		return this.offenderMiddleName;
	}
	
	/**
	 * Returns suffix of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return suffix of offender if association exists
	 */
	public String getOffenderSuffix() {
		return this.offenderSuffix;
	}
	
	/**
	 * Returns offender number of offender if association exists.
	 * 
	 * <p>Returns {@code null} if {@code this#getAssociationExists()}
	 * returns {@code null} or {@code false}.
	 * 
	 * @return offender number of offender if association exists
	 */
	public Integer getOffenderNumber() {
		return this.offenderNumber;
	}
	
	/**
	 * Returns date.
	 * 
	 * @return date
	 */
	public Date getDate() {
		return this.date;
	}
	
	/**
	 * Returns value.
	 * 
	 * @return value
	 */
	public String getValue() {
		return this.value;
	}
}