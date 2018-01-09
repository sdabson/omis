package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.OtherPertinentInformationSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummary;
import omis.presentenceinvestigation.domain.OtherPertinentInformationSectionSummaryNote;

/**
 * OtherPertinentInformationSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 19, 2017)
 *@since OMIS 3.0
 *
 */
public class OtherPertinentInformationSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"OtherPertinentInformationSectionSummaryNote already exists with "
			+ "given Date and Description for the specified "
			+ "OtherPertinentInformationSectionSummary.";
	
	private final OtherPertinentInformationSectionSummaryNoteDao
		otherPertinentInformationSectionSummaryNoteDao;
	
	private final InstanceFactory<OtherPertinentInformationSectionSummaryNote> 
		otherPertinentInformationSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for OtherPertinentInformationSectionSummaryNoteDelegate
	 * @param otherPertinentInformationSectionSummaryNoteDao
	 * @param otherPertinentInformationSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public OtherPertinentInformationSectionSummaryNoteDelegate(
			final OtherPertinentInformationSectionSummaryNoteDao
				otherPertinentInformationSectionSummaryNoteDao,
			final InstanceFactory<OtherPertinentInformationSectionSummaryNote> 
				otherPertinentInformationSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.otherPertinentInformationSectionSummaryNoteDao =
				otherPertinentInformationSectionSummaryNoteDao;
		this.otherPertinentInformationSectionSummaryNoteInstanceFactory =
				otherPertinentInformationSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an OtherPertinentInformationSectionSummaryNote with the
	 * specified properties
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @param description - String
	 * @param date - Date
	 * @return Newly created OtherPertinentInformationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When an
	 * OtherPertinentInformationSectionSummaryNote already exists with given 
	 * Date and Description for the specified
	 * OtherPertinentInformationSectionSummary
	 */
	public OtherPertinentInformationSectionSummaryNote create(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.otherPertinentInformationSectionSummaryNoteDao.find(
				otherPertinentInformationSectionSummary,
				description, date) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		OtherPertinentInformationSectionSummaryNote
			otherPertinentInformationSectionSummaryNote = 
				this.otherPertinentInformationSectionSummaryNoteInstanceFactory
				.createInstance();
		
		otherPertinentInformationSectionSummaryNote
			.setOtherPertinentInformationSectionSummary(
				otherPertinentInformationSectionSummary);
		otherPertinentInformationSectionSummaryNote.setDate(date);
		otherPertinentInformationSectionSummaryNote.setDescription(description);
		otherPertinentInformationSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		otherPertinentInformationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.otherPertinentInformationSectionSummaryNoteDao
				.makePersistent(otherPertinentInformationSectionSummaryNote);
	}
	
	/**
	 * Updates an OtherPertinentInformationSectionSummaryNote with the
	 * specified properties
	 * @param otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated OtherPertinentInformationSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When another
	 * OtherPertinentInformationSectionSummaryNote already exists with given 
	 * Date and Description for the specified
	 * OtherPertinentInformationSectionSummaryNote's
	 * OtherPertinentInformationSectionSummary
	 */
	public OtherPertinentInformationSectionSummaryNote update(
			final OtherPertinentInformationSectionSummaryNote
				otherPertinentInformationSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.otherPertinentInformationSectionSummaryNoteDao.findExcluding(
				otherPertinentInformationSectionSummaryNote
				.getOtherPertinentInformationSectionSummary(), description,
				date, otherPertinentInformationSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		otherPertinentInformationSectionSummaryNote.setDate(date);
		otherPertinentInformationSectionSummaryNote.setDescription(description);
		otherPertinentInformationSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.otherPertinentInformationSectionSummaryNoteDao
				.makePersistent(otherPertinentInformationSectionSummaryNote);
	}
	
	/**
	 * Removes an OtherPertinentInformationSectionSummaryNote
	 * @param otherPertinentInformationSectionSummaryNote -
	 * OtherPertinentInformationSectionSummaryNote to remove
	 */
	public void remove(final OtherPertinentInformationSectionSummaryNote
			otherPertinentInformationSectionSummaryNote){
		this.otherPertinentInformationSectionSummaryNoteDao.makeTransient(
				otherPertinentInformationSectionSummaryNote);
	}
	
	/**
	 * Returns a list of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 * @param otherPertinentInformationSectionSummary -
	 * OtherPertinentInformationSectionSummary
	 * @return List of all OtherPertinentInformationSectionSummaryNotes found
	 * with specified OtherPertinentInformationSectionSummary
	 */
	public List<OtherPertinentInformationSectionSummaryNote>
		findByOtherPertinentInformationSectionSummary(
			final OtherPertinentInformationSectionSummary
				otherPertinentInformationSectionSummary){
		return this.otherPertinentInformationSectionSummaryNoteDao
				.findByOtherPertinentInformationSectionSummary(
						otherPertinentInformationSectionSummary);
	}
	
}
