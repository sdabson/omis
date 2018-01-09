package omis.adaaccommodation.service.impl;

import java.util.Date;
import java.util.List;

import omis.adaaccommodation.dao.AuthorizationDao;
import omis.adaaccommodation.dao.AuthorizationSourceCategoryDao;
import omis.adaaccommodation.domain.Accommodation;
import omis.adaaccommodation.domain.Authorization;
import omis.adaaccommodation.domain.AuthorizationSourceCategory;
import omis.adaaccommodation.service.AuthorizationService;
import omis.audit.AuditComponentRetriever;
import omis.audit.domain.AuthorizationSignature;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.user.domain.UserAccount;

/**
 * Authorization service implementation.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jul 20, 2015)
 * @since OMIS 3.0
 */
public class AuthorizationServiceImpl implements AuthorizationService {

	/* Data access object. */	
	private final AuthorizationDao authorizationDao;
	private final AuthorizationSourceCategoryDao authorizationSourceCategoryDao;
	
	/* Instance factories. */
	private final InstanceFactory<Authorization> authorizationInstanceFactory;
	
	/* Component retrievers. */
	private final AuditComponentRetriever auditComponentRetriever;

	public AuthorizationServiceImpl(
		final AuthorizationDao authorizationDao,
		final AuthorizationSourceCategoryDao authorizationSourceCategoryDao,
		final InstanceFactory<Authorization> authorizationInstanceFactory,
		final AuditComponentRetriever auditComponentRetriever) {
		
		this.authorizationDao = authorizationDao;
		this.authorizationSourceCategoryDao = authorizationSourceCategoryDao;
		this.authorizationInstanceFactory = authorizationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}

	/** {@inheritDoc} */
	@Override
	public Authorization create(Accommodation accommodation,
			Date authorizationDate, UserAccount authorizationUser,
			AuthorizationSourceCategory authorizationSourceCategory,
			String comments, Date startDate, Date endDate) 
					throws DuplicateEntityFoundException {
		Authorization authorization = 
				this.authorizationInstanceFactory.createInstance();
		DateRange dateRange = new DateRange();
		dateRange.getStartDate();
		dateRange.getEndDate();
		if(this.authorizationDao.find(accommodation, authorizationDate, 
				authorizationUser, authorizationSourceCategory, dateRange) 
				!= null) {
			throw new DuplicateEntityFoundException(
					"Duplicate authorization found");
		}	
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		authorization.setAccommodation(accommodation);
		authorization.setAuthorizationSource(authorizationSourceCategory);
		authorization.setComments(comments);
		authorization.setAuthorizationTerm(dateRange);
		authorization.setAuthorizationSignature(new AuthorizationSignature(
				authorizationUser, authorizationDate));		
		authorization.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		authorization.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.authorizationDao.makePersistent(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public Authorization update(Authorization authorization, 
			Date authorizationDate,
			UserAccount user,
			AuthorizationSourceCategory authorizationSourceCategory,
			String comments, Date startDate, Date endDate) 
					throws DuplicateEntityFoundException {
		DateRange dateRange = new DateRange();
		/*if(this.authorizationDao.findExcluding(authorization, 
				authorization.getAccommodation(), 
				authorization.getAuthorizationSignature().getDate(), user, 
				authorizationSourceCategory, dateRange) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate authorization found.");
		}		*/
		//setting authorization term dateRange		
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);		
		authorization.setAuthorizationTerm(dateRange);
		authorization.setAuthorizationSource(authorizationSourceCategory);
		authorization.setComments(comments);
		authorization.setAuthorizationSignature(new AuthorizationSignature(
				user, authorizationDate));
		authorization.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		
		return this.authorizationDao.makePersistent(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(Authorization authorization) {
		this.authorizationDao.makeTransient(authorization);
	}

	/** {@inheritDoc} */
	@Override
	public List<AuthorizationSourceCategory> 
		findAllAuthorizationSourceCategories() {
		return this.authorizationSourceCategoryDao.findCategories();
	}
	
	/** {@inheritDoc} */
	@Override
	public Authorization
	findAuthorizationByAccommodation(Accommodation accommodation) {
		return this.authorizationDao.findByAccommodation(accommodation);
	}
}