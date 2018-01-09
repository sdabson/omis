package omis.religion.report.impl.hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.religion.dao.ReligiousAccommodationAuthorizationDao;
import omis.religion.dao.ReligiousAccommodationDao;
import omis.religion.dao.ReligiousPreferenceDao;
import omis.religion.domain.ReligiousAccommodation;
import omis.religion.domain.ReligiousPreference;
import omis.religion.report.ReligiousPreferenceReportService;
import omis.religion.report.ReligiousPreferenceSummary;

/**
 * Hibernate implementation of religious preference report service.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Mar 27, 2014)
 * @since OMIS 3.0
 */
public class ReligiousPreferenceReportServiceHibernateImpl
		implements ReligiousPreferenceReportService {

	private final ReligiousPreferenceDao religiousPreferenceDao;
	
	private final ReligiousAccommodationAuthorizationDao
	religiousAccommodationAuthorizationDao;
	
	private final ReligiousAccommodationDao religiousAccommodationDao;
	
	/**
	 * Hibernate implementation of report service for religious preferences.
	 * 
	 * @param religiousPreferenceDao data access object for religious
	 * preferences
	 * @param religiousAccommodationAuthorizationDao data access object for
	 * religious accommodation authorizations
	 * @param religiousAccommodationDao data access object for religious
	 * accommodations
	 */
	public ReligiousPreferenceReportServiceHibernateImpl(
			final ReligiousPreferenceDao religiousPreferenceDao,
			final ReligiousAccommodationAuthorizationDao
				religiousAccommodationAuthorizationDao,
			final ReligiousAccommodationDao religiousAccommodationDao) {
		this.religiousPreferenceDao = religiousPreferenceDao;
		this.religiousAccommodationAuthorizationDao
			= religiousAccommodationAuthorizationDao;
		this.religiousAccommodationDao = religiousAccommodationDao;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<ReligiousPreferenceSummary> findSummariesByOffender(
			final Offender offender) {
		List<ReligiousPreferenceSummary> preferenceSummaries
			= new ArrayList<ReligiousPreferenceSummary>();
		List<ReligiousPreference> preferences
			= this.religiousPreferenceDao.findByOffender(offender);
		List<ReligiousAccommodation> accommodations
			= this.religiousAccommodationDao.findAll();
		for (ReligiousPreference preference : preferences) {
			List<String> authorizedAccommodationNames
				= new ArrayList<String>();
			for (ReligiousAccommodation accommodation : accommodations) {
				if (this.religiousAccommodationAuthorizationDao
						.find(preference, accommodation) != null) {
					authorizedAccommodationNames.add(accommodation.getName());
				}
			}
			final Long id = preference.getId();
			final String religionName = preference.getReligion().getName();
			final String religionGroupName
				= preference.getReligion().getGroup().getName();
			final Date startDate = DateRange.getStartDate(
					preference.getDateRange());
			final Date endDate = DateRange.getEndDate(
					preference.getDateRange());
			final List<String>
				unmodifiableAuthorizedAccommodationNames
					= Collections.unmodifiableList(authorizedAccommodationNames);
			final Boolean approved;
			if (preference.getVerificationSignature() != null) {
				approved = preference.getVerificationSignature()
					.getResult();
			} else {
				approved = null;
			}
			preferenceSummaries.add(new ReligiousPreferenceSummary() {

				private static final long serialVersionUID = 1L;
				
				/** {@inheritDoc} */
				@Override
				public Long getId() {
					return id;
				}

				/** {@inheritDoc} */
				@Override
				public String getReligionName() {
					return religionName;
				}

				/** {@inheritDoc} */
				@Override
				public String getReligionGroupName() {
					return religionGroupName;
				}
				
				/** {@inheritDoc} */
				@Override
				public Date getStartDate() {
					return startDate;
				}

				/** {@inheritDoc} */
				@Override
				public Date getEndDate() {
					return endDate;
				}

				/** {@inheritDoc} */
				@Override
				public List<String>
				getAuthorizedAccommodationNames() {
					return unmodifiableAuthorizedAccommodationNames;
				}

				/** {@inheritDoc} */
				@Override
				public Boolean getApproved() {
					return approved;
				}
			});
		}
		return preferenceSummaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<String> findAccommodationNames() {
		List<ReligiousAccommodation> accommodations
			= this.religiousAccommodationDao.findAll();
		List<String> accommodationNames = new ArrayList<String>();
		for (ReligiousAccommodation accommodation : accommodations) {
			accommodationNames.add(accommodation.getName());
		}
		return accommodationNames;
	}
}