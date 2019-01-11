/* 
* OMIS - Offender Management Information System 
* Copyright (C) 2011 - 2017 State of Montana 
* 
* This program is free software: you can redistribute it and/or modify 
* it under the terms of the GNU General Public License as published by 
* the Free Software Foundation, either version 3 of the License, or 
* (at your option) any later version. 
* 
* This program is distributed in the hope that it will be useful, 
* but WITHOUT ANY WARRANTY; without even the implied warranty of 
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
* GNU General Public License for more details. 
* 
* You should have received a copy of the GNU General Public License 
* along with this program.  If not, see <http://www.gnu.org/licenses/>. 
*/
package omis.stg.service.delegate;

import java.util.Date;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.stg.dao.SecurityThreatGroupActivityDao;
import omis.stg.domain.SecurityThreatGroupActivity;
import omis.stg.exception.SecurityThreatGroupActivityExistsException;

/**
 * Security threat group activity.
 * 
 * @author Trevor Isles
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */

public class SecurityThreatGroupActivityDelegate {

	/* Instance factories. */
	private final InstanceFactory<SecurityThreatGroupActivity> 
		activityInstanceFactory;
	
	/* DAOs. */
	private final SecurityThreatGroupActivityDao activityDao;
	
	/* Audit component retriever. */
	private final AuditComponentRetriever auditComponentRetriever;
	
	/* Constructor. */
	
	/** Instantiates delegate for security threat group activity.
	 * @param securityThreatGroupActivityDao data access object for
	 * activity.
	 */
	public SecurityThreatGroupActivityDelegate(
			final InstanceFactory<SecurityThreatGroupActivity> 
				activityInstanceFactory,
			final SecurityThreatGroupActivityDao activityDao,
			final AuditComponentRetriever auditComponentRetriever) {
		this.activityInstanceFactory = activityInstanceFactory;
		this.activityDao = activityDao;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** Creates a security threat group activity.
	 * @param Date - date
	 * @param Person - reportedBy
	 * @param String - summary
	 * @param  - 
	 * @return Creates a security threat group activity. 
	 * @throws SecurityThreatGroupActivityExistsException - when an activity already exists.
	 */
	public SecurityThreatGroupActivity create(
			final Date reportDate, 
			final Person reportedBy,
			final String summary)
		throws SecurityThreatGroupActivityExistsException {
		if (this.activityDao.find(reportDate, reportedBy, summary) != null) {
			throw new SecurityThreatGroupActivityExistsException (
					"Duplicate security threat group activity found");
		}
		
		SecurityThreatGroupActivity activity = 
			this.activityInstanceFactory.createInstance();
				activity.setCreationSignature(new CreationSignature(
			this.auditComponentRetriever.retrieveUserAccount(),
			this.auditComponentRetriever.retrieveDate()));
			this.populateSecurityThreatGroupActivity(activity, reportDate, reportedBy,
					summary);
		return this.activityDao.makePersistent(activity);
	}
	
	/**Updates a security threat group activity.
	 * @param Date - date
	 * @param Person - reportedBy
	 * @param String - summary
	 * @param  - 
	 * @return Updates a security threat group activity. 
	 * @throws SecurityThreatGroupActivityExistsException - when an activity already exists.
	 */
	public SecurityThreatGroupActivity update(
			final SecurityThreatGroupActivity activity,
			final Date reportDate, 
			final Person reportedBy, 
			final String summary) 
					throws SecurityThreatGroupActivityExistsException {
				if (this.activityDao.findExcluding(
					activity, reportDate, reportedBy, summary) != null) {
					throw new SecurityThreatGroupActivityExistsException(
							"Duplicate security threat group activity found");
		}
		this.populateSecurityThreatGroupActivity(activity, reportDate, reportedBy,
				summary);
		return this.activityDao.makePersistent(activity);
	}
	
	/** Removes a security threat group activity.
	 * @param SecurityThreatGroupActivity - activity
	 * @param  - .
	 * @return Removes a security threat group activity.
	 * @throws SecurityThreatGroupActivityExistsException - when security threat group 
	 * activity already exists. 
	 */
	public void remove(final SecurityThreatGroupActivity activity) {
		this.activityDao.makeTransient(activity);
	}
	
		/**
		 * Populates the specified security threat group activity.
		 * 
		 * @param SecurityThreatGroupActivity - activity
		 * @param Date - date
		 * @param Person - reportedBy
		 * @param String - summary
		 *  
		 * @return populated security threat group activity.
		 */
		private void populateSecurityThreatGroupActivity(
				final SecurityThreatGroupActivity activity,
				final Date reportDate, 
				final Person reportedBy, 
				final String summary) {
			activity.setReportDate(reportDate);
			activity.setReportedBy(reportedBy);
			activity.setSummary(summary);
			activity.setUpdateSignature(new UpdateSignature(
					this.auditComponentRetriever.retrieveUserAccount(),
					this.auditComponentRetriever.retrieveDate()));
	}	
}