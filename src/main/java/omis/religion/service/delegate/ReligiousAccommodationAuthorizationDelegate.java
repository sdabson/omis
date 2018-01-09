package omis.religion.service.delegate;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.OperationNotAuthorizedException;
import omis.instance.factory.InstanceFactory;
import omis.religion.dao.ReligiousAccommodationAuthorizationDao;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousAccommodationAuthorization;
import omis.religion.domain.ReligiousPreference;

/**
 * Delegate for religious accommodation authorizations.
 *
 * @author Josh Divine
 * @version 0.1.0 (Jul 26, 2017)
 * @since OMIS 3.0
 *
 */
public class ReligiousAccommodationAuthorizationDelegate {

	/* Data access objects. */
	
	private final ReligiousAccommodationAuthorizationDao
		religiousAccommodationAuthorizationDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<ReligiousAccommodationAuthorization>
		religiousAccommodationAuthorizationInstanceFactory;
	
	/* Component Retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Constructor for religious accommodation authorization delegate.
	 * 
	 * @param religiousAccommodationAuthorizationDao religious accommodation 
	 * authorization data access object
	 * @param religiousAccommodationAuthorizationInstanceFactory religious accommodation authorization instance factory
	 */
	public ReligiousAccommodationAuthorizationDelegate(
			final ReligiousAccommodationAuthorizationDao
				religiousAccommodationAuthorizationDao, 
			final InstanceFactory<ReligiousAccommodationAuthorization>
				religiousAccommodationAuthorizationInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.religiousAccommodationAuthorizationDao = 
				religiousAccommodationAuthorizationDao;
		this.religiousAccommodationAuthorizationInstanceFactory = 
				religiousAccommodationAuthorizationInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates a new religious accommodation authorization.
	 * 
	 * @param preference religious preference
	 * @param accommodation religious accommodation
	 * @return religious accommodation authorization
	 * @throws OperationNotAuthorizedException  operation not authorized 
	 * exception
	 */
	public ReligiousAccommodationAuthorization authorize(
			final ReligiousPreference preference, 
			final ReligiousAccommodation accommodation) 
					throws OperationNotAuthorizedException {
		if (this.religiousAccommodationAuthorizationDao.find(preference, 
				accommodation) != null) {
			throw new OperationNotAuthorizedException(
					"Accommodation already authorized");
		}
		ReligiousAccommodationAuthorization authorization = 
				this.religiousAccommodationAuthorizationInstanceFactory
				.createInstance();
		authorization.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		authorization.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		authorization.setAccommodation(accommodation);
		authorization.setPreference(preference);
		return this.religiousAccommodationAuthorizationDao
				.makePersistent(authorization);
	}
	
	/**
	 * Removes a religious accommodation authorization.
	 * 
	 * @param authorization religious accommodation authorization
	 * @throws OperationNotAuthorizedException operation not authorized 
	 * exception
	 */
	public void remove(final ReligiousPreference preference,
			final ReligiousAccommodation accommodation)
					throws OperationNotAuthorizedException {
		ReligiousAccommodationAuthorization authorization =
				this.religiousAccommodationAuthorizationDao
					.find(preference, accommodation);
		if (authorization != null) {
			this.religiousAccommodationAuthorizationDao
				.makeTransient(authorization);
		} else {
			throw new OperationNotAuthorizedException(
					"Accommodation not authorized");
		}
	}
	
	/**
	 * Returns the authorization for accommodation of the religious preference.
	 * 
	 * <p>If no authorization exists (the accommodation is not authorized),
	 * return {@code null}.
	 * 
	 * @param preference religious preference
	 * @param accommodation accommodation
	 * @return authorization for accommodation of preference; {@code null}
	 * if no authorized
	 */
	public ReligiousAccommodationAuthorization find(
			final ReligiousPreference preference, 
			final ReligiousAccommodation accommodation) {
		return this.religiousAccommodationAuthorizationDao.find(preference, 
				accommodation);
		
	}
	
	/**
	 * Removes religious accommodation authorizations for religious preference.
	 * 
	 * @param preference preference
	 * @return number of records removed
	 */
	public int removeByPreference(ReligiousPreference preference) {
		return this.religiousAccommodationAuthorizationDao.removeByPreference(
				preference);
	}
}
