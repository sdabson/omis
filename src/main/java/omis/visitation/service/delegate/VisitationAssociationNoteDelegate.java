package omis.visitation.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.visitation.dao.VisitationAssociationNoteDao;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationNote;

/**
 * Visitation association note delegate.
 * 
 * @author Joel Norrsi
 * @version 0.1.0 (Feb 10, 2017)
 * @since OMIS 3.0
 */
public class VisitationAssociationNoteDelegate {

	/* Data access objects. */
	VisitationAssociationNoteDao visitationAssociationNoteDao;
	
	/* Instance factories. */
	InstanceFactory<VisitationAssociationNote>
		visitationAssociationNoteInstanceFactory;
	
	/* Component retriever. */
	AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an instance of visitation association note delegate with
	 * the specified data access object, instance factory and component
	 * retriever.
	 * @param visitaitonAssociationNoteDao
	 * @param visitationAssociationNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public VisitationAssociationNoteDelegate(
			final VisitationAssociationNoteDao visitaitonAssociationNoteDao,
			final InstanceFactory<VisitationAssociationNote>
			visitationAssociationNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.visitationAssociationNoteDao = visitaitonAssociationNoteDao;
		this.visitationAssociationNoteInstanceFactory
			= visitationAssociationNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Public delegate methods. */
	
	/**
	 * Creates a new visitation association note with the specified values.
	 * 
	 * @param association visitation association
	 * @param date date
	 * @param value value
	 * @return newly created visitation association note
	 * @throws DuplicateEntityFoundException thrown when a duplicate visitation
	 * association note is found
	 */
	public VisitationAssociationNote create(
			final VisitationAssociation association, final Date date,
			final String value) throws DuplicateEntityFoundException {
		if (this.visitationAssociationNoteDao.find(association, date, value)
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate visitation association note found");
		}
		VisitationAssociationNote note
			= this.visitationAssociationNoteInstanceFactory.createInstance();
		note.setAssociation(association);
		note.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		this.populateNote(note, date, value);
		return this.visitationAssociationNoteDao.makePersistent(note);
	}
	
	/**
	 * Updates the specified visitation association note with the specified date
	 * and value.
	 * 
	 * @param note visitation association note
	 * @param date date
	 * @param value value
	 * @return updated visitation association note
	 * @throws DuplicateEntityFoundException
	 */
	public VisitationAssociationNote update(
			final VisitationAssociationNote note, final Date date,
			final String value) throws DuplicateEntityFoundException {
		if (this.visitationAssociationNoteDao.findExcluding(
				note.getAssociation(), date, value, note) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate visitation association note found.");
		}
		this.populateNote(note, date, value);
		return this.visitationAssociationNoteDao.makePersistent(note);
	}
	
	/* Helper methods. */
	
	private VisitationAssociationNote populateNote(
			final VisitationAssociationNote note, final Date date,
			final String value) {
		note.setDate(date);
		note.setValue(value);
		note.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return note;
	}
}