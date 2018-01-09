package omis.person.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.person.dao.AlternativeIdentityAssociationDao;
import omis.person.domain.AlternativeIdentityAssociation;
import omis.person.domain.AlternativeIdentityCategory;
import omis.person.domain.AlternativeNameAssociation;
import omis.person.domain.Person;
import omis.person.domain.PersonIdentity;

/**
 * AlternativeIdentityAssociationDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 9, 2016)
 *@since OMIS 3.0
 *
 */
public class AlternativeIdentityAssociationDelegate {
	
	private static final String DUPLICATE_ENTITY_FOUND_MSG =
			"Alternative Identity Association Already Exists For Given Person "
			+ "Identity, Category, and Date Range";
	
	private final AlternativeIdentityAssociationDao 
		alternativeIdentityAssociationDao;
	
	private final InstanceFactory<AlternativeIdentityAssociation> 
		alternativeIdentityAssociationInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for AlternativeIdentityAssociationDelegate
	 * @param alternativeIdentityAssociationDao
	 * @param alternativeIdentityAssociationDelegateInstanceFactory
	 * @param auditComponentRetriever
	 */
	public AlternativeIdentityAssociationDelegate(
			final AlternativeIdentityAssociationDao 
				alternativeIdentityAssociationDao,
			final InstanceFactory<AlternativeIdentityAssociation> 
				alternativeIdentityAssociationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.alternativeIdentityAssociationDao = 
				alternativeIdentityAssociationDao;
		this.alternativeIdentityAssociationInstanceFactory = 
				alternativeIdentityAssociationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates an Alternative Identity Association with Given Parameters
	 * @param personIdentity
	 * @param dateRange
	 * @param alternativeIdentityCategory
	 * @param alternativeNameAssociation
	 * @return AlternativeIdentityAssociation - Alternative Identity Association 
	 * with Given Parameters
	 * @throws DuplicateEntityFoundException
	 */
	public AlternativeIdentityAssociation create(
			final PersonIdentity personIdentity, final DateRange dateRange, 
			final AlternativeIdentityCategory alternativeIdentityCategory, 
			final AlternativeNameAssociation alternativeNameAssociation)
			throws DuplicateEntityFoundException{
		if(this.alternativeIdentityAssociationDao
				.find(personIdentity, alternativeIdentityCategory, dateRange) 
					!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		AlternativeIdentityAssociation alternativeIdentityAssociation = 
				this.alternativeIdentityAssociationInstanceFactory
					.createInstance();
		
		alternativeIdentityAssociation.setAlternativeNameAssociation(
				alternativeNameAssociation);
		alternativeIdentityAssociation.setCategory(alternativeIdentityCategory);
		alternativeIdentityAssociation.setDateRange(dateRange);
		alternativeIdentityAssociation.setIdentity(personIdentity);
		alternativeIdentityAssociation.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		alternativeIdentityAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.alternativeIdentityAssociationDao
				.makePersistent(alternativeIdentityAssociation);
	}
	
	/**
	 * Updates an alternative identity association with the given parameters
	 * @param alternativeIdentityAssociation
	 * @param personIdentity
	 * @param dateRange
	 * @param alternativeIdentityCategory
	 * @param alternativeNameAssociation
	 * @return AlternativeIdentityAssociation - updated alternative identity 
	 * association with the given parameters
	 * @throws DuplicateEntityFoundException
	 */
	public AlternativeIdentityAssociation update(
			final AlternativeIdentityAssociation alternativeIdentityAssociation,
			final PersonIdentity personIdentity, final DateRange dateRange,
			final AlternativeIdentityCategory alternativeIdentityCategory,
			final AlternativeNameAssociation alternativeNameAssociation)
			throws DuplicateEntityFoundException{
		if(this.alternativeIdentityAssociationDao
				.findExcluding(alternativeIdentityAssociation, 
						personIdentity, alternativeIdentityCategory, dateRange) 
							!= null){
			throw new DuplicateEntityFoundException(DUPLICATE_ENTITY_FOUND_MSG);
		}
		
		alternativeIdentityAssociation.setAlternativeNameAssociation(
				alternativeNameAssociation);
		alternativeIdentityAssociation.setCategory(alternativeIdentityCategory);
		alternativeIdentityAssociation.setDateRange(dateRange);
		alternativeIdentityAssociation.setIdentity(personIdentity);
		alternativeIdentityAssociation.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.alternativeIdentityAssociationDao
				.makePersistent(alternativeIdentityAssociation);
	}
	
	/**
	 * Removes an alternative identity association
	 * @param alternativeIdentityAssociationDelegate
	 */
	public void remove(final AlternativeIdentityAssociation
			alternativeIdentityAssociationDelegate){
		this.alternativeIdentityAssociationDao
			.makeTransient(alternativeIdentityAssociationDelegate);
	}
	
	/**
	 * Returns alternative identity associations for person.
	 * 
	 * @param person person
	 * @return alternative identity associations for person
	 */
	public List<AlternativeIdentityAssociation> findByPerson(
			final Person person){
		return this.alternativeIdentityAssociationDao.findByPerson(person);
	}
	
	/**
	 * Returns alternative identity associations for person active on date.
	 * 
	 * @param person person
	 * @param date date
	 * @return alternative identity associations for person active on date
	 */
	public List<AlternativeIdentityAssociation> findByPersonOnDate(
			final Person person, final Date date){
		return this.alternativeIdentityAssociationDao
				.findByPersonOnDate(person, date);
	}
	
}
