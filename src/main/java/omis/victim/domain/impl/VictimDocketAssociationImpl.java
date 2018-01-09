package omis.victim.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocketAssociation;

/**
 * Victim docket association implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 08, 2017)
 * @since OMIS 3.0
 */
public class VictimDocketAssociationImpl implements VictimDocketAssociation {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Person victim;
	private Docket docket;
	private String victimImpactSummary;
	private CreationSignature creationSignature;
	private UpdateSignature updateSignature;
	
	/**
	 * Instantiates a default instance of victim docket association.
	 */
	public VictimDocketAssociationImpl() {
		//Default constructor.
	}
	
	/** {@inheritDoc} */
	@Override
	public Long getId() {
		return this.id;
	}

	/** {@inheritDoc} */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public Person getVictim() {
		return this.victim;
	}

	/** {@inheritDoc} */
	@Override
	public void setVictim(final Person victim) {
		this.victim = victim;
	}

	/** {@inheritDoc} */
	@Override
	public Docket getDocket() {
		return this.docket;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocket(final Docket docket) {
		this.docket = docket;
	}

	/** {@inheritDoc} */
	@Override
	public String getVictimImpactSummary() {
		return this.victimImpactSummary;
	}

	/** {@inheritDoc} */
	@Override
	public void setVictimImpactSummary(final String victimImpactSummary) {
		this.victimImpactSummary = victimImpactSummary;
	}
	
	/** {@inheritDoc} */
	@Override
	public CreationSignature getCreationSignature() {
		return this.creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setCreationSignature(final CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public UpdateSignature getUpdateSignature() {
		return this.updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public void setUpdateSignature(final UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof VictimDocketAssociation)) {
			return false;
		}
		
		VictimDocketAssociation that = (VictimDocketAssociation) o;
		
		if (this.getVictim() == null) {
			throw new IllegalStateException("Victim required.");
		}
		if (!this.getVictim().equals(that.getVictim())) {
			return false;
		}
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required");
		}
		if (!this.getDocket().equals(that.getDocket())) {
			return false;
		} 
		
		return true;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		if (this.getVictim() == null) {
			throw new IllegalStateException("Victim required.");
		}
		if (this.getDocket() == null) {
			throw new IllegalStateException("Docket required");
		}
		
		int hashCode = 14;
		
		hashCode = 29 * hashCode + this.getVictim().hashCode();
		hashCode = 29 * hashCode + this.getDocket().hashCode();
		
		return hashCode;
	}
}
