package omis.trackeddocument.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.docket.domain.Docket;
import omis.instance.factory.InstanceFactory;
import omis.trackeddocument.dao.TrackedDocumentReceivalDao;
import omis.trackeddocument.domain.TrackedDocumentCategory;
import omis.trackeddocument.domain.TrackedDocumentReceival;
import omis.trackeddocument.exception.TrackedDocumentReceivalExistsException;
import omis.user.domain.UserAccount;

/**
 * Tracked document receival delegate.
 * 
 *@author Yidong Li
 *@version 0.1.0 (Dec 12, 2017)
 *@since OMIS 3.0
 *
 */
public class TrackedDocumentReceivalDelegate {
	/* Resources. */
	private final InstanceFactory<TrackedDocumentReceival>
		trackedDocumentReceivalInstanceFactory;
	private final AuditComponentRetriever auditComponentRetriever;
	private final TrackedDocumentReceivalDao trackedDocumentReceivalDao;

	/**
	 * Instantiates an implementation of tracked document receival delegate.
	 * @param auditComponentRetriever auditComponentRetriever
	 * @param trackedDocumentReceivalInstanceFactory 
	 * trackedDocumentReceivalInstanceFactory
	 * @param trackedDocumentReceivalDao trackedDocumentReceivalDao
	 */
	public TrackedDocumentReceivalDelegate(
		final TrackedDocumentReceivalDao trackedDocumentReceivalDao, 
		final AuditComponentRetriever auditComponentRetriever, 
		final InstanceFactory<TrackedDocumentReceival> 
		trackedDocumentReceivalInstanceFactory) {
		this.auditComponentRetriever = auditComponentRetriever;
		this.trackedDocumentReceivalDao = trackedDocumentReceivalDao;
		this.trackedDocumentReceivalInstanceFactory
			= trackedDocumentReceivalInstanceFactory;
	}
	
	/**
	 * Track an new receival.
	 * @param docket docket
	 * @param category category 
	 * @param receivedDate received date
	 * @param receivedByUserAccount received user account
	 * @throws TrackedDocumentReceivalExistsException 
	 * TrackedDocumentReceivalExistsException
	 * @return receival receival
	 */
	public TrackedDocumentReceival trackReceival(final Docket docket, 
		final TrackedDocumentCategory category, final Date receivedDate, 
		final UserAccount receivedByUserAccount) 
		throws TrackedDocumentReceivalExistsException {
		if (this.trackedDocumentReceivalDao.findExistingReceival(docket, 
			category, receivedDate) != null) {
			throw new TrackedDocumentReceivalExistsException("Receival Already"
					+ " Exist.");
		}
		
		TrackedDocumentReceival receival 
			= this.trackedDocumentReceivalInstanceFactory.createInstance();
		receival.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		receival.setUpdateSignature(new UpdateSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
		receival.setDocket(docket);
		receival.setReceivedByUserAccount(receivedByUserAccount);
		receival.setCategory(category);
		receival.setReceivedDate(receivedDate);
		return this.trackedDocumentReceivalDao.makePersistent(receival);
	}
	
	/**
	 * Remove receival.
	 * @param receival receival
	 */
	public void remove(final TrackedDocumentReceival receival) {
		this.trackedDocumentReceivalDao.makeTransient(receival);
	}
	
	/**
	 * Removes receivals by docket.
	 * 
	 * @param docket docket
	 *
	 */
	public void removeByDocket(final Docket docket) {
		List<TrackedDocumentReceival> receivals
			= this.trackedDocumentReceivalDao.findReceivalsByDocket(docket);
		for (TrackedDocumentReceival receival : receivals) {
			this.trackedDocumentReceivalDao.makeTransient(receival);
		}
	}
	
	/**
	 * Returns receivals by docket.
	 * 
	 * @param docket docket
	 * @return return a list of tracked document receival
	 *
	 */
	public List<TrackedDocumentReceival> findByDocket(final Docket docket) {
		return this.trackedDocumentReceivalDao.findReceivalsByDocket(docket);
	}
}