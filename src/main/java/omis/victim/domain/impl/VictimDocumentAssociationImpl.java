package omis.victim.domain.impl;

import omis.audit.domain.CreationSignature;
import omis.docket.domain.Docket;
import omis.document.domain.Document;
import omis.person.domain.Person;
import omis.victim.domain.VictimDocumentAssociation;

/**
 * Victim document association implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 22, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentAssociationImpl implements VictimDocumentAssociation {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Person victim;
	private Document document;
	private Docket docket;
	private CreationSignature creationSignature;
	
	/**
	 * Instantiates a default instance of victim document association.
	 */
	public VictimDocumentAssociationImpl() {
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
	public Document getDocument() {
		return this.document;
	}

	/** {@inheritDoc} */
	@Override
	public void setDocument(final Document document) {
		this.document = document;
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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}	
		if (!(o instanceof VictimDocumentAssociation)) {
			return false;
		}
		
		VictimDocumentAssociation that = (VictimDocumentAssociation) o;
		
		if (this.getVictim() == null) {
			throw new IllegalStateException("Victim required.");
		}
		if (!this.getVictim().equals(that.getVictim())) {
			return false;
		}
		if (this.getDocument() == null) {
			throw new IllegalStateException("Document required");
		}
		if (!this.getDocument().equals(that.getDocument())) {
			return false;
		}
		return true;
	}
}
