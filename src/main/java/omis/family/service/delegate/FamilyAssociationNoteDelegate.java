package omis.family.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.family.dao.FamilyAssociationNoteDao;
import omis.family.domain.FamilyAssociation;
import omis.family.domain.FamilyAssociationNote;
import omis.instance.factory.InstanceFactory;

/**
 * Family association note delegate.
 * 
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (Oct 6, 2015)
 * @since OMIS 3.0
 */
public class FamilyAssociationNoteDelegate {
	/* Data access objects. */
	private FamilyAssociationNoteDao familyAssociationNoteDao;
	
	/* Instance factories. */
	private InstanceFactory<FamilyAssociationNote> 
		familyAssociationNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates a family association service implementation delegate with
	 * the specified data access object and instance factory.
	 * 
	 * @param familyAssociationNoteDao family association note 
	 * data access object
	 * @param familyAssociationNoteInstanceFactory family association 
	 * note instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public FamilyAssociationNoteDelegate(
			final FamilyAssociationNoteDao familyAssociationNoteDao,
			final InstanceFactory<FamilyAssociationNote> 
				familyAssociationNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.familyAssociationNoteDao = familyAssociationNoteDao;
		this.familyAssociationNoteInstanceFactory = 
				familyAssociationNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Management methods. */
	
	/**
	 * Add family association note.
	 * 
	 * @param association family association
	 * @param date date
	 * @param value note content
	 * @return a new family association note
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public FamilyAssociationNote create(final FamilyAssociation association, 
		final Date date, final String value) 
		throws DuplicateEntityFoundException {
		if (this.familyAssociationNoteDao.find(association, date) != null) {
			throw new DuplicateEntityFoundException(
				"Duplicate family association note found");
		}
		FamilyAssociationNote familyAssociationNote 
			= this.familyAssociationNoteInstanceFactory.createInstance(); 
		familyAssociationNote.setDate(date);
		familyAssociationNote.setAssociation(association);
		familyAssociationNote.setValue(value);
		CreationSignature creationSignature = new CreationSignature();
		creationSignature.setUserAccount(this.auditComponentRetriever
				.retrieveUserAccount());
		creationSignature.setDate(this.auditComponentRetriever.retrieveDate());
		familyAssociationNote.setCreationSignature(creationSignature);
		UpdateSignature updateSignature = new UpdateSignature();
		updateSignature.setUserAccount(this.auditComponentRetriever
				.retrieveUserAccount());
		updateSignature.setDate(this.auditComponentRetriever.retrieveDate());
		familyAssociationNote.setUpdateSignature(updateSignature);
		this.familyAssociationNoteDao.makePersistent(familyAssociationNote);
		return familyAssociationNote;
	}
	
	/**
	 * Update a family association note.
	 * 
	 * @param note family association note
	 * @param date date
	 * @param value note content
	 * @return an updated family association note
	 * @throws DuplicateEntityFoundException duplicate entity found exception
	 */
	public FamilyAssociationNote update(final FamilyAssociationNote note, 
		final Date date, final String value)
				throws DuplicateEntityFoundException {
		if (familyAssociationNoteDao.findExcluding(note.getAssociation(), date, 
			note) != null) {
			throw new DuplicateEntityFoundException(
				"Family association note already exist");
		}
		UpdateSignature updateSignature = new UpdateSignature();
		updateSignature.setUserAccount(this.auditComponentRetriever
				.retrieveUserAccount());
		updateSignature.setDate(this.auditComponentRetriever.retrieveDate());
		note.setUpdateSignature(updateSignature);
		note.setDate(date);
		note.setAssociation(note.getAssociation());
		note.setValue(value);
		this.familyAssociationNoteDao.makePersistent(note);
		return note;
	}
	
	/**
	 * Find family association notes by family association note.
	 * 
	 * @param association family association
	 * @return a found family association note
	 */
	public List<FamilyAssociationNote> findByAssociation(
		final FamilyAssociation	association) {
		return this.familyAssociationNoteDao.findByAssociation(association);
	}
	
	/**
	 * Remove a family association note.
	 * 
	 * @param note family association note
	 */
	public void remove(final FamilyAssociationNote note) {
		this.familyAssociationNoteDao.makeTransient(note);
	}
	
	/**
	 * Find existing family association .
	 * 
	 * @return A list of family association notes
	 */
	public List<FamilyAssociationNote> findExists() {
		return this.familyAssociationNoteDao.findAll();
	}	
}