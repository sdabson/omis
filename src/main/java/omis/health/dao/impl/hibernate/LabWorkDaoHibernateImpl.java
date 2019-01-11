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
package omis.health.dao.impl.hibernate;

import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.health.dao.LabWorkDao;
import omis.health.domain.Lab;
import omis.health.domain.LabWork;
import omis.health.domain.LabWorkCategory;
import omis.health.domain.LabWorkReferral;
import omis.health.domain.OffenderAppointmentAssociation;
import omis.offender.domain.Offender;
import omis.facility.domain.Facility;

import org.hibernate.SessionFactory;

/**
 * Lab work data access object hibernate implementation.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @version 0.1.0 (Nov. 8, 2018)
 * @since OMIS 3.0
 */
public class LabWorkDaoHibernateImpl 
	extends GenericHibernateDaoImpl<LabWork> 
	implements LabWorkDao {
	
	/* Parameter names. */
	
	private static final String OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME 
		= "offenderAppointmentAssociation";

	private static final String LAB_WORK_PARAMETER_NAME = "labWork";
	
	private static final String SAMPLE_LAB_PARAMETER_NAME = "sampleLab";
	
	private static final String RESULTS_LAB_PARAMETER_NAME = "resultsLab";
	
	private static final String LAB_WORK_CATEGORY_PARAMETER_NAME 
		= "labWorkCategory";
	
	private static final String LAB_WORK_REFERRAL_PARAMETER_NAME 
		= "labWorkReferral";
	
	private static final String DATE_PARAMETER_NAME = "date";
	
	private static final String OFFENDER_PARAMETER_NAME = "offender";
	
	private static final String FACILITY_PARAMETER_NAME = "facility";
	
	private static final String START_DATE_PARAMETER_NAME = "startDate";
	
	private static final String END_DATE_PARAMETER_NAME = "endDate";
	
	private static final String SAMPLE_MUST_BE_TAKEN_PARAMETER_NAME 
		= "sampleMustBeTaken";
	
	private static final String OFFENDER_APPOINTMENT_ASSO_PARAMETER_NAME = "offenderAppointmentAssociation";
	
	/* Query names. */
	
	private static final String 
	FIND_OFFENDER_APPOINTMENT_ASSOCIATION_QUERY_NAME 
		="findOffenderAppointmentAssociation";
	
	private static final String FIND_REQUIRED_LAB_WORK = "findRequiredLabWork";
	
	private static final String FIND_REQUIRED_LAB_WORK_EXCLUDING_QUERY_NAME
		= "findRequiredLabWorkExcluding";
	
	private static final String 
	FIND_LAB_WORKS_BY_OFFENDER_APPOINTMENT_ASSOCIATION_QUERY_NAME
		= "findByOffenderAppointmentAssociation";
	
	private static final String FIND_MATCHING_LAB_WORK = "findMatchingLabWork";
	
	private static final String FIND_ASSOCIATED_LAB_WORK_QUERY_NAME
		= "findByLabWorkReferral";
	
	private static final String FIND_LAB_WORKS_BY_OFFENDER_AND_DATE_QUERY_NAME
		= "findLabWorksBYOffenderAndDate";

	private static final String FIND_IMCOMPLETE_LAB_WORKS_BY_OFFENDER_QUERY_NAME
		= "findIncompleteLabWorkByOffender";
	
	private static final String FIND_IMCOMPLETE_LAB_WORKS_BY_SEARCH_QUERY_NAME
		= "findIncompleteLabWorkByOffenderBySearch";
	
	private static final String FIND_IMCOMPLETE_LAB_WORKS_BY_FACILITY_QUERY_NAME
		="findIncompleteLabWorkByFacility";
	
	private static final String FIND_EXISTING_LAB_WORK_QUERY_NAME
		= "findExistingLabWork";
	
	/**
	 * Instantiates an instance of lab work DAO with the specified session
	 * factory and entity name.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public LabWorkDaoHibernateImpl(final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}

	/** {@inheritDoc} */
	@Override
	public LabWork find(final OffenderAppointmentAssociation association) {
		LabWork labWork = (LabWork) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_OFFENDER_APPOINTMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME,
						association)
				.uniqueResult();
		return labWork;
	}
	

	/** {@inheritDoc} */
	@Override
	public LabWork findLabWork(
			final OffenderAppointmentAssociation offenderAppointmentAssociation,
			final LabWorkCategory category, final Lab sampleLab, 
			final Lab resultsLab) {
		LabWork labWork = (LabWork) getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_MATCHING_LAB_WORK)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME,
						offenderAppointmentAssociation)
				.setParameter(LAB_WORK_CATEGORY_PARAMETER_NAME, category)
				.setParameter(SAMPLE_LAB_PARAMETER_NAME, sampleLab)
				.setParameter(RESULTS_LAB_PARAMETER_NAME, resultsLab)
				.uniqueResult();
		return labWork;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findExcluding(final LabWork labWork,
			final OffenderAppointmentAssociation offenderAppointmentAssociation,
			final LabWorkCategory category, final Lab sampleLab, 
			final Lab resultsLab) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REQUIRED_LAB_WORK_EXCLUDING_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME,
						offenderAppointmentAssociation)
				.setParameter(LAB_WORK_CATEGORY_PARAMETER_NAME, category)
				.setParameter(SAMPLE_LAB_PARAMETER_NAME, sampleLab)
				.setParameter(RESULTS_LAB_PARAMETER_NAME, resultsLab)
				.setParameter(LAB_WORK_PARAMETER_NAME, labWork)
				.list();
		return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWork> findRequiredLabWork(
			final OffenderAppointmentAssociation 
			offenderAppointmentAssociation) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_REQUIRED_LAB_WORK)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME,
						offenderAppointmentAssociation)
				.list();
		return labWorks;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findByOffenderAppointmentAssociation(
			final OffenderAppointmentAssociation
			offenderAppointmentAssociation) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(
				FIND_LAB_WORKS_BY_OFFENDER_APPOINTMENT_ASSOCIATION_QUERY_NAME)
				.setParameter(OFFENDER_APPOINTMENT_ASSOCIATION_PARAMETER_NAME,  
						offenderAppointmentAssociation)
				.list();
		return labWorks;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findLabWorkByReferral(final LabWorkReferral referral) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_ASSOCIATED_LAB_WORK_QUERY_NAME)
				.setParameter(LAB_WORK_REFERRAL_PARAMETER_NAME,
						referral)
				.list();
		return labWorks;
	}

	/** {@inheritDoc} */
	@Override
	public List<LabWork> findByOffenderAndDate(final Offender offender, 
			final Date date) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_LAB_WORKS_BY_OFFENDER_AND_DATE_QUERY_NAME)
				.setParameter(OFFENDER_PARAMETER_NAME, offender)
				.setDate(DATE_PARAMETER_NAME, date)
				.list();
		return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByOffender(final Offender offender, 
			final Facility facility, final Date startDate, final Date endDate, 
			final Boolean sampleMustBeTaken){
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
		   .getCurrentSession()
		   .getNamedQuery(FIND_IMCOMPLETE_LAB_WORKS_BY_OFFENDER_QUERY_NAME)
		   .setParameter(OFFENDER_PARAMETER_NAME, offender)
		   .setParameter(FACILITY_PARAMETER_NAME, facility)
		   .setDate(START_DATE_PARAMETER_NAME, startDate)
		   .setDate(END_DATE_PARAMETER_NAME, endDate)
		   .setParameter(SAMPLE_MUST_BE_TAKEN_PARAMETER_NAME, sampleMustBeTaken)
		   .list();
		return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByOffenderBySearch(final Offender 
			offender, final Facility facility, final Date startDate, 
			final Date endDate, final Boolean sampleMustBeTaken){
	   @SuppressWarnings("unchecked")
	  List<LabWork> labWorks = getSessionFactory()
	  .getCurrentSession()
	  .getNamedQuery(FIND_IMCOMPLETE_LAB_WORKS_BY_SEARCH_QUERY_NAME)
	  .setParameter(OFFENDER_PARAMETER_NAME, offender)
	  .setParameter(FACILITY_PARAMETER_NAME, facility)
	  .setDate(START_DATE_PARAMETER_NAME, startDate)
	  .setDate(END_DATE_PARAMETER_NAME, endDate)
	  .setParameter(SAMPLE_MUST_BE_TAKEN_PARAMETER_NAME, sampleMustBeTaken)
	  .list();
	  return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<LabWork> findIncompleteByFacility(final Facility facility, 
			final Date startDate, final Date endDate, 
			final Boolean sampleMustBeTaken) {
		@SuppressWarnings("unchecked")
		List<LabWork> labWorks = getSessionFactory()
		.getCurrentSession()
		.getNamedQuery(FIND_IMCOMPLETE_LAB_WORKS_BY_FACILITY_QUERY_NAME)
		.setParameter(FACILITY_PARAMETER_NAME, facility)
		.setDate(START_DATE_PARAMETER_NAME, startDate)
		.setDate(END_DATE_PARAMETER_NAME, endDate)
		.setParameter(SAMPLE_MUST_BE_TAKEN_PARAMETER_NAME, sampleMustBeTaken)
		.list();
		return labWorks;
	}
	
	/** {@inheritDoc} */
	@Override
	public LabWork findExisting(final OffenderAppointmentAssociation
			offenderAppointmentAssociation) {
		LabWork labWork = (LabWork) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_EXISTING_LAB_WORK_QUERY_NAME)
			.setParameter(OFFENDER_APPOINTMENT_ASSO_PARAMETER_NAME, offenderAppointmentAssociation)
			.setReadOnly(true)
			.uniqueResult();
		return labWork;
	}
}