package omis.education.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.education.dao.EducationNoteDao;
import omis.education.domain.EducationNote;
import omis.education.domain.EducationTerm;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;

/**
 * EducationNoteDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Jul 26, 2016)
 *@since OMIS 3.0
 *
 */
public class EducationNoteDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG = "Education note "
			+ "already exists for this education term.";
	
	private final EducationNoteDao educationNoteDao;
	
	private final InstanceFactory<EducationNote> educationNoteInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor
	 * @param educationNoteDao - education note dao
	 * @param educationNoteInstanceFactory - education note instance factory
	 * @param auditComponentRetriever - audit component retriever
	 */
	public EducationNoteDelegate(EducationNoteDao educationNoteDao,
			InstanceFactory<EducationNote> educationNoteInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.educationNoteDao = educationNoteDao;
		this.educationNoteInstanceFactory = educationNoteInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new education note
	 * @param educationTerm - education note
	 * @param date - date
	 * @param description - description
	 * @return newly created education note
	 * @throws DuplicateEntityFoundException - when education note already
	 * exists with given date and description for specified education term
	 */
	public EducationNote create(final EducationTerm educationTerm, final Date date, 
			final String description)
					throws DuplicateEntityFoundException{
		if(this.educationNoteDao.find(description, educationTerm, date) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		EducationNote educationNote = 
				this.educationNoteInstanceFactory.createInstance();
		
		educationNote.setDate(date);
		educationNote.setDescription(description);
		educationNote.setEducationTerm(educationTerm);
		educationNote.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		educationNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationNoteDao.makePersistent(educationNote);
	
	}
	
	/**
	 * Updates an existing education note
	 * @param educationNote - education note to update
	 * @param date - date
	 * @param description - description
	 * @return newly created education note
	 * @throws DuplicateEntityFoundException - when education note already
	 * exists with given date and description for its education term
	 */
	public EducationNote update(final EducationNote educationNote, final Date date, 
			final String description)
					throws DuplicateEntityFoundException{
		if(this.educationNoteDao.findExcluding(description, 
				educationNote.getEducationTerm(),
				date, 
				educationNote)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		educationNote.setDate(date);
		educationNote.setDescription(description);
		educationNote.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.educationNoteDao.makePersistent(educationNote);
	
	}
	
	/**
	 * Removes an education note
	 * @param educationNote - education note to remove
	 */
	public void remove(final EducationNote educationNote){
		this.educationNoteDao.makeTransient(educationNote);
	}
	
	
	/**
	 * Finds and returns a list of all education notes by specified education term
	 * @param educationTerm - education term
	 * @return list of all education notes by specified education term
	 */
	public List<EducationNote> findByEducationTerm(final EducationTerm educationTerm){
		return this.educationNoteDao.findByEducationTerm(educationTerm);
	}
}
