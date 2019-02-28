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
package omis.incident.report.impl.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import omis.audit.AuditComponentRetriever;
import omis.incident.domain.IncidentStatementCategory;
import omis.incident.domain.Jurisdiction;
import omis.incident.report.IncidentStatementSummary;
import omis.incident.report.IncidentStatementSummaryService;
import omis.incident.report.InvolvedPartySummary;
import omis.location.domain.Location;
import omis.person.domain.Person;

/**
 * Hibernate implementation of incident report service.
 * 
 * @author Yidong Li
 * @author Josh Divine
 * @version 0.1.1 (Feb 14, 2018)
 * @since OMIS 3.0
 */
public class IncidentStatementSummaryServiceHibernateImpl
		implements IncidentStatementSummaryService {

	/* Queries */
	private static final String FIND_INVOLVED_PARTIES_BY_CRITERIA_QUERY_NAME
		= "findInvolvedPartiesByCriteria";
	private static final String FIND_BY_LOCATIN_QUERY_NAME
		= "findIncidentStatementsByLocation";
	private static final String FIND_BY_INVOLVED_PEOPLE_QUERY_NAME
		= "findIncidentStatementsByInvolvedPeople";
	private static final String FIND_BY_DOCUMENTER
		= "findIncidentStatementsByDocumenter";
	
	/* Parameter names */
	private final static String START_DATE_PARAM_NAME = "startDate";
	private final static String END_DATE_PARAM_NAME = "endDate";
	private final static String LOCATION_PARAM_NAME = "location";
	private final static String STATEMENT_ID_PARAM_NAME = "statementId";
	private final static String PERSONS_PARAM_NAME = "persons";
	private final static String JURISDICTION_PARAM_NAME = "jurisdiction";
	private final static String DOCUMENTER_PARAM_NAME = "documenter";
	
	/* Property names. */
	private final static String LOCATION_PROPERTY_NAME = "location";
	private final static String JURISDICTION_PROPERTY_NAME = "jurisdiction";
	
	/* Entity names. */
	private final static String FACILITY_ENTITY_NAME
		= "omis.facility.domain.Facility";
	private static final String INCIDENT_STATEMENT_ENTITY_NAME
		= "omis.incident.domain.IncidentStatement";
	private static final String INVOLVED_PARTY_ENTITY_NAME
		= "omis.incident.domain.InvolvedParty";
	
	private SessionFactory sessionFactory;
		
	/* Component retriever. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/* Constructors. */
	
	/**
	 * Instantiates an Hibernate implementation of incident 
	 * report summary service
	 * @param sessionFactory session factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public IncidentStatementSummaryServiceHibernateImpl(final SessionFactory
		sessionFactory, final AuditComponentRetriever auditComponentRetriever) {
			this.sessionFactory = sessionFactory;
			this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/* Method implementations. */

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public List<InvolvedPartySummary> findInvolvedParties(final Long id) {
		List<InvolvedPartySummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_INVOLVED_PARTIES_BY_CRITERIA_QUERY_NAME)
			.setParameter(STATEMENT_ID_PARAM_NAME, id)
			.setReadOnly(true)
			.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementSummary> findByInvolvedPeople(
			final Date startDate, final Date endDate,
			final Jurisdiction jurisdiction,
			final Location location, final List<Person> involvedPeople) {
		@SuppressWarnings("unchecked")
		List<IncidentStatementSummary> summaries = this.sessionFactory
			.getCurrentSession()
			.getNamedQuery(FIND_BY_INVOLVED_PEOPLE_QUERY_NAME)
			.setDate(START_DATE_PARAM_NAME,startDate)
			.setDate(END_DATE_PARAM_NAME,endDate)
			.setParameterList(PERSONS_PARAM_NAME, involvedPeople)
			.setParameter(LOCATION_PARAM_NAME, location, 
				this.sessionFactory.getClassMetadata(FACILITY_ENTITY_NAME)
				.getPropertyType(LOCATION_PROPERTY_NAME))
			.setParameter(JURISDICTION_PARAM_NAME, jurisdiction, 
				this.sessionFactory.getClassMetadata(
						INCIDENT_STATEMENT_ENTITY_NAME)
				.getPropertyType(JURISDICTION_PROPERTY_NAME))
			.setReadOnly(true)
			.list();
		return summaries;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementSummary> findByLocation(final Date startDate,
			final Date endDate, final Jurisdiction jurisdiction,
			final Location location) {
		@SuppressWarnings("unchecked")
		List<IncidentStatementSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_LOCATIN_QUERY_NAME)
				.setDate(START_DATE_PARAM_NAME, startDate)
				.setDate(END_DATE_PARAM_NAME, endDate)
				.setParameter(LOCATION_PARAM_NAME, location, 
						this.sessionFactory
						.getClassMetadata(FACILITY_ENTITY_NAME)
						.getPropertyType(LOCATION_PROPERTY_NAME))
				.setParameter(JURISDICTION_PARAM_NAME, jurisdiction, 
						this.sessionFactory.getClassMetadata(
								INCIDENT_STATEMENT_ENTITY_NAME)
						.getPropertyType(JURISDICTION_PROPERTY_NAME))
				.setReadOnly(true)
				.list();
				return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementSummary> findByCurrentUser() {
		@SuppressWarnings("unchecked")
		List<IncidentStatementSummary> summaries = this.sessionFactory
				.getCurrentSession()
				.getNamedQuery(FIND_BY_DOCUMENTER)
				.setParameter(DOCUMENTER_PARAM_NAME,
						this.auditComponentRetriever.retrieveUserAccount())
				.setReadOnly(true)
				.list();
		return summaries;
	}

	/** {@inheritDoc} */
	@Override
	public List<IncidentStatementSummary> findByCriteria(final Date startDate, final Date endDate,
			final Jurisdiction jurisdiction, final IncidentStatementCategory category,
			final Person reporter, final String title, final List<String> keywords,
			final Person involvedParty, final String involvedPartyName) {
			//Generate criteria to return involved party
			Criteria involvedPartyCrit = this.sessionFactory.getCurrentSession()
					.createCriteria(INVOLVED_PARTY_ENTITY_NAME, "involvedParty");
			
			//Join criteria for inclusion in restrictions
			Criteria incStatementCrit = involvedPartyCrit.createCriteria("statement", "statement", JoinType.RIGHT_OUTER_JOIN);
			Criteria reporterCrit = incStatementCrit.createCriteria("reporter", "reporter", JoinType.LEFT_OUTER_JOIN);
			
			//Aliases for reference in projections
			incStatementCrit.createAlias("scene.facility", "facility", JoinType.LEFT_OUTER_JOIN)
			.createAlias("scene.complex", "complex", JoinType.LEFT_OUTER_JOIN)
			.createAlias("scene.unit", "unit", JoinType.LEFT_OUTER_JOIN)
			.createAlias("scene.section", "section", JoinType.LEFT_OUTER_JOIN)
			.createAlias("scene.level", "level", JoinType.LEFT_OUTER_JOIN)
			.createAlias("scene.room", "room", JoinType.LEFT_OUTER_JOIN);
					
			reporterCrit.createAlias("name", "reporterName", JoinType.INNER_JOIN);
		
			//Generate restrictions to return all relevant incident statements, based on the state of objects
			//provided.
			if(involvedParty != null) {
				involvedPartyCrit.add(Restrictions.eq("person", involvedParty));
			} else {
				if(involvedPartyName != null) {
					involvedPartyCrit.add(Restrictions.ilike("name", involvedPartyName, MatchMode.ANYWHERE));
				}
			}
			if(startDate != null) {
				incStatementCrit.add(Restrictions.ge("incidentDate", startDate));
			}
			if(endDate != null) {
				incStatementCrit.add(Restrictions.le("incidentDate", endDate));
			}
			if(category != null) {
				incStatementCrit.add(Restrictions.eq("category", category));
			}
			if(jurisdiction != null) {
				incStatementCrit.add(Restrictions.eq("jurisdiction", jurisdiction));
			}
			if(title != null && !title.isEmpty()) {
				incStatementCrit.add(Restrictions.ilike("title", title, MatchMode.ANYWHERE));
			}
			if(reporter != null) {
				incStatementCrit.add(Restrictions.eq("reporter", reporter));
			}
			if(keywords != null) {
				for(String keyword : keywords) {
					if(!keyword.isEmpty()) {
						incStatementCrit.add(Restrictions.or(Restrictions.ilike("summary", keyword, MatchMode.ANYWHERE),
						Restrictions.ilike("scene.location", keyword, MatchMode.ANYWHERE)));
					}
				}
			}

			//Create projections for return values of incident statement summary properties
			ProjectionList incStatementCritProjections = Projections.projectionList();
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.id"), "id"));
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.number"), "reportNumber"));
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.incidentDate"), "incidentDate"));
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.title"), "reportTitle"));
			incStatementCritProjections.add(Projections.alias(Projections.property("reporterName.lastName"), "reportingStaffLastName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("reporterName.firstName"), "reportingStaffFirstName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.scene.location"), "locationName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("facility.name"), "facilityName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("complex.name"), "complexName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("unit.name"), "unitName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("section.name"), "sectionName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("level.name"), "levelName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("room.name"), "roomName"));
			incStatementCritProjections.add(Projections.alias(Projections.property("statement.submissionCategory"), "submissionCategory"));
			
			//Set group by statement in projections
			incStatementCritProjections.add(Projections.groupProperty("statement.id"));
			incStatementCritProjections.add(Projections.groupProperty("statement.number"));
			incStatementCritProjections.add(Projections.groupProperty("statement.incidentDate"));
			incStatementCritProjections.add(Projections.groupProperty("statement.title"));
			incStatementCritProjections.add(Projections.groupProperty("reporterName.lastName"));
			incStatementCritProjections.add(Projections.groupProperty("reporterName.firstName"));
			incStatementCritProjections.add(Projections.groupProperty("statement.scene.location"));
			incStatementCritProjections.add(Projections.groupProperty("facility.name"));
			incStatementCritProjections.add(Projections.groupProperty("complex.name"));
			incStatementCritProjections.add(Projections.groupProperty("unit.name"));
			incStatementCritProjections.add(Projections.groupProperty("section.name"));
			incStatementCritProjections.add(Projections.groupProperty("level.name"));
			incStatementCritProjections.add(Projections.groupProperty("room.name"));
			incStatementCritProjections.add(Projections.groupProperty("statement.submissionCategory"));
			
			
			//Set projections list on criteria (any of the joined criteria can contain the list)
			incStatementCrit.setProjection(incStatementCritProjections);
			
			//Set criteria stipulations
			//Transform results into incident statement summary objects using default constructor.
			incStatementCrit.setResultTransformer(Transformers.aliasToBean(IncidentStatementSummary.class));
			incStatementCrit.isReadOnly();
			incStatementCrit.addOrder(Order.desc("incidentDate")).addOrder(Order.desc("number"));
		
			@SuppressWarnings("unchecked")
			List<IncidentStatementSummary> summaries = involvedPartyCrit.list();
			
			return summaries;
	}
}