
package omis.presentenceinvestigation.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.presentenceinvestigation.dao.ChemicalUseSectionSummaryNoteDao;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummary;
import omis.presentenceinvestigation.domain.ChemicalUseSectionSummaryNote;

/**
 * ChemicalUseSectionSummaryNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (May 6, 2017)
 *@since OMIS 3.0
 *
 */
public class ChemicalUseSectionSummaryNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"A Chemical Use Section Summary Note already exists with given date "
			+ "and description for specified Chemical Use Section Summary";
	
	private final ChemicalUseSectionSummaryNoteDao chemicalUseSectionSummaryNoteDao;
	
	private final InstanceFactory<ChemicalUseSectionSummaryNote> 
		chemicalUseSectionSummaryNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ChemicalUseSectionSummaryNoteDelegate
	 * @param chemicalUseSectionSummaryNoteDao
	 * @param chemicalUseSectionSummaryNoteInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ChemicalUseSectionSummaryNoteDelegate(
			final ChemicalUseSectionSummaryNoteDao chemicalUseSectionSummaryNoteDao,
			final InstanceFactory<ChemicalUseSectionSummaryNote> 
				chemicalUseSectionSummaryNoteInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.chemicalUseSectionSummaryNoteDao = chemicalUseSectionSummaryNoteDao;
		this.chemicalUseSectionSummaryNoteInstanceFactory = chemicalUseSectionSummaryNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	
	/**
	 * Creates a ChemicalUseSectionSummaryNote with specified properties
	 * @param description - String
	 * @param date - Date
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return Newly created ChemicalUseSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummaryNote
	 * already exists with all of the specified properties
	 */
	public ChemicalUseSectionSummaryNote create(final String description,
			final Date date,
			final ChemicalUseSectionSummary chemicalUseSectionSummary)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryNoteDao.find(description, date,
				chemicalUseSectionSummary) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote = 
				this.chemicalUseSectionSummaryNoteInstanceFactory.createInstance();
		
		chemicalUseSectionSummaryNote.setChemicalUseSectionSummary(
				chemicalUseSectionSummary);
		chemicalUseSectionSummaryNote.setDescription(description);
		chemicalUseSectionSummaryNote.setDate(date);
		chemicalUseSectionSummaryNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		chemicalUseSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryNoteDao.makePersistent(
				chemicalUseSectionSummaryNote);
	}
	
	/**
	 * Updates a ChemicalUseSectionSummaryNote with the specified properties
	 * @param chemicalUseSectionSummaryNote - ChemicalUseSectionSummaryNote
	 * to update
	 * @param description - String
	 * @param date - Date
	 * @return Updated ChemicalUseSectionSummaryNote
	 * @throws DuplicateEntityFoundException - When a ChemicalUseSectionSummaryNote
	 * already exists with specified Description and Date for its
	 * ChemicalUseSectionSummary
	 */
	public ChemicalUseSectionSummaryNote update(
			final ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote,
			final String description, final Date date)
					throws DuplicateEntityFoundException{
		if(this.chemicalUseSectionSummaryNoteDao.findExcluding(description,
				date, chemicalUseSectionSummaryNote.getChemicalUseSectionSummary(),
				chemicalUseSectionSummaryNote) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		chemicalUseSectionSummaryNote.setDescription(description);
		chemicalUseSectionSummaryNote.setDate(date);
		chemicalUseSectionSummaryNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.chemicalUseSectionSummaryNoteDao.makePersistent(
				chemicalUseSectionSummaryNote);
	}
	
	/**
	 * Removes a ChemicalUseSectionSummaryNote
	 * @param chemicalUseSectionSummaryNote - ChemicalUseSectionSummaryNote to
	 * remove
	 */
	public void remove(
			final ChemicalUseSectionSummaryNote chemicalUseSectionSummaryNote){
		this.chemicalUseSectionSummaryNoteDao.makeTransient(
				chemicalUseSectionSummaryNote);
	}
	
	/**
	 * Returns a list of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 * @param chemicalUseSectionSummary - ChemicalUseSectionSummary
	 * @return List of all ChemicalUseSectionSummaryNotes found with
	 * specified ChemicalUseSectionSummary
	 */
	public List<ChemicalUseSectionSummaryNote> findByChemicalUseSectionSummary(
			final ChemicalUseSectionSummary chemicalUseSectionSummary){
		return this.chemicalUseSectionSummaryNoteDao
				.findByChemicalUseSectionSummary(chemicalUseSectionSummary);
	}
	
}
