package omis.need.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.need.dao.CasePlanObjectiveDao;
import omis.need.dao.CasePlanReferralDao;
import omis.need.dao.NeedDomainDao;
import omis.need.dao.ObjectivePriorityDao;
import omis.need.domain.CasePlanObjective;
import omis.need.domain.CasePlanReferral;
import omis.need.domain.CasePlanReferralResponse;
import omis.need.domain.NeedDomain;
import omis.need.domain.ObjectivePriority;
import omis.need.domain.ObjectiveSource;
import omis.need.domain.ReferralCategory;
import omis.need.service.CasePlanObjectiveService;
import omis.offender.domain.Offender;
import omis.organization.domain.Organization;
import omis.organization.domain.OrganizationDepartment;
import omis.person.domain.Person;

/**
 * @author Kelly Churchill
 * @author Joel Norris
 * @version 0.1.0 (Jul 12, 2015)
 * @since OMIS 3.0
 */
public class CasePlanObjectiveServiceImpl implements CasePlanObjectiveService{
	
	/* Data access objective. */
	
	private CasePlanReferralDao casePlanReferralDao;
	private CasePlanObjectiveDao casePlanObjectiveDao;
	private NeedDomainDao needDomainDao;
	private ObjectivePriorityDao objectivePriorityDao;
	
	/* Component retriever. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Instance factory. */
	
	private InstanceFactory<CasePlanObjective> casePlanObjectiveInstanceFactory;
	private InstanceFactory<CasePlanReferral> casePlanReferralInstanceFactory;
	
	/**
	 * Instantiates an instance of case plan objective service with the
	 * specified data access objects and instance factories.
	 * 
	 * @param casePlanReferralDao case plan referral data access object
	 * @param casePlanObjectiveDao case plan objective data access object
	 * @param needDomainDao need domain data access object
	 * @param objectivePriorityDao objective priority data access object
	 * @param casePlanObjectiveInstanceFactory case plan objective instance
	 * factory
	 */
	public CasePlanObjectiveServiceImpl(
			final CasePlanReferralDao casePlanReferralDao,
			final CasePlanObjectiveDao casePlanObjectiveDao,
			final NeedDomainDao needDomainDao,
			final ObjectivePriorityDao objectivePriorityDao,
			final InstanceFactory<CasePlanObjective>
			casePlanObjectiveInstanceFactory,
			final InstanceFactory<CasePlanReferral>
			casePlanReferralInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever){
		this.casePlanReferralDao = casePlanReferralDao;
		this.casePlanObjectiveDao = casePlanObjectiveDao;
		this.needDomainDao = needDomainDao;
		this.objectivePriorityDao = objectivePriorityDao;
		this.casePlanObjectiveInstanceFactory 
			= casePlanObjectiveInstanceFactory;
		this.casePlanReferralInstanceFactory = casePlanReferralInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CasePlanObjective create(final Offender offender,
			final Date identificationDate, final String name,
			final String comment, final ObjectiveSource source,
			final ObjectivePriority priority, final NeedDomain domain,
			final Boolean offenderAgrees, final Person staffMember) 
		throws DuplicateEntityFoundException {
		if (this.casePlanObjectiveDao.find(offender, name, domain) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate case plan objective found");
		}
		CasePlanObjective objective = this.casePlanObjectiveInstanceFactory
				.createInstance();
		this.populateCasePlanObjective(objective, identificationDate,
				name, comment, source, priority, domain,
				offenderAgrees, staffMember);
		objective.setOffender(offender);
		objective.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.casePlanObjectiveDao.makePersistent(objective);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public CasePlanObjective update(CasePlanObjective objective,
			Date identificationDate, String name, String comment,
			ObjectiveSource source, ObjectivePriority priority,
			NeedDomain domain, final Boolean offenderAgrees,
			final Person staffMember) 
		throws DuplicateEntityFoundException{
		if (this.casePlanObjectiveDao.findExcluding(
				objective, objective.getOffender(), name, domain) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate case plan objective found");
		}
		this.populateCasePlanObjective(objective, identificationDate,
				name, comment, source, priority, domain,
				offenderAgrees, staffMember);
		return this.casePlanObjectiveDao.makePersistent(objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remove(CasePlanObjective objective) {
		this.casePlanObjectiveDao.makeTransient(objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CasePlanReferral createReferral(final CasePlanObjective objective,
			final Date date, final String comment, final ObjectiveSource source,
			final ObjectivePriority priority, final NeedDomain domain,
			final Organization organization, 
			final OrganizationDepartment department,
			final ReferralCategory category, 
			final CasePlanReferralResponse response) 
		throws DuplicateEntityFoundException{

		if (this.casePlanReferralDao.find(objective, date, organization, 
				department) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate case plan objective found");
		}
		
		CasePlanReferral referral = this.casePlanReferralInstanceFactory
				.createInstance();
		this.populateCasePlanReferral(referral,objective,date,comment,
				organization,department,category,response);

		referral.setCreationSignature(new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.casePlanReferralDao.makePersistent(referral);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CasePlanReferral updateReferral(CasePlanReferral referral,
			CasePlanObjective objective, Date date, String comment,
			ObjectiveSource source, ObjectivePriority priority,
			NeedDomain domain) 
		throws DuplicateEntityFoundException{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeReferral(CasePlanReferral referral) {
		this.casePlanReferralDao.makeTransient(referral);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CasePlanObjective> findByOffender(Offender offender) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CasePlanReferral> findReferralsByObjective(
			CasePlanObjective objective) {
		return this.casePlanReferralDao.findByObjective(objective);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ObjectivePriority> findPriorities() {
		return this.objectivePriorityDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NeedDomain> findNeedDomains() {
		return this.needDomainDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ReferralCategory> findReferralCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	/* Helper methods. */
	
	private CasePlanObjective populateCasePlanObjective(
			final CasePlanObjective objective, final Date identificationDate,
			final String name, final String comment,
			final ObjectiveSource source,
			final ObjectivePriority priority, final NeedDomain domain,
			final Boolean offenderAgrees, final Person staffMember) {
		objective.setIdentificationDate(identificationDate);
		objective.setName(name);
		objective.setComment(comment);
		objective.setSource(source);
		objective.setPriority(priority);
		objective.setDomain(domain);
		objective.setOffenderAgrees(offenderAgrees);
		objective.setStaffMember(staffMember);
		objective.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return objective;
	}
	
	private CasePlanReferral populateCasePlanReferral(
			final CasePlanReferral referral,
			final CasePlanObjective objective,
			final Date date,
			final String comment,
			final Organization organization,
			final OrganizationDepartment department,
			final ReferralCategory category,
			final CasePlanReferralResponse response){
		referral.setObjective(objective);
		referral.setDate(date);
		referral.setComment(comment);
		referral.setOrganization(organization);
		referral.setDepartment(department);
		referral.setCategory(category);
		referral.setResponse(response);
		referral.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return referral;
	}
}