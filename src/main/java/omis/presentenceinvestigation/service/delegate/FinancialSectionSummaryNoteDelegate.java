package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.FinancialSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.FinancialSectionSummary;
import omis.presentenceinvestigation.domain.FinancialSectionSummaryNote;

/**
 * FinancialSectionSummaryNoteDelegate
 * 
 *@author Trevor Isles 
 *@version 0.1.0 (July 20, 2017)
 *@since OMIS 3.0
 *
 */
public class FinancialSectionSummaryNoteDelegate {

	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Financial Section Summary Note already exists with given date "
			+ "and description for specified Financial Section Summary";
	
	private final FinancialSectionSummaryNoteDao financialSectionSummaryNoteDao;
	
	private final InstanceFactory<FinancialSectionSummaryNote> 
		financialSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for FinancialSectionSummaryNoteDelegate
	 * @param financialSectionSummaryNoteDao
	 * @param financialSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public FinancialSectionSummaryNoteDelegate(
			final FinancialSectionSummaryNoteDao financialSectionSummaryNoteDao,
			final InstanceFactory<FinancialSectionSummaryNote> 
				financialSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.financialSectionSummaryNoteDao = financialSectionSummaryNoteDao;
		this.financialSectionSummaryNoteInstanceFactory 
			= financialSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a FinancialSectionSummaryNote with specified properties
	 * @param description - String
	 * @param date - Date
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return Newly created FinancialSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummaryNote
	 * already exists with all of the specified properties
	 */
	public FinancialSectionSummaryNote create(final String description,
			final Date date,
			final FinancialSectionSummary financialSectionSummary)
					throws DuplicateEntityFoundException{
		if(this.financialSectionSummaryNoteDao.find(description, date,
				financialSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		FinancialSectionSummaryNote financialSectionSummaryNote = 
				this.financialSectionSummaryNoteInstanceFactory.createInstance();
		
		financialSectionSummaryNote.setFinancialSectionSummary(
				financialSectionSummary);
		financialSectionSummaryNote.setDescription(description);
		financialSectionSummaryNote.setDate(date);
		financialSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		financialSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialSectionSummaryNoteDao.makePersistent(
				financialSectionSummaryNote);
	}
	
	/**
	 * Updates a FinancialSectionSummaryNote with the specified properties
	 * @param financialSectionSummaryNote - FinancialSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated FinancialSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a FinancialSectionSummaryNote
	 * already exists with specified Description and Date for its
	 * FinancialSectionSummary
	 */
	public FinancialSectionSummaryNote update(
			final FinancialSectionSummaryNote financialSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.financialSectionSummaryNoteDao.findExcluding(description,
				date, financialSectionSummaryNote.getFinancialSectionSummary(),
				financialSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		financialSectionSummaryNote.setDescription(description);
		financialSectionSummaryNote.setDate(date);
		financialSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.financialSectionSummaryNoteDao.makePersistent(
				financialSectionSummaryNote);
	}
	
	/**
	 * Removes a FinancialSectionSummaryNote
	 * @param financialSectionSummaryNote - FinancialSectionSummaryNote to
	 * remove
	 */
	public void remove(
			final FinancialSectionSummaryNote financialSectionSummaryNote){
		this.financialSectionSummaryNoteDao.makeTransient(
				financialSectionSummaryNote);
	}
	
	/**
	 * Returns a list of all FinancialSectionSummaryNotes found with
	 * specified FinancialSectionSummary
	 * @param financialSectionSummary - FinancialSectionSummary
	 * @return List of all FinancialSectionSummaryNotes found with
	 * specified FinancialSectionSummary
	 */
	public List<FinancialSectionSummaryNote> findByFinancialSectionSummary(
			final FinancialSectionSummary financialSectionSummary){
		return this.financialSectionSummaryNoteDao
				.findByFinancialSectionSummary(financialSectionSummary);
	}
	
}
