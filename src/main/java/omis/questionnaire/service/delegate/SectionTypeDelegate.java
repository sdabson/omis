package omis.questionnaire.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.questionnaire.dao.SectionTypeDao;
import omis.questionnaire.domain.SectionType;

/**
 * SectionTypeDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Oct 20, 2016)
 *@since OMIS 3.0
 *
 */
public class SectionTypeDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Section Type Already Exists With Given Description";
	
	private final SectionTypeDao sectionTypeDao;
	
	private final InstanceFactory<SectionType> 
		sectionTypeInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for SectionTypeDelegate
	 * @param sectionTypeDao
	 * @param sectionTypeInstanceFactory
	 * @param auditComponentRetriever
	 */
	public SectionTypeDelegate(
			final SectionTypeDao sectionTypeDao,
			final InstanceFactory<SectionType> 
				sectionTypeInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.sectionTypeDao = sectionTypeDao;
		this.sectionTypeInstanceFactory = sectionTypeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a SectionType
	 * @param description - String
	 * @param valid - Boolean
	 * @return SectionType - Newly Created SectionType
	 * @throws DuplicateEntityFoundException - when SectionType already exists
	 * with given Description
	 */
	public SectionType create(final String description, final Boolean valid)
			throws DuplicateEntityFoundException{
		if(this.sectionTypeDao.find(description) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		SectionType sectionType = 
				this.sectionTypeInstanceFactory.createInstance();
		
		sectionType.setDescription(description);
		sectionType.setValid(valid);
		sectionType.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		sectionType.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.sectionTypeDao.makePersistent(sectionType);
	}
	
	/**
	 * Updates a SectionType
	 * @param sectionType - SectionType to update
	 * @param description - String
	 * @param valid - Boolean
	 * @return SectionType - Updated SectionType
	 * @throws DuplicateEntityFoundException - when SectionType already exists
	 * with given Description
	 */
	public SectionType update(final SectionType sectionType, 
			final String description, final Boolean valid)
			throws DuplicateEntityFoundException{
		if(this.sectionTypeDao.findExcluding(description, sectionType) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		sectionType.setDescription(description);
		sectionType.setValid(valid);
		sectionType.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.sectionTypeDao.makePersistent(sectionType);
	}
	
	/**
	 * Removes a Section Type
	 * @param sectionType - SectionType to remove
	 */
	public void remove(final SectionType sectionType){
		this.sectionTypeDao.makeTransient(sectionType);
	}
	
	/**
	 * Returns a list of all valid SectionTypes
	 * @return List of all valid SectionTypes
	 */
	public List<SectionType> findAll(){
		return this.sectionTypeDao.findAll();
	}
	
}
