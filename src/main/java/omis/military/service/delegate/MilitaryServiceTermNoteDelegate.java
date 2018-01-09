package omis.military.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.military.dao.MilitaryServiceTermNoteDao;
import omis.military.domain.MilitaryServiceTerm;
import omis.military.domain.MilitaryServiceTermNote;

public class MilitaryServiceTermNoteDelegate {
	
	/* Instance factories. */
	
	private final InstanceFactory<MilitaryServiceTermNote>
	militaryServiceTermNoteInstanceFactory;
	
	/* DAOs. */
	
	private final MilitaryServiceTermNoteDao militaryServiceTermNoteDao;
	
	/* Audit component retriever. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/**
	 * Instantiates delegate for military service term notes.
	 * 
	 * @param militaryServiceTermNoteInstanceFactory instance factory for
	 * military service term notes.
	 * @param militaryServiceTermNoteDao data access object for military service
	 * term notes.
	 */
	
	public MilitaryServiceTermNoteDelegate(
			final InstanceFactory<MilitaryServiceTermNote>
				militaryServiceTermNoteInstanceFactory,
			final MilitaryServiceTermNoteDao militaryServiceTermNoteDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.militaryServiceTermNoteInstanceFactory
			= militaryServiceTermNoteInstanceFactory;
		this.militaryServiceTermNoteDao = militaryServiceTermNoteDao;
		this.auditComponentRetriever = auditComponentRetriever;
		
		}
	
	/** Creates a military service term note.
	 * @param Military Service Term - militaryServiceTerm
	 * @param Date - startDate.
	 * @param Note note
	 * @param  - .
	 * @return military service term note. 
	 * @throws DuplicateEntityFoundException - when service term note
	 *  already exists. 
	 *  */

	public MilitaryServiceTermNote create(
			final MilitaryServiceTerm serviceTerm, final Date date, 
			final String note) throws DuplicateEntityFoundException {
		if (this.militaryServiceTermNoteDao.find(serviceTerm, note, date) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate military service term note found");
		}
		MilitaryServiceTermNote serviceTermNote =
			this.militaryServiceTermNoteInstanceFactory.createInstance();
		serviceTermNote.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		serviceTermNote.setServiceTerm(serviceTerm);
		this.populateServiceTermNote(serviceTermNote, date, note);
		return this.militaryServiceTermNoteDao.makePersistent(serviceTermNote);
	}	
	
	/** Updates a military service term note.
	 * @param Military Service Term - militaryServiceTerm
	 * @param Date - startDate.
	 * @param Note note
	 * @param  - .
	 * @return updated military service term note. 
	 * @throws DuplicateEntityFoundException - when military service term note
	 *  already exists. 
	 *  */
	
	public MilitaryServiceTermNote update(
			MilitaryServiceTermNote serviceTermNote, Date date, String note)
		throws DuplicateEntityFoundException {
		if (this.militaryServiceTermNoteDao.findExcluding(serviceTermNote, 
				serviceTermNote.getServiceTerm(), note, date) != null) {
		throw new DuplicateEntityFoundException(
					"Duplicate military service term note found");
		}
		this.populateServiceTermNote(serviceTermNote, date, note);
		return this.militaryServiceTermNoteDao.makePersistent(serviceTermNote);
	}
	
	/** Removes a military service term note.
	 * @param Military Service Term - militaryServiceTerm
	 * @param Date - startDate.
	 * @param Note note
	 * @param  - .
	 * @return remove a military service term note. 
	 * @throws DuplicateEntityFoundException - when a military service term note
	 *  already exists. 
	 *  */
	
	public void remove(final MilitaryServiceTermNote serviceTermNote) {
		this.militaryServiceTermNoteDao.makeTransient(serviceTermNote);
	}
	
		/*
		 * Populates the specified military service term note.
		 * 
		 * @param date date
		 * @param note note
		 * @return populated military service term note
		 */
		private MilitaryServiceTermNote populateServiceTermNote(
				final MilitaryServiceTermNote serviceTermNote, final Date date,
				final String note) {
			serviceTermNote.setDate(date);
			serviceTermNote.setNote(note);
			serviceTermNote.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
			return serviceTermNote;
		}

		public List<MilitaryServiceTermNote> findByServiceTerm
		(MilitaryServiceTerm serviceTerm) {
			return this.militaryServiceTermNoteDao.findByServiceTerm(serviceTerm);

		}
	}