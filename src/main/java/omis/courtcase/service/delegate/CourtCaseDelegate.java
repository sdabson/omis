package omis.courtcase.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.courtcase.dao.CourtCaseDao;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.docket.domain.Docket;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.region.domain.State;

/** 
 * Court case delegate for services.
 * 
 * @author Ryan Johns
 * @author Josh Divine
 * @version 0.1.2 (Aug 24, 2017)
 * @since OMIS 3.0 
 */
public class CourtCaseDelegate {
	private final CourtCaseDao courtCaseDao;
	
	/* Instance factories. */
	private final InstanceFactory<CourtCase> courtCaseInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;

	
	/** Constructor.
	 * @param courtCaseDao court case dao
	 * @param courtCaseInstanceFactory court case instance factory
	 * @param auditComponentRetriever audit component retriever
	 */
	public CourtCaseDelegate(final CourtCaseDao courtCaseDao,
			final InstanceFactory<CourtCase> courtCaseInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.courtCaseDao = courtCaseDao;
		this.courtCaseInstanceFactory = courtCaseInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
		
	}
		
	/** Finds court cases by defendant.
	 * @param defendant defendant
	 * @return list of court cases 
	 */
	public List<CourtCase> findByDefendant(final Person defendant) {
		return this.courtCaseDao.findByDefendant(defendant);
	}
	
	/**
	 * Returns the court case for the specified docket.
	 * 
	 * @param docket docket
	 * @return court case
	 */
	public CourtCase findByDocket(final Docket docket) {
		return this.courtCaseDao.findByDocket(docket);
	}
	
	/** 
	 * Creates a new court case.
	 * 
	 * @param docket docket
	 * @param interStateNumber inter state number
	 * @param interState inter state
	 * @param pronouncementDate pronouncement date
	 * @param jurisdictionAuthority jurisdiction authority
	 * @param sentenceReviewDate sentence review date
	 * @param dangerDesignator danger designator
	 * @param courtCasePersonnel court case personnel
	 * @param courtCaseFlags court case flags
	 * @param comments comments
	 * @return court case 
	 * @throws CourtCaseExistsException when court case exists 
	 */
	public CourtCase create(final Docket docket, 
			final String interStateNumber, final State interState,
			final Date pronouncementDate, 
			final JurisdictionAuthority jurisdictionAuthority, 
			final Date sentenceReviewDate,
			final OffenderDangerDesignator dangerDesignator,
			final CourtCasePersonnel courtCasePersonnel,
			final CourtCaseFlags courtCaseFlags, final String comments) 
					throws CourtCaseExistsException {
		if (this.courtCaseDao.find(docket.getPerson(), docket.getValue()) 
				!= null) {
			throw new CourtCaseExistsException("Duplicate court case found");
	}
		CourtCase courtCase = this.courtCaseInstanceFactory.createInstance();
		
		this.populateCourtCase(courtCase, docket, interStateNumber, interState, 
				pronouncementDate, jurisdictionAuthority, sentenceReviewDate, 
				dangerDesignator, courtCasePersonnel, courtCaseFlags, 
				comments);
		courtCase.setCreationSignature(this.newCreationSignature());
		courtCase.setUpdateSignature(this.newUpdateSignature());
		return this.courtCaseDao.makePersistent(courtCase);
	}
	
	/** 
	 * Updates court case.
	 * 
	 * @param courtCase court case
	 * @param docket docket
	 * @param interStateNumber inter state number
	 * @param interState inter state
	 * @param pronouncementDate pronouncement date
	 * @param jurisdictionAuthority jurisdiction authority
	 * @param sentenceReviewDate sentence review date
	 * @param dangerDesignator danger designator
	 * @param courtCasePersonnel court case personnel
	 * @param courtCaseFlags court case flags
	 * @param comments comments
	 * @return court case 
	 */
	public CourtCase update(final CourtCase courtCase,
			final String interStateNumber, final State interState,
			final Date pronouncementDate, 
			final JurisdictionAuthority jurisdictionAuthority, 
			final Date sentenceReviewDate,
			final OffenderDangerDesignator dangerDesignator,
			final CourtCasePersonnel courtCasePersonnel,
			final CourtCaseFlags courtCaseFlags, final String comments) {
		this.populateCourtCase(courtCase, courtCase.getDocket(), 
				interStateNumber, interState, pronouncementDate, 
				jurisdictionAuthority, sentenceReviewDate, dangerDesignator, 
				courtCasePersonnel, courtCaseFlags, comments);
		courtCase.setUpdateSignature(this.newUpdateSignature());
		return this.courtCaseDao.makePersistent(courtCase);
	}
	
	/** 
	 * Finds all court cases.
	 *  
	 * @return all court cases 
	 */
	public List<CourtCase> findAll() {
		return this.courtCaseDao.findAll();
	}
	
	/** 
	 * Removes court case.
	 * 
	 * @param courtCase court case 
	 */
	public void remove(final CourtCase courtCase) {
		this.courtCaseDao.makeTransient(courtCase);
	}
	
	/**
	 * Dismisses the court case.
	 * 
	 * @param courtCase court case
	 * @return court case
	 */
	public CourtCase dismiss(CourtCase courtCase) {
		courtCase.getFlags().setDismissed(true);
		return this.courtCaseDao.makePersistent(courtCase);
	}
	
	/* Helper methods. */
	
	/**
	 * Populates the specified court case with all the values that can be
	 * used to both create a new court case and update an existing one.
	 * 
	 * @param courtCase court case
	 * @param docket docket
	 * @param interStateNumber inter state number
	 * @param interState inter state
	 * @param pronouncementDate pronouncement date
	 * @param jurisdictionAuthority jurisdiction authority
	 * @param sentenceReviewDate sentence review date
	 * @param dangerDesignator danger designator
	 * @param courtCasePersonnel court case personnel
	 * @param courtCaseFlags court case flags
	 * @param comments comments
	 * @return populated court case
	 */
	private CourtCase populateCourtCase(final CourtCase courtCase, 
			final Docket docket, final String interStateNumber, 
			final State interState, final Date pronouncementDate, 
			final JurisdictionAuthority jurisdictionAuthority, 
			final Date sentenceReviewDate,
			final OffenderDangerDesignator dangerDesignator, 
			final CourtCasePersonnel courtCasePersonnel,
			final CourtCaseFlags courtCaseFlags, final String comments) {
		courtCase.setDocket(docket);
		courtCase.setInterStateNumber(interStateNumber);
		courtCase.setInterState(interState);
		courtCase.setPronouncementDate(pronouncementDate);
		courtCase.setJurisdictionAuthority(jurisdictionAuthority);
		courtCase.setSentenceReviewDate(sentenceReviewDate);
		courtCase.setDangerDesignator(dangerDesignator);
		courtCase.setPersonnel(courtCasePersonnel);
		courtCase.setFlags(courtCaseFlags);
		courtCase.setComments(comments);
		return courtCase;
	}
	
	/*
	 * Returns a new update signature with current date and user.
	 * 
	 * @return new update signature
	 */
	private UpdateSignature newUpdateSignature() {
		return new UpdateSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}
	
	/*
	 * Returns a new creation signature with the current date and user.
	 * 
	 * @return new creation signature
	 */
	private CreationSignature newCreationSignature() {
		return new CreationSignature(this.auditComponentRetriever
				.retrieveUserAccount(), this.auditComponentRetriever
				.retrieveDate());
	}

	
}
