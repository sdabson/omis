package omis.victim.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.relationship.domain.Relationship;
import omis.victim.domain.VictimAssociation;
import omis.victim.domain.component.VictimAssociationFlags;

/**
 * Implementation of victim association.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 20, 2015)
 * @since OMIS 3.0
 */
public class VictimAssociationImpl
		implements VictimAssociation {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Relationship relationship;

	private CreationSignature creationSignature;

	private UpdateSignature updateSignature;

	private Date registerDate;

	private Boolean packetSent;

	private Date packetSentDate;

	private VictimAssociationFlags flags;

	/** Instantiates default implementation of victim association. */
	public VictimAssociationImpl() {
		// Default instantiation
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}
	
	/** {@inheritDoc} */
	@Override
	public void setRelationship(
			final Relationship relationship) {
		this.relationship = relationship;
	}

	/** {@inheritDoc} */
	@Override
	public Relationship getRelationship() {
		return this.relationship;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(
			final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(
			final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setRegisterDate(final Date registeredDate) {
		this.registerDate = registeredDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getRegisterDate() {
		return this.registerDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setPacketSent(final Boolean packetSent) {
		this.packetSent = packetSent;
	}

	/** {@inheritDoc} */
	@Override
	public Boolean getPacketSent() {
		return this.packetSent;
	}

	/** {@inheritDoc} */
	@Override
	public void setPacketSendDate(final Date packetSentDate) {
		this.packetSentDate = packetSentDate;
	}

	/** {@inheritDoc} */
	@Override
	public Date getPacketSendDate() {
		return this.packetSentDate;
	}

	/** {@inheritDoc} */
	@Override
	public void setFlags(final VictimAssociationFlags flags) {
		this.flags = flags;
	}

	/** {@inheritDoc} */
	@Override
	public VictimAssociationFlags getFlags() {
		return this.flags;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof VictimAssociation)) {
			return false;
		}
		VictimAssociation that = (VictimAssociation) obj;
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		if (!this.getRelationship().equals(that.getRelationship())) {
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getRelationship() == null) {
			throw new IllegalStateException("Relationship required");
		}
		int hashCode = 14;
		hashCode = 29 * hashCode + this.getRelationship().hashCode();
		return hashCode;
	}
}