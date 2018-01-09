package omis.stg.service.delegate;

import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.exception.DuplicateEntityFoundException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.stg.dao.SecurityThreatGroupActivityInvolvementDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.domain.SecurityThreatGroupActivityInvolvement;
import omis.stg.exception.InvolvedOffenderRequiredException;

/**
 * Security threat group activity involvement.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 30, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityInvolvementDelegate {

	/* Instance factories. */
	private final InstanceFactory<SecurityThreatGroupActivityInvolvement>
		involvementInstanceFactory;
	
	/* DAOs. */
	private final SecurityThreatGroupActivityInvolvementDao involvementDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/** Instantiates delegate for security threat group activity.
	 * @param securityThreatGroupActivityDao data access object for
	 * activity.
	 */
	public SecurityThreatGroupActivityInvolvementDelegate(
			final InstanceFactory<SecurityThreatGroupActivityInvolvement> 
				involvementInstanceFactory,
			final SecurityThreatGroupActivityInvolvementDao involvementDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.involvementInstanceFactory = involvementInstanceFactory;
		this.involvementDao = involvementDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a security threat group activity involvement.
	 * @param Offender - offender
	 * @param String - narrative
	 *
	 * @return Creates a security threat group activity involvement. 
	 * @throws DuplicateEntityFoundException - when an security threat group 
	 * activity involvement already exists.
	 */
	public SecurityThreatGroupActivityInvolvement involveOffender(
			final Offender offender,
			final SecurityThreatGroupActivity activity,
			final String narrative)
		throws DuplicateEntityFoundException {
		if (this.involvementDao.find(offender, activity, narrative) != null) {
			throw new DuplicateEntityFoundException(
				"Duplicate security threat group activity involvement found.");
		}
				
	SecurityThreatGroupActivityInvolvement involvement =
			this.involvementInstanceFactory.createInstance();
		involvement.setActivity(activity);
		involvement.setOffender(offender);
		involvement.setNarrative(narrative);
				involvement.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
				involvement.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
				return this.involvementDao.makePersistent(involvement);
	}

	/** Updates a security threat group activity involvement.
	 * @param Offender - offender
	 * @param String - narrative
	 *
	 * @return Updates a security threat group activity involvement. 
	 * @throws DuplicateEntityFoundException 
	 */
	public SecurityThreatGroupActivityInvolvement updateInvolvementNarrative(
			final SecurityThreatGroupActivityInvolvement involvement,
			final String narrative) throws DuplicateEntityFoundException {
		if (this.involvementDao.findExcluding(involvement.getOffender(), 
				involvement.getActivity(), narrative, involvement) != null) {
			throw new DuplicateEntityFoundException(
					"Duplicate security threat group activity involvement found.");
		}
		involvement.setNarrative(narrative);
		involvement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
		return this.involvementDao.makePersistent(involvement);
	}
	
	/** Removes a security threat group activity involvement.
	 * @param Offender - offender
	 * @param String - narrative
	 *
	 * @return Removes a security threat group activity involvement. 
	 * @throws InvolvedOffenderRequiredException - at least one security threat 
	 * group activity involvement needs exists.
	 */
	public void removeInvolvement(
			SecurityThreatGroupActivityInvolvement involvment) {
		this.involvementDao.makeTransient(involvment);
	}

	public List<SecurityThreatGroupActivityInvolvement> findInvolvements(
			SecurityThreatGroupActivity activity) {
		return this.involvementDao.findInvolvements(activity);
	}
	
	public SecurityThreatGroupActivityInvolvement findOffenders(
			Offender offender, SecurityThreatGroupActivity activity, 
			String narrative) {
		return this.involvementDao.find(offender, activity, narrative);
	}
	
	/**
	 * Populates the specified security threat group activity involvement.
	 * 
	 * @param SecurityThreatGroupActivity - activity
	 * @param Offender - offender
	 * @param String - narrative
	 *  
	 * @return populated security threat group activity involvement.
	 */
	/*private void populateSecurityThreatGroupActivityInvolvement(
			final SecurityThreatGroupActivityInvolvement involvement,
			final SecurityThreatGroupActivity activity,
			final Offender offender,
			final String narrative) {
		involvement.setActivity(activity);
		involvement.setOffender(offender);
		involvement.setNarrative(narrative);
		involvement.setUpdateSignature(new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate()));
	}*/
	
}
