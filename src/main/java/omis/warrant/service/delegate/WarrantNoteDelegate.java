package omis.warrant.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.warrant.dao.WarrantNoteDao;
import omis.warrant.domain.Warrant;
import omis.warrant.domain.WarrantNote;

/**
 * WarrantNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 8, 2017)
 *@since OMIS 3.0
 *
 */
/**
 * WarrantNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 9, 2017)
 *@since OMIS 3.0
 *
 */
public class WarrantNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A WarrantNote already exists with given Note and Date for the specified Warrant.";
	
	private final WarrantNoteDao warrantNoteDao;
	
	private final InstanceFactory<WarrantNote> 
		warrantNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for WarrantNoteDelegate
	 * @param warrantNoteDao
	 * @param warrantNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public WarrantNoteDelegate(
			final WarrantNoteDao warrantNoteDao,
			final InstanceFactory<WarrantNote> 
				warrantNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.warrantNoteDao = warrantNoteDao;
		this.warrantNoteInstanceFactory = warrantNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a WarrantNote with the specified properties
	 * @param warrant - Warrant
	 * @param note - String
	 * @param date - Date
	 * @return Newly created WarrantNote
	 * @throws DuplicateEntityFoundException - When a WarrantNote already exists
	 * with the given Note and Date for the specified Warrant
	 */
	public WarrantNote create(final Warrant warrant, final String note,
			final Date date)
				throws DuplicateEntityFoundException{
		if(this.warrantNoteDao.find(note, date, warrant) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		WarrantNote warrantNote = 
				this.warrantNoteInstanceFactory.createInstance();
		
		warrantNote.setWarrant(warrant);
		warrantNote.setDate(date);
		warrantNote.setNote(note);
		warrantNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		warrantNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantNoteDao.makePersistent(warrantNote);
	}
	
	/**
	 * Updates a WarrantNote with the specified properties
	 * @param warrantNote - WarrantNote to update
	 * @param note - String
	 * @param date - Date
	 * @return Updated WarrantNote
	 * @throws DuplicateEntityFoundException - When a WarrantNote already exists
	 * with the given Note and Date for the specified Warrant
	 */
	public WarrantNote update(final WarrantNote warrantNote, final String note,
			final Date date)
				throws DuplicateEntityFoundException{
		if(this.warrantNoteDao.findExcluding(
				note, date, warrantNote.getWarrant(), warrantNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		warrantNote.setDate(date);
		warrantNote.setNote(note);
		warrantNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.warrantNoteDao.makePersistent(warrantNote);
	}
	
	/**
	 * Removes a WarrantNote
	 * @param warrantNote - WarrantNote to remove
	 */
	public void remove(final WarrantNote warrantNote){
		this.warrantNoteDao.makeTransient(warrantNote);
	}
	
	/**
	 * Returns a list of all WarrantNotes with specified Warrant
	 * @param warrant - Warrant
	 * @return List of all WarrantNotes with specified Warrant
	 */
	public List<WarrantNote> findByWarrant(final Warrant warrant){
		return this.warrantNoteDao.findByWarrant(warrant);
	}
	
}
