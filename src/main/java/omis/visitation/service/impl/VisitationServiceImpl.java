package omis.visitation.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.exception.DuplicateEntityFoundException;
import omis.facility.dao.FacilityDao;
import omis.facility.domain.Facility;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.relationship.dao.RelationshipDao;
import omis.relationship.domain.Relationship;
import omis.visitation.dao.VisitationAssociationDao;
import omis.visitation.domain.VisitationApproval;
import omis.visitation.domain.VisitationAssociation;
import omis.visitation.domain.VisitationAssociationFlags;
import omis.visitation.service.VisitationService;

/**
 * Visitation service implementation.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sep 22, 2014)
 * @since OMIS 3.0
 * @deprecated This is not used and should likely be removed.
 */
@Deprecated
public class VisitationServiceImpl implements VisitationService {

	/* Data access objects. */
	
	private VisitationAssociationDao visitationAssociationDao;
	
	private RelationshipDao relationshipDao;
	
	private FacilityDao facilityDao;
	
	/* Instance factories. */
	
	private InstanceFactory<VisitationAssociation> 
	visitationAssociationInstanceFactory;
	
	private InstanceFactory<Relationship> relationshipInstanceFactory;
	
	/* Component retrievers. */
	
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates an instance of visitation service with the specified data
	 * access objects and instance factories.
	 */
	public VisitationServiceImpl(
			final VisitationAssociationDao visitationAssociationDao,
			final RelationshipDao relationshipDao,
			final FacilityDao facilityDao,
			final InstanceFactory<VisitationAssociation> 
			visitationAssociationInstanceFactory,
			final InstanceFactory<Relationship> relationshipInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.visitationAssociationDao = visitationAssociationDao;
		this.relationshipDao = relationshipDao;
		this.facilityDao = facilityDao;
		this.visitationAssociationInstanceFactory
			= visitationAssociationInstanceFactory;
		this.relationshipInstanceFactory = relationshipInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	
	/** {@inheritDoc} */
	@Override
	public VisitationAssociation approve(final Offender offender, 
			final Person visitor, final Date decisionDate, 
			final DateRange dateRange, final Boolean money, 
			final Boolean nonContact, final Boolean courtOrder, 
			final Boolean specialVisit)
		throws DuplicateEntityFoundException, DateConflictException {
		if (dateRange == null) {
			throw new IllegalStateException("Date range cannot be null");
		}
		Relationship relationship = this.setupRelationship(offender, visitor);
		if (this.visitationAssociationDao.find(relationship,
				dateRange.getStartDate()) != null) {
			throw new DuplicateEntityFoundException("Duplicate visitation"
					+ " association found");
		}
		if (this.visitationAssociationDao.findByDateRange(relationship, 
				dateRange.getStartDate(), dateRange.getEndDate()) != null) {
			throw new DateConflictException("Visitation association"
					+ " with same relationship and overlapping date range"
					+ " found");
		}
		VisitationAssociation association = 
				this.visitationAssociationInstanceFactory.createInstance();
		this.populateVisitationAssociation(association, relationship, 
				decisionDate, dateRange.getStartDate(), 
				dateRange.getEndDate(), money, nonContact, courtOrder,
				specialVisit, true);
		association.setCreationSignature(this.newCreationSignature());
		return this.visitationAssociationDao.makePersistent(association);
	}
	
	/** {@inheritDoc} */
	@Override
	public VisitationAssociation deny(final Offender offender, 
			final Person visitor, final Date decisionDate, 
			final DateRange dateRange, final Boolean money, 
			final Boolean nonContact, final Boolean courtOrder, 
			final Boolean specialVisit)
		throws DuplicateEntityFoundException, DateConflictException {
		if (dateRange == null) {
			throw new IllegalStateException("Date range cannot be null");
		}
		VisitationAssociation association = 
				this.visitationAssociationInstanceFactory.createInstance();
		this.populateVisitationAssociation(association, this.setupRelationship(
				offender, visitor), decisionDate, dateRange.getStartDate(), 
				dateRange.getEndDate(), money, nonContact, courtOrder,
				specialVisit, false);
		association.setCreationSignature(this.newCreationSignature());
		return association;
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociation> findApprovedAssociationsByOffender(
			final Offender offender, final Date date) {
		return this.visitationAssociationDao
				.findApprovedVisitationAssociationsByOffender(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociation> findAssociationsByOffender(
			final Offender offender, final Date date) {
		return this.visitationAssociationDao
				.findVisitationAssociationByOffender(offender, date);
	}

	/** {@inheritDoc} */
	@Override
	public List<VisitationAssociation> findSpecialVisitAssociationsByOffender(
			final Offender offender, final Date date) {
		return this.visitationAssociationDao
				.findSpecialVisitVisitationAssociationsByOffender(offender, 
						date);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final VisitationAssociation visitationAssociation) {
		// TODO Relationship removed if not being used anywhere else? JNo
		this.visitationAssociationDao.makeTransient(visitationAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public List<Facility> findFacilities() {
		return this.facilityDao.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public VisitationAssociation endVisitationAssociaiton(
			final VisitationAssociation visitationAssociation, 
			final Date endDate) {
		if (visitationAssociation.getDateRange() == null) {
			throw new IllegalStateException("A daterange is required in order"
					+ " to properly end a visitation association");
		}
		visitationAssociation.getDateRange().setEndDate(endDate);
		return this.visitationAssociationDao
				.makePersistent(visitationAssociation);
	}

	/** {@inheritDoc} */
	@Override
	public VisitationAssociation updateAssociation(
			VisitationAssociation visitationAssociation, Date decisionDate,
			DateRange dateRange, Boolean money, Boolean nonContact,
			Boolean courtOrder, Boolean specialVisit, Boolean approved)
		throws DuplicateEntityFoundException, DateConflictException {
		if (visitationAssociation.getDateRange() == null) {
			throw new IllegalStateException("A daterange is required in order"
					+ " to properly end a visitation association");
		}
		this.populateVisitationAssociation(visitationAssociation, 
				visitationAssociation.getRelationship(), decisionDate, 
				dateRange.getStartDate(), dateRange.getEndDate(), money, 
				nonContact, courtOrder, specialVisit, approved);
		return this.visitationAssociationDao.makePersistent(
				visitationAssociation);
	}
	
	/* Helper methods. */

	/*
	 * Returns a new creation signature.
	 * 
	 * @return new creation signature
	 */
	private CreationSignature newCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	/*
	 * Returns a new update signature.
	 *  
	 * @return new update signature
	 */
	private UpdateSignature newUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	/*
	 * Check for a relationship between the specified offender and the
	 * specified visitor. If one is found, it is returned. If one is not
	 * found, a new relationship is instantiates with the specified offender
	 * as the first person and the specified visitor as the second person.
	 * 
	 * @param offender offender
	 * @param visitor visitor
	 * @return relationship with the specified offender and visitor
	 */
	private Relationship setupRelationship(final Offender offender, 
			final Person visitor) {
		final Relationship relationship;
		if (this.relationshipDao.findByPeople(offender, visitor) != null) {
			relationship = this.relationshipDao.findByPeople(offender, visitor);
		} else {
			relationship = this.relationshipInstanceFactory.createInstance();
			relationship.setFirstPerson(offender);
			relationship.setSecondPerson(visitor);
			relationship.setCreationSignature(this.newCreationSignature());
			this.relationshipDao.makePersistent(relationship);
		}
		return relationship;
	}

	/*
	 * Populates the specified visitation association with the specified
	 * relationship, decision date, and status of approved or not.
	 * 
	 * @param association visitation association
	 * @param relationship relationship
	 * @param decisionDate decision date
	 * @param money whether money is accepted or not
	 * @param nonContact whether a non contact order is in place
	 * @param courtOrder whether visitation is the result of a court order 
	 * @param specialVisit whether this association is subject to special
	 * visit authorization
	 * @return populated visitation association
	 */
	private VisitationAssociation populateVisitationAssociation(
			final VisitationAssociation association,
			final Relationship relationship, final Date decisionDate, 
			final Date startDate, final Date endDate,
			final Boolean money, final Boolean nonContact, 
			final Boolean courtOrder, final Boolean specialVisit, 
			final Boolean approved) {
		association.setRelationship(relationship);
		association.setDateRange(new DateRange(startDate, endDate));
		association.setApproval(new VisitationApproval(approved, decisionDate));
		association.setFlags(new VisitationAssociationFlags(money, nonContact,
				courtOrder, specialVisit));
		association.setUpdateSignature(this.newUpdateSignature());
		return association;
	}
}