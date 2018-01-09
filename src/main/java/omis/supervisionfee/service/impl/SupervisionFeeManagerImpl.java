package omis.supervisionfee.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.court.dao.CourtDao;
import omis.court.domain.Court;
import omis.datatype.DateRange;
import omis.exception.DateConflictException;
import omis.instance.factory.InstanceFactory;
import omis.offender.domain.Offender;
import omis.person.domain.Person;
import omis.supervisionfee.dao.MonthlySupervisionFeeDao;
import omis.supervisionfee.dao.SupervisionFeeRequirementDao;
import omis.supervisionfee.dao.SupervisionFeeRequirementReasonDao;
import omis.supervisionfee.domain.MonthlySupervisionFee;
import omis.supervisionfee.domain.MonthlySupervisionFeeAuthorityCategory;
import omis.supervisionfee.domain.SupervisionFeeRequirement;
import omis.supervisionfee.domain.SupervisionFeeRequirementReason;
import omis.supervisionfee.service.SupervisionFeeManager;

/**
 * Supervision Fee Manager Implementation.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Aug 28, 2014)
 * @since OMIS 3.0
 */
public class SupervisionFeeManagerImpl 
				implements SupervisionFeeManager {

	/* Data access objects. */
	
	private final MonthlySupervisionFeeDao  monthlySupervisionFeeDao;
	private final SupervisionFeeRequirementDao supervisionFeeRequirementDao;
	private final SupervisionFeeRequirementReasonDao 
					supervisionFeeRequirementReasonDao;
	private final CourtDao courtDao;
	
	/* Instance factories. */
	
	private final InstanceFactory<MonthlySupervisionFee> 
					monthlySupervisionFeeInstanceFactory;
	private final InstanceFactory<SupervisionFeeRequirement>
					supervisionFeeRequirementInstanceFactory;
		
	/* Component retrievers. */
	
	private final AuditComponentRetriever auditComponentRetriever;	
	
	/**
	 * Instantiates an instance of supervision fee manager
	 * with the specified data access objects and component retriever.
	 * 
	 * @param monthlySupervisionFeeDao monthly supervision fee 
	 * data access object
	 * @param supervisionFeeRequirementDao supervision fee requirement 
	 * data access object
	 * @param supervisionFeeRequirementReasonDao supervision fee requirement
	 * reason date access object
	 * @param courtDao court data access object
	 * @param monthlySupervisionFeeInstanceFactory monthly supervision fee 
	 * instance factory
	 * @param supervisionFeeRequirementInstanceFactory supervision fee 
	 * requirement instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public SupervisionFeeManagerImpl(
					final MonthlySupervisionFeeDao monthlySupervisionFeeDao, 
					final SupervisionFeeRequirementDao 
						supervisionFeeRequirementDao,
					final SupervisionFeeRequirementReasonDao 
						supervisionFeeRequirementReasonDao,
					final CourtDao courtDao,
					final InstanceFactory<MonthlySupervisionFee> 
						monthlySupervisionFeeInstanceFactory,
					final InstanceFactory<SupervisionFeeRequirement> 
						supervisionFeeRequirementInstanceFactory,
					final AuditComponentRetriever auditComponentRetriever) {
					
		this.monthlySupervisionFeeDao = monthlySupervisionFeeDao;
		this.supervisionFeeRequirementDao = supervisionFeeRequirementDao;	
		this.supervisionFeeRequirementReasonDao 
			= supervisionFeeRequirementReasonDao;
		this.courtDao = courtDao;
		this.monthlySupervisionFeeInstanceFactory 
			= monthlySupervisionFeeInstanceFactory;
		this.supervisionFeeRequirementInstanceFactory 
			= supervisionFeeRequirementInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;	
	}
	
	/** {@inheritDoc} */
	@Override
	public MonthlySupervisionFee imposeMonthlySupervisionFee(
					final Offender offender, final BigDecimal fee, 
					final Date startDate, final Date endDate,
					final MonthlySupervisionFeeAuthorityCategory category, 
					final String comment) 
					throws DateConflictException {
		DateRange range = new DateRange(startDate, endDate);

		if (this.monthlySupervisionFeeDao.findByDateRange(
						offender, startDate, endDate).size() > 0) {
			throw new DateConflictException(
				"Fee within this date range already exists.");
		}
		
		MonthlySupervisionFee monthlySupervisionFee =	
						this.monthlySupervisionFeeInstanceFactory
						.createInstance();
		
		monthlySupervisionFee.setOffender(offender);	
		monthlySupervisionFee.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		monthlySupervisionFee.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));		
		monthlySupervisionFee.setDateRange(range);
		monthlySupervisionFee.setAuthorityCategory(category);
		monthlySupervisionFee.setFee(fee);
		monthlySupervisionFee.setComment(comment);
				
		this.monthlySupervisionFeeDao.makePersistent(monthlySupervisionFee);
		return monthlySupervisionFee;
	}

	/** {@inheritDoc} */
	@Override
	public MonthlySupervisionFee endMonthlySupervisionFee(
					final MonthlySupervisionFee monthlySupervisionFee,
					final Date endDate)
					throws DateConflictException {
		if (this.monthlySupervisionFeeDao.findByDateRange(
						monthlySupervisionFee.getOffender(), 
						monthlySupervisionFee.getDateRange()
						.getStartDate(), endDate).size() > 0) {
			throw new DateConflictException(
				"Fee within this date range alread exists.");
		}
		
		monthlySupervisionFee.getDateRange().setEndDate(endDate);
		this.monthlySupervisionFeeDao.makePersistent(monthlySupervisionFee);
		return monthlySupervisionFee;
	}

	/** {@inheritDoc} */
	@Override
	public void removeMonthlySupervisionFee(
					final MonthlySupervisionFee monthlySupervisionFee) {
		this.monthlySupervisionFeeDao.makeTransient(monthlySupervisionFee);
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionFeeRequirement imposeSupervisionFeeRequirement(
					final MonthlySupervisionFee monthlySupervisionFee, 
					final BigDecimal fee, final Person officer, 
					final Date startDate, final Date endDate, 
					final String comments, 
					final SupervisionFeeRequirementReason reason) 
					throws DateConflictException {
		//creates a new instance of the requirement
		SupervisionFeeRequirement  requirement = 
							this.supervisionFeeRequirementInstanceFactory
							.createInstance();
		if (this.supervisionFeeRequirementDao.findByDateRange(
						startDate, endDate, monthlySupervisionFee).size() > 0) {
			throw new DateConflictException(
				"Fee within this date range already exists.");
		}
		if (this.supervisionFeeRequirementDao
						.findWithinMonthlySupervisionFeeDateRange(
						startDate, endDate, monthlySupervisionFee).size() > 0) {
			throw new DateConflictException(
				"Fee requirement needs to be within this monthly "
				+ "supervision fee date range.");
		}		
		requirement.setMonthlySupervisionFee(monthlySupervisionFee);
					
		//create new dateRange		
		DateRange dateRange = new DateRange();
		//set startDate and endDate on dateRange
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		//set startDate on new requirement
		requirement.setDateRange(dateRange);		
		//populates the supervision fee requirement	
		requirement.setReason(reason);		
		requirement.setComment(comments);
		requirement.setFee(fee);
		requirement.setOfficer(officer);
		requirement.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));		
		requirement.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));	
		this.supervisionFeeRequirementDao.makePersistent(requirement);
		
		return requirement;
	}
	
	/** {@inheritDoc} */
	@Override
	public SupervisionFeeRequirement imposeSupervisionFeeRequirement(
					final MonthlySupervisionFee monthlySupervisionFee, 
					final BigDecimal fee, final Court court, 
					final Date startDate, final Date endDate, 
					final String comments, 
					final SupervisionFeeRequirementReason reason) 
					throws DateConflictException {			
		if (this.supervisionFeeRequirementDao.findByDateRange(
						startDate, endDate, monthlySupervisionFee).size() > 0) {
			throw new DateConflictException(
					"Fee within this date range already exists.");				
		}
		if (this.supervisionFeeRequirementDao
						.findWithinMonthlySupervisionFeeDateRange(
						startDate, endDate, monthlySupervisionFee).size() > 0) {
			throw new DateConflictException(
				"Fee requirement needs to be within this monthly "
				+ "supervision fee date range.");
		}		
		//creates a new instance of the requirement
		SupervisionFeeRequirement  requirement = 
						this.supervisionFeeRequirementInstanceFactory
						.createInstance();
		//create new dateRange
		DateRange dateRange = new DateRange();
		//set startDate on dateRange
		dateRange.setStartDate(startDate);
		dateRange.setEndDate(endDate);
		//set startDate on new requirement
		requirement.setDateRange(dateRange);				
		//populates the supervision fee requirement	
		requirement.setMonthlySupervisionFee(monthlySupervisionFee);
		requirement.setFee(fee);
		requirement.setCourt(court);
		requirement.setComment(comments);
		requirement.setReason(reason);
		requirement.setCreationSignature(new CreationSignature(
						this.auditComponentRetriever.retrieveUserAccount(), 
						this.auditComponentRetriever.retrieveDate()));
		requirement.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));
		this.supervisionFeeRequirementDao.makePersistent(requirement);
		
		return requirement;
	}

	/** {@inheritDoc} */
	@Override
	public SupervisionFeeRequirement endSupervisionFeeRequirement(
					final SupervisionFeeRequirement supervisionFeeRequirement, 
					final Date endDate) 
					throws DateConflictException {
		if (this.supervisionFeeRequirementDao.findByDateRangeExcluding(
						supervisionFeeRequirement.getDateRange().getStartDate(),
						endDate, supervisionFeeRequirement
						.getMonthlySupervisionFee(), 
						supervisionFeeRequirement).size() > 0) {
			throw new DateConflictException(
				"Fee within this date range already exists.");
		}	
		supervisionFeeRequirement.getDateRange().setEndDate(endDate);
		return supervisionFeeRequirement;
	}

	/** {@inheritDoc} */
	@Override
	public void removeSupervisionFeeRequirement(
					final SupervisionFeeRequirement supervisionFeeRequirement) {
		this.supervisionFeeRequirementDao.makeTransient(
						supervisionFeeRequirement);
	}

	/** {@inheritDoc} */
	@Override
	public MonthlySupervisionFee updateMonthlySupervisionFee(
					final MonthlySupervisionFee monthlySupervisionFee, 
					final BigDecimal fee, final Date startDate, 
					final Date endDate, final String comments) 
					throws DateConflictException {		
		if (this.monthlySupervisionFeeDao.findByDateRangeExcluding(
						monthlySupervisionFee.getOffender(), startDate, 
						endDate, monthlySupervisionFee).size() > 0) {
			throw new DateConflictException(
				"Fee within this date range already exists.");
		}	
		monthlySupervisionFee.setFee(fee);
		monthlySupervisionFee.setComment(comments);
		DateRange dateRange = new DateRange(startDate, endDate);
		monthlySupervisionFee.setDateRange(dateRange);
		monthlySupervisionFee.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));		

		this.monthlySupervisionFeeDao.makePersistent(monthlySupervisionFee);
		
		return monthlySupervisionFee;
	}

	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirement> 
					findSupervisionFeeRequirementsByMonthlySupervisionFee(
								final MonthlySupervisionFee 
								monthlySupervisionFee) {
		return this.supervisionFeeRequirementDao
				.findByMonthlySupervisionFee(monthlySupervisionFee);
	}
	
	/** {@inheritDoc} */
	@Override
	public SupervisionFeeRequirement updateSupervisionFeeRequirement(
					final SupervisionFeeRequirement supervisionFeeRequirement,
					final BigDecimal fee, 
					final SupervisionFeeRequirementReason reason,
					final Date startDate, final Date endDate, 
					final String comments)
			throws DateConflictException {	
		if (this.supervisionFeeRequirementDao.findByDateRangeExcluding(
						startDate, endDate, 
						supervisionFeeRequirement.getMonthlySupervisionFee(), 
						supervisionFeeRequirement).size() > 0) {
			throw new DateConflictException(
						"Fee within this date range already exists.");			
		} 
		if (this.supervisionFeeRequirementDao
						.findWithinMonthlySupervisionFeeDateRange(
							startDate, endDate, 
							supervisionFeeRequirement
							.getMonthlySupervisionFee()).size() > 0) {
			throw new DateConflictException(
						"Fee requirement needs to be within this monthly "
						+ "supervision fee date range.");
		}
		supervisionFeeRequirement.setFee(fee);
		supervisionFeeRequirement.setReason(reason);
		DateRange dateRange = new DateRange(startDate, endDate);
		supervisionFeeRequirement.setDateRange(dateRange);
		supervisionFeeRequirement.setComment(comments);
		supervisionFeeRequirement.setUpdateSignature(new UpdateSignature(
						this.auditComponentRetriever.retrieveUserAccount(),
						this.auditComponentRetriever.retrieveDate()));	
		this.supervisionFeeRequirementDao.makePersistent(
						supervisionFeeRequirement);
			
		return supervisionFeeRequirement;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SupervisionFeeRequirementReason> 
					findAllSupervisionFeeRequirementReasons() {
		return this.supervisionFeeRequirementReasonDao.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<Court> findAllCourts() {
		return this.courtDao.findAll();
	}	
}
