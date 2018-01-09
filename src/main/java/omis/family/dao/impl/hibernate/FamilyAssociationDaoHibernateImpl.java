package omis.family.dao.impl.hibernate;

/*import java.util.Date;*/
import java.util.Date;
import java.util.List;

import omis.dao.impl.hibernate.GenericHibernateDaoImpl;
import omis.datatype.DateRange;
import omis.family.dao.FamilyAssociationDao;
import omis.family.domain.FamilyAssociation;
import omis.offender.domain.Offender;
import omis.relationship.domain.Relationship;

import org.hibernate.SessionFactory;

/**
 * Implementation of data access object for family association.
 * 
 * @author Joel Norris
 * @author Yidong Li
 * @author Sheronda Vaughn
 * @version 0.1.1 (nov 21, 2017)
 * @since OMIS 3.0
 */
public class FamilyAssociationDaoHibernateImpl 
	extends GenericHibernateDaoImpl<FamilyAssociation>
	implements FamilyAssociationDao {

	/* Query names. */
	private static final String FIND_BY_RELATIONSHIP_AND_DATERANGE_QUERY_NAME
		= "findFamilyAssociationByRelationshipAndDateRange";
	private static final String 
		FIND_BY_RELATIONSHIP_AND_DATERANGE_EXCLUDING_QUERY_NAME
	 	= "findFamilyAssociationByRelationshipAndDateRangeExcluding";
	private static final String FIND_FAMILY_ASSOCIATION_ON_DATE_QUERY_NAME
		= "findFamilyAssociationOnDate";
	private static final String FIND_DATE_RANGE_OVERLAP_QUERY_NAME
		= "findFamilyAssociationDateRangeOverlap";
	
	private static final String FIND_ALL_ASSOCIATIONS_QUERY_NAME
		= "findAllFamilyAssociations";
	
	private static final String FIND_CONFLICTING_ASSOCIATIONS_COUNT_QUERY_NAME
		= "findConflictingFamilyAssociationsCount";
	
	private static final String 
		FIND_CONFLICTING_ASSOCIATIONS_EXCLUDING_COUNT_QUERY_NAME
		= "findConflictingFamilyAssociationsExcludingCount";
	
	private static final String FIND_ALL_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME
		= "findAllAssociationsByOffender";
		
	/* Parameter names. */
	private static final String RELATIONSHIP_PARAM_NAME = "relationship";
	private static final String FAMILY_ASSOCIATION_PARAM_NAME = "association";
	private static final String START_DATE_PARAM_NAME = "startDate";
	private static final String END_DATE_PARAM_NAME = "endDate";
	private static final String DATE_PARAM_NAME = "date";
	private static final String EXCLUDED_FAMILY_ASSOCIATION_PARAM_NAME 
		= "excludedFamilyAssociation";
	private static final String OFFENDER_PARAM_NAME = "offender";
			
	/* Property names. */
	
	/**
	 * Instantiates a default instance of family association data access object.
	 * 
	 * @param sessionFactory session factory
	 * @param entityName entity name
	 */
	public FamilyAssociationDaoHibernateImpl(
			final SessionFactory sessionFactory,
			final String entityName) {
		super(sessionFactory, entityName);
	}
	
	/** {@inheritDoc} */
	@Override
	public FamilyAssociation find(
			final Relationship relationship, final DateRange dateRange) {
		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();
		FamilyAssociation association = (FamilyAssociation) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_BY_RELATIONSHIP_AND_DATERANGE_QUERY_NAME)
			.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
			.setTimestamp(START_DATE_PARAM_NAME, startDate)
			.setTimestamp(END_DATE_PARAM_NAME, endDate)
			.uniqueResult();
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public FamilyAssociation findExcluding(
			final FamilyAssociation association, 
			final Relationship relationship, final DateRange dateRange) {
		Date startDate = dateRange.getStartDate();
		Date endDate = dateRange.getEndDate();
		FamilyAssociation familyAssociation = (FamilyAssociation) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
					FIND_BY_RELATIONSHIP_AND_DATERANGE_EXCLUDING_QUERY_NAME)
			.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
			.setParameter(FAMILY_ASSOCIATION_PARAM_NAME, association)
			.setTimestamp(START_DATE_PARAM_NAME, startDate)
			.setTimestamp(END_DATE_PARAM_NAME, endDate)
			.uniqueResult();
		return familyAssociation;
	}

	/** {@inheritDoc} */
	@Override
	public FamilyAssociation findOnDate(final Relationship relationship, 
			final Date date) {
		FamilyAssociation familyAssociation = (FamilyAssociation)
				this.getSessionFactory()
				.getCurrentSession()
				.getNamedQuery(FIND_FAMILY_ASSOCIATION_ON_DATE_QUERY_NAME)
				.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
				.setDate(DATE_PARAM_NAME, date)
				.uniqueResult();
		return familyAssociation;
	}
	
	/** {@inheritDoc} */
	@Override
	public long findDateRangeOverLap(final Relationship relationship, 
				final DateRange dateRange) {
		long overlaps = (long) this.getSessionFactory()
			.getCurrentSession()
			.getNamedQuery(FIND_DATE_RANGE_OVERLAP_QUERY_NAME)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
			.uniqueResult();
		return overlaps;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<FamilyAssociation> findAll() {
		@SuppressWarnings("unchecked")
		List<FamilyAssociation> associations = (List<FamilyAssociation>) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_ASSOCIATIONS_QUERY_NAME)
			.list();
		return associations;
	};
	
	/** {@inheritDoc} */
	@Override
	public Long findConflicting(final Relationship relationship,
		final DateRange dateRange) {
		Long count = (long) 0;
		count = (Long) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_CONFLICTING_ASSOCIATIONS_COUNT_QUERY_NAME)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
			.uniqueResult();
		return count;
	};
	
	/** {@inheritDoc} */
	@Override
	public Long findConflictingExcluding(final Relationship relationship,
		final DateRange dateRange, final FamilyAssociation familyAssociation) {
		Long count = (long) 0;
		count = (Long) this.getSessionFactory().getCurrentSession()
			.getNamedQuery(
					FIND_CONFLICTING_ASSOCIATIONS_EXCLUDING_COUNT_QUERY_NAME)
			.setDate(START_DATE_PARAM_NAME, dateRange.getStartDate())
			.setDate(END_DATE_PARAM_NAME, dateRange.getEndDate())
			.setParameter(RELATIONSHIP_PARAM_NAME, relationship)
			.setParameter(
					EXCLUDED_FAMILY_ASSOCIATION_PARAM_NAME, familyAssociation)
			.uniqueResult();
		return count;
	};
	
	/** {@inheritDoc} */
	@Override
	public List<FamilyAssociation> findByOffender(final Offender offender) {
		@SuppressWarnings("unchecked")
		List<FamilyAssociation> associations = (List<FamilyAssociation>) 
			this.getSessionFactory().getCurrentSession()
			.getNamedQuery(FIND_ALL_ASSOCIATIONS_BY_OFFENDER_QUERY_NAME)
			.setParameter(OFFENDER_PARAM_NAME, offender)
			.list();
		return associations;
	};
}