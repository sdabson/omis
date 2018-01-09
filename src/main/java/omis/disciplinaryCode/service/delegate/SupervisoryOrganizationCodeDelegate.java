package omis.disciplinaryCode.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.disciplinaryCode.dao.SupervisoryOrganizationCodeDao;
import omis.disciplinaryCode.domain.DisciplinaryCode;
import omis.disciplinaryCode.domain.SupervisoryOrganizationCode;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.supervision.domain.SupervisoryOrganization;

/**
 * SupervisoryOrganizationCodeDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Aug 9, 2016)
 *@since OMIS 3.0
 *
 */
public class SupervisoryOrganizationCodeDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG 
	= "Supervisory organization code already exists for given supervisory "
			+ "organization.";

	private final SupervisoryOrganizationCodeDao supervisoryOrganizationCodeDao;
	
	private final InstanceFactory<SupervisoryOrganizationCode> 
		supervisoryOrganizationCodeInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for supervisory organization code delegate
	 * @param supervisoryOraganizationCodeDao - supervisory organization code dao
	 * @param supervisoryOrganizationCodeInstanceFactory - supervisory 
	 * organization code instance factory
	 * @param auditComponentRetriever - audit component retriever
	 * 
	 */
	public SupervisoryOrganizationCodeDelegate(
			SupervisoryOrganizationCodeDao supervisoryOrganizationCodeDao,
			InstanceFactory<SupervisoryOrganizationCode> 
				supervisoryOrganizationCodeInstanceFactory,
			AuditComponentRetriever auditComponentRetriever) {
		this.supervisoryOrganizationCodeDao = supervisoryOrganizationCodeDao;
		this.supervisoryOrganizationCodeInstanceFactory 
			= supervisoryOrganizationCodeInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Returns a list of all supervisory organization codes
	 * @return list of all supervisory organization codes
	 */
	public List<SupervisoryOrganizationCode> findAll(){
		return this.supervisoryOrganizationCodeDao.findAll();
	}
	
	/**
	 * Creates a supervisory organization code
	 * @param supervisoryOrganization - supervisory organization
	 * @param dateRange - date range
	 * @param code - disciplinary code
	 * @return newly created supervisory organization code
	 * @throws DuplicateEntityFoundException - when supervisory organization
	 * code already exists for given supervisory organization
	 */
	public SupervisoryOrganizationCode create(
			final SupervisoryOrganization supervisoryOrganization, 
			final DateRange dateRange, final DisciplinaryCode code) 
					throws DuplicateEntityFoundException{
		if(this.supervisoryOrganizationCodeDao
				.find(supervisoryOrganization, code)
					!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		SupervisoryOrganizationCode supervisoryOrganizationCode = 
				this.supervisoryOrganizationCodeInstanceFactory.createInstance();
		
		supervisoryOrganizationCode.setSupervisoryOrganization(
				supervisoryOrganization);
		supervisoryOrganizationCode.setDateRange(dateRange);
		supervisoryOrganizationCode.setCode(code);
		supervisoryOrganizationCode.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		supervisoryOrganizationCode.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.supervisoryOrganizationCodeDao
				.makePersistent(supervisoryOrganizationCode);
		
		
	}
	
	/**
	 * Update a specified supervisory organization code
	 * @param supervisoryOrganizationCode - supervisory organization code to update
	 * @param supervisoryOrganization - supervisory organization
	 * @param dateRange - date range
	 * @param code - disciplinary code
	 * @return updated supervisory organization code
	 * @throws DuplicateEntityFoundException - when supervisory organization
	 * code already exists for given supervisory organization
	 */
	public SupervisoryOrganizationCode update(
			final SupervisoryOrganizationCode supervisoryOrganizationCode,
			final DateRange dateRange, final DisciplinaryCode code) 
					throws DuplicateEntityFoundException{
		if(this.supervisoryOrganizationCodeDao.findExcluding(
				supervisoryOrganizationCode.getSupervisoryOrganization(), code, 
				supervisoryOrganizationCode)
				!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		supervisoryOrganizationCode.setDateRange(dateRange);
		supervisoryOrganizationCode.setCode(code);
		supervisoryOrganizationCode.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		
		return this.supervisoryOrganizationCodeDao
				.makePersistent(supervisoryOrganizationCode);
	}
	
	/**
	 * Removes a supervisory organization code
	 * @param supervisoryOrganizationCode - supervisory organization code to remove
	 */
	public void remove(final SupervisoryOrganizationCode 
			supervisoryOrganizationCode){
		this.supervisoryOrganizationCodeDao
			.makeTransient(supervisoryOrganizationCode);
	}
	
}
