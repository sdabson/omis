package omis.disciplinaryCode.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.disciplinaryCode.dao.DisciplinaryCodeDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * DisciplinaryCodeDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.1 (Sep 1, 2017)
 *@since OMIS 3.0
 *
 */
public class DisciplinaryCodeDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
		= "Disciplinary code already exists with given value.";
	
	private final DisciplinaryCodeDao disciplinaryCodeDao;
	
	private final InstanceFactory<DisciplinaryCode> disciplinaryCodeInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for disciplinary code delegate
	 * @param disciplinaryCodeDao - disciplinary code dao
	 * @param disciplinaryCodeInstanceFactory - disciplinary code instance factory
	 * @param auditComponentRetriever - audit componenet retriever
	 */
	public DisciplinaryCodeDelegate(final DisciplinaryCodeDao disciplinaryCodeDao,
			final InstanceFactory<DisciplinaryCode> disciplinaryCodeInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.disciplinaryCodeDao = disciplinaryCodeDao;
		this.disciplinaryCodeInstanceFactory = disciplinaryCodeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a disciplinary code
	 * @param value - value
	 * @param description - description
	 * @param extendedDescription - extended description
	 * @return newly created disciplinary code
	 * @throws DuplicateEntityFoundException - when disciplinary code already
	 * exists with given value
	 */
	public DisciplinaryCode create(final String value, final String description,
			final String extendedDescription)
			throws DuplicateEntityFoundException{
		if(this.disciplinaryCodeDao.find(value) != null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		DisciplinaryCode disciplinaryCode = this.disciplinaryCodeInstanceFactory
				.createInstance();
		
		disciplinaryCode.setValue(value);
		disciplinaryCode.setDescription(description);
		disciplinaryCode.setExtendedDescription(extendedDescription);
		disciplinaryCode.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		disciplinaryCode.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.disciplinaryCodeDao.makePersistent(disciplinaryCode);
		
	}
	
	/**
	 * Updates a disciplinary code
	 * @param disciplinaryCode - disciplinary code to update
	 * @param value - value
	 * @param description - description
	 * @param extendedDescription - extended description
	 * @return Updated disciplinary code
	 * @throws DuplicateEntityFoundException - when disciplinary code already
	 * exists with given value
	 */
	public DisciplinaryCode update(final DisciplinaryCode disciplinaryCode,
			final String value, final String description,
			final String extendedDescription)
					throws DuplicateEntityFoundException{
		if(this.disciplinaryCodeDao.findExcluding(value, disciplinaryCode) 
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		disciplinaryCode.setValue(value);
		disciplinaryCode.setDescription(description);
		disciplinaryCode.setExtendedDescription(extendedDescription);
		disciplinaryCode.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.disciplinaryCodeDao.makePersistent(disciplinaryCode);
	}
	
	/**
	 * Removes a disciplinary code
	 * @param disciplinaryCode - disciplinary code to remove
	 */
	public void remove(final DisciplinaryCode disciplinaryCode){
		this.disciplinaryCodeDao.makeTransient(disciplinaryCode);
	}
	
	/**
	 * Returns a list of disciplinary codes with the specified value
	 * @param value - value
	 * @return list of disciplinary codes with the specified value
	 */
	public List<DisciplinaryCode> findQuery(final String value){
		return this.disciplinaryCodeDao.search(value);
	}
	
	/**
	 * Returns a list of disciplinary codes valid for specified Supervisory 
	 * Organization on specified date
	 * @param supervisoryOrganization - SupervisoryOrganization
	 * @param effectiveDate - Date
	 * @return  list of disciplinary codes valid for specified Supervisory 
	 * Organization on specified date
	 */
	public List<DisciplinaryCode>  findBySupervisoryOrganizationAndDate(
			final SupervisoryOrganization supervisoryOrganization,
			final Date effectiveDate){
		return this.disciplinaryCodeDao.findBySupervisoryOrganizationAndDate(
				supervisoryOrganization, effectiveDate);
	}
}
