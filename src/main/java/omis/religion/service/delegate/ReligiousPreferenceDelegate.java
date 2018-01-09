package omis.religion.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.audit.domain.VerificationSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.exception.OperationNotAuthorizedException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.religion.dao.ReligiousPreferenceDao;
import omis.religion.domain.Religion;
import omis.religion.domain.ReligiousPreference;

/**
 * ReligiousPreferenceDelegate.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Nov 10, 2016)
 *@since OMIS 3.0
 *
 */
public class ReligiousPreferenceDelegate {
	
	private final ReligiousPreferenceDao religiousPreferenceDao;
	
	private final InstanceFactory<ReligiousPreference> 
		religiousPreferenceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	/**
	 * Constructor for ReligiousPreferenceDelegate
	 * @param religiousPreferenceDao
	 * @param religiousPreferenceInstanceFactory
	 * @param auditComponentRetriever
	 */
	public ReligiousPreferenceDelegate(
			final ReligiousPreferenceDao religiousPreferenceDao,
			final InstanceFactory<ReligiousPreference> 
				religiousPreferenceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.religiousPreferenceDao = religiousPreferenceDao;
		this.religiousPreferenceInstanceFactory
			= religiousPreferenceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/**
	 * Creates the initial religious preference.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param startDate start date
	 * @return newly created initial religious preference
	 * @throws DuplicateEntityFoundException if duplicate entity exists 
	 */
	public ReligiousPreference createInitial(final Offender offender,
			final Religion religion, final Date startDate) 
					throws DuplicateEntityFoundException {
		if (this.religiousPreferenceDao.exists(offender, religion, startDate, 
				null)) {
			throw new DuplicateEntityFoundException(
					"Duplicate preference exists");
		}
		ReligiousPreference preference = this.religiousPreferenceInstanceFactory
				.createInstance();
		preference.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		preference.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		preference.setOffender(offender);
		preference.setDateRange(new DateRange(startDate, null));
		preference.setReligion(religion);
		return this.religiousPreferenceDao.makePersistent(preference);
	}
	
	/**
	 * Updates an existing religious preference.
	 * 
	 * @param preference preference
	 * @param religion religion
	 * @param dateRange date range
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return updated religious preference
	 * @throws DuplicateEntityFoundException if the religious preference
	 * already exists
	 * @throws DateConflictException if the religious preference date range
	 * conflicts with existing religious preferences
	 * @throws OperationNotAuthorizedException if an attempt was made to
	 * enter a religious preference within the minimum amount of time required
	 * between changes without an authorized user
	 */
	public ReligiousPreference update(final ReligiousPreference preference,
			final Religion religion,final DateRange dateRange,
			final String comment, 
			final VerificationSignature verificationSignature) 
				throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException {
		Date startDate;
		Date endDate;
		if (dateRange != null) {
			startDate = dateRange.getStartDate();
			endDate = dateRange.getEndDate();
		} else {
			startDate = endDate = null;
		}
		endActiveIfExists(preference.getOffender(), startDate, preference);
		if (this.religiousPreferenceDao.existsExcluding(
				preference.getOffender(), religion, startDate, endDate,
				preference)) {
			throw new DuplicateEntityFoundException(
					"Duplicate preference exists");
		}
		if (this.religiousPreferenceDao.countByOffenderOnDateExcluding(
				preference.getOffender(), startDate, endDate, preference) > 0) {
			throw new DateConflictException("Overlapping date ranges");
		}
		if (verificationSignature == null
					|| verificationSignature.getUserAccount() == null) {
			if (startDate != null) {
				Date yearlyStartDate = DateRange.findYearlyRange(startDate)
					.getStartDate();
				if (this.religiousPreferenceDao.countStartDateChangesExcluding(
						preference.getOffender(), yearlyStartDate, startDate,
						preference) > 0) {
					throw new OperationNotAuthorizedException(
						"Preference not allowed within start of"
								+ " existing preference");
				}
			}
			if (endDate != null) {
				Date yearlyEndDate = DateRange.findYearlyRange(endDate)
						.getEndDate();
				if (this.religiousPreferenceDao.countEndDateChangesExcluding(
						preference.getOffender(), endDate, yearlyEndDate,
						preference) > 0) {
					throw new OperationNotAuthorizedException(
						"Preference not allowed within end of"
									+ " existing preference");
				}
			}
		}
		preference.setReligion(religion);
		preference.setComment(comment);
		preference.setDateRange(new DateRange(startDate, endDate));
		preference.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		preference.setVerificationSignature(verificationSignature);
		return preference;
	}
	
	/**
	 * Saves new religious preference.
	 * 
	 * @param offender offender
	 * @param religion religion
	 * @param dateRange date range
	 * @param comment comment
	 * @param verificationSignature verification signature
	 * @return newly created verification signature
	 * @throws DuplicateEntityFoundException if the religious preference
	 * already exists
	 * @throws DateConflictException if the religious preference date range
	 * conflicts with existing religious preferences
	 * @throws OperationNotAuthorizedException if an attempt was made to
	 * enter a religious preference within the minimum amount of time required
	 * between changes without an authorized user
	 */
	public ReligiousPreference save(final Offender offender,
			final Religion religion, final DateRange dateRange,
			final String comment,
			final VerificationSignature verificationSignature)
				throws DuplicateEntityFoundException, DateConflictException,
					OperationNotAuthorizedException {
		Date startDate;
		Date endDate;
		if (dateRange != null) {
			startDate = dateRange.getStartDate();
			endDate = dateRange.getEndDate();
		} else {
			startDate = endDate = null;
		}
		endActiveIfExists(offender, startDate);
		if (this.religiousPreferenceDao.exists(offender, religion, startDate,
				endDate)) {
			throw new DuplicateEntityFoundException(
					"Duplicate preference exists");
		}
		if (this.religiousPreferenceDao.countByOffenderOnDate(offender,
				startDate, endDate) > 0) {
			throw new DateConflictException("Overlapping date ranges");
		}
		if (verificationSignature == null
				|| verificationSignature.getUserAccount() == null) {
			if (startDate != null) {
				Date yearlyStartDate = DateRange.findYearlyRange(startDate)
					.getStartDate();
				if (this.religiousPreferenceDao.countStartDateChanges(offender,
						yearlyStartDate, startDate) > 0) {
					throw new OperationNotAuthorizedException(
							"Preference not allowed within start of"
							+ " existing preference");
				}
			}
			if (endDate != null) {
				Date yearlyEndDate = DateRange.findYearlyRange(endDate)
						.getEndDate();
				if (this.religiousPreferenceDao.countEndDateChanges(offender,
						endDate, yearlyEndDate) > 0) {
					throw new OperationNotAuthorizedException(
							"Preference not allowed within end of"
							+ " existing preference");
				}
			}
		}
		ReligiousPreference preference = this.religiousPreferenceInstanceFactory
				.createInstance();
		preference.setCreationSignature(
				new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		preference.setUpdateSignature(
				new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		preference.setOffender(offender);
		preference.setDateRange(new DateRange(startDate, endDate));
		preference.setReligion(religion);
		preference.setComment(comment);
		preference.setVerificationSignature(verificationSignature);
		return this.religiousPreferenceDao.makePersistent(preference);
	}
	
	/**
	 * Removes a ReligiousPreference
	 * @param religiousPreference - Religious Preference to remove
	 */
	public void remove(final ReligiousPreference religiousPreference){
		this.religiousPreferenceDao.makeTransient(religiousPreference);
	}
	
	/**
	 * Returns religious preferences by offender.
	 * 
	 * @param offender offender
	 * @return religious preferences by offender
	 */
	public List<ReligiousPreference> findByOffender(final Offender offender) {
		return this.religiousPreferenceDao.findByOffender(offender);
	}
	
	/* Helper Methods */
	
	/**
	 * Ends current active preference if one exists
	 * @param offender
	 * @param startDate
	 * @param excludedPreferences - ReligiousPreferences to Exlude
	 * @return Boolean 
	 */
	private boolean endActiveIfExists(final Offender offender,
			final Date startDate,
			final ReligiousPreference... excludedPreferences) {
		boolean result;
		if (startDate != null) {
			ReligiousPreference existingPreference;
			if (excludedPreferences.length > 1) {
				existingPreference = this.religiousPreferenceDao
						.findByOffenderOnDateExcluding(offender, startDate,
								excludedPreferences);
			} else {
				existingPreference = this.religiousPreferenceDao
						.findByOffenderOnDate(offender, startDate);
			}
			if (existingPreference != null) {
				if (existingPreference.getDateRange() != null) {
					if (existingPreference.getDateRange()
							.getEndDate() != null) {
						result = false;
					} else {
						existingPreference.setDateRange(
								new DateRange(existingPreference.getDateRange()
										.getStartDate(), startDate));
						this.religiousPreferenceDao.makePersistent(
								existingPreference);
						result = true;
					}
				} else {
					existingPreference.setDateRange(
							new DateRange(null, startDate));
					this.religiousPreferenceDao.makePersistent(
							existingPreference);
					result = true;
				}
			} else {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}
	
	/**
	 * Returns religious preferences for offender on date.
	 * 
	 * @param offender offender
	 * @param date date
	 * @return religious preferences for offender on date
	 */
	public ReligiousPreference findByOffenderOnDate(final Offender offender, 
			final Date date) {
		return this.religiousPreferenceDao.findByOffenderOnDate(offender, date);
	}
}
