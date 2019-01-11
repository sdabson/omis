package omis.stg.service.impl;

import java.util.Date;
import java.util.List;

import omis.audit.domain.VerificationMethod;
import omis.audit.domain.VerificationSignature;
import omis.audit.service.delegate.VerificationMethodDelegate;
import omis.datatype.DateRange;
import omis.offender.domain.Offender;
import omis.region.domain.City;
import omis.region.domain.State;
import omis.region.service.delegate.CityDelegate;
import omis.region.service.delegate.StateDelegate;
import omis.stg.domain.SecurityThreatGroup;
import omis.stg.domain.SecurityThreatGroupActivityLevel;
import omis.stg.domain.SecurityThreatGroupAffiliation;
import omis.stg.domain.SecurityThreatGroupAffiliationNote;
import omis.stg.domain.SecurityThreatGroupChapter;
import omis.stg.domain.SecurityThreatGroupRank;
import omis.stg.exception.SecurityThreatGroupAffiliationExistsException;
import omis.stg.exception.SecurityThreatGroupAffiliationNoteExistsException;
import omis.stg.exception.SecurityThreatGroupChapterExistsException;
import omis.stg.exception.SecurityThreatGroupRankExistsException;
import omis.stg.service.SecurityThreatGroupAffiliationService;
import omis.stg.service.delegate.SecurityThreatGroupActivityLevelDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationDelegate;
import omis.stg.service.delegate.SecurityThreatGroupAffiliationNoteDelegate;
import omis.stg.service.delegate.SecurityThreatGroupChapterDelegate;
import omis.stg.service.delegate.SecurityThreatGroupDelegate;
import omis.stg.service.delegate.SecurityThreatGroupRankDelegate;

/**
 * Implementation of service for security threat group affiliations.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Jan 17, 2014)
 * @since OMIS 3.0
 */
public class SecurityThreatGroupAffiliationServiceImpl
		implements SecurityThreatGroupAffiliationService {
	
	private SecurityThreatGroupAffiliationDelegate 
		securityThreatGroupAffiliationDelegate;
	
	private final SecurityThreatGroupDelegate securityThreatGroupDelegate;
	
	private final SecurityThreatGroupActivityLevelDelegate
		securityThreatGroupActivityLevelDelegate;
	
	private final SecurityThreatGroupChapterDelegate 
		securityThreatGroupChapterDelegate;
	
	private final SecurityThreatGroupRankDelegate 
		securityThreatGroupRankDelegate;
	
	private final StateDelegate stateDelegate;

	private final CityDelegate cityDelegate;
	
	private final VerificationMethodDelegate verificationMethodDelegate;
	
	private final SecurityThreatGroupAffiliationNoteDelegate 
		securityThreatGroupAffiliationNoteDelegate;

	/**
	 * Instantiates an implementation of service for security threat group
	 * affiliations.
	 * 
	 * @param securityThreatGroupAffiliationDao data access object for
	 * security threat group affiliations
	 * @param securityThreatGroupDelegate data access object for security threat
	 * groups
	 * @param securityThreatGroupActivityLevelDelegate data access object for
	 * security threat group activity levels 
	 * @param securityThreatGroupChapterDelegate data access object for security
	 * threat group chapters
	 * @param securityThreatGroupRankDelegate data access object for security
	 * threat group ranks
	 * @param stateDelegate data access object for States
	 * @param cityDelegate data access object for cities
	 * @param verificationMethodDelegate data access object for verification 
	 * method
	 * @param auditComponentRetriever audit component retriever
	 */
	public SecurityThreatGroupAffiliationServiceImpl(
			final SecurityThreatGroupAffiliationNoteDelegate 
			securityThreatGroupAffiliationNoteDelegate,
			final SecurityThreatGroupDelegate securityThreatGroupDelegate,
			final SecurityThreatGroupActivityLevelDelegate
				securityThreatGroupActivityLevelDelegate,
			final SecurityThreatGroupChapterDelegate 
				securityThreatGroupChapterDelegate,
			final SecurityThreatGroupRankDelegate 
				securityThreatGroupRankDelegate,
			final StateDelegate stateDelegate, final CityDelegate cityDelegate,
			final VerificationMethodDelegate verificationMethodDelegate,
			final SecurityThreatGroupAffiliationDelegate 
				securityThreatGroupAffiliationDelegate) {
		this.securityThreatGroupAffiliationDelegate 
			= securityThreatGroupAffiliationDelegate;
		this.securityThreatGroupDelegate = securityThreatGroupDelegate;
		this.securityThreatGroupActivityLevelDelegate
			= securityThreatGroupActivityLevelDelegate;
		this.securityThreatGroupChapterDelegate 
			= securityThreatGroupChapterDelegate;
		this.securityThreatGroupRankDelegate = securityThreatGroupRankDelegate;
		this.stateDelegate = stateDelegate;
		this.cityDelegate = cityDelegate;
		this.verificationMethodDelegate = verificationMethodDelegate;
		this.securityThreatGroupAffiliationNoteDelegate 
			= securityThreatGroupAffiliationNoteDelegate;
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupAffiliation> findByOffender(
			final Offender offender) {
		return this.securityThreatGroupAffiliationDelegate
				.findByOffender(offender);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliation create(
			final Offender offender, final DateRange dateRange,
			final SecurityThreatGroup group,
			final SecurityThreatGroupActivityLevel activityLevel,
			final SecurityThreatGroupChapter chapter,
			final SecurityThreatGroupRank rank,
			final State state, final City city, final String moniker,
			final String comment,
			final VerificationSignature verificationSignature)
					throws SecurityThreatGroupAffiliationExistsException {
		return this.securityThreatGroupAffiliationDelegate.create(offender, 
				dateRange, group, activityLevel, chapter, rank, state, city, 
				moniker, comment, verificationSignature);
	}

	/** {@inheritDoc} 
	 * @throws SecurityThreatGroupAffiliationExistsException */
	@Override
	public SecurityThreatGroupAffiliation update(
			final SecurityThreatGroupAffiliation affiliation,
			final DateRange dateRange, final SecurityThreatGroup group,
			final SecurityThreatGroupActivityLevel activityLevel,
			final SecurityThreatGroupChapter chapter,
			final SecurityThreatGroupRank rank,
			final State state, final City city, final String moniker,
			final String comment,
			final VerificationSignature verificationSignature) 
					throws SecurityThreatGroupAffiliationExistsException {
		return this.securityThreatGroupAffiliationDelegate.update(affiliation, 
				dateRange, group, activityLevel, chapter, rank, state, city, 
				moniker, comment, verificationSignature);
	}

	/** {@inheritDoc} */
	@Override
	public void remove(final SecurityThreatGroupAffiliation affiliation) {
		this.securityThreatGroupAffiliationDelegate.remove(affiliation);
	}
	
	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupAffiliationNote createNote(
			final SecurityThreatGroupAffiliation affiliation, final Date date, 
			final String note)
			throws SecurityThreatGroupAffiliationNoteExistsException {
			return (SecurityThreatGroupAffiliationNote) this
					.securityThreatGroupAffiliationNoteDelegate
					.addNote(affiliation, date, note);
	}

	/** {@inheritDoc} 
	 * @throws SecurityThreatGroupAffiliationNoteExistsException */
	@Override
	public SecurityThreatGroupAffiliationNote updateNote(
			SecurityThreatGroupAffiliationNote affiliationNote, Date date, 
			String note) throws SecurityThreatGroupAffiliationNoteExistsException {
		return this.securityThreatGroupAffiliationNoteDelegate
				.update(affiliationNote, date, note);
	}

	/** {@inheritDoc} */
	@Override
	public void removeNote(final SecurityThreatGroupAffiliationNote 
			affiliationNote) {
		this.securityThreatGroupAffiliationNoteDelegate.remove(affiliationNote);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroup> findGroups() {
		return this.securityThreatGroupDelegate.findGroups();
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupActivityLevel> findActivityLevels() {
		return this.securityThreatGroupActivityLevelDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupChapter> findChapters() {
		return this.securityThreatGroupChapterDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupChapter> findChaptersByGroup(
			SecurityThreatGroup group) {
		return this.securityThreatGroupChapterDelegate
				.findChaptersByGroup(group);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupRank> findRanks() {
		return this.securityThreatGroupRankDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<State> findStates() {
		return this.stateDelegate.findAll();
	}

	/** {@inheritDoc} */
	@Override
	public List<City> findCitiesByState(final State state) {
		return this.cityDelegate.findByState(state);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<VerificationMethod> findVerificationMethods() {
		return this.verificationMethodDelegate.findAll();
	}
	
	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupAffiliationNote> 
		findAffiliationNotesByAffiliation(
				SecurityThreatGroupAffiliation affiliation) {
		return this.securityThreatGroupAffiliationNoteDelegate
				.findAffiliationNotesByAffiliation(affiliation);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupChapter createChapter(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws SecurityThreatGroupChapterExistsException {
		return this.securityThreatGroupChapterDelegate
				.create(name, securityThreatGroup);
	}

	/** {@inheritDoc} */
	@Override
	public SecurityThreatGroupRank createRank(String name, 
			SecurityThreatGroup securityThreatGroup) 
					throws SecurityThreatGroupRankExistsException {
		return this.securityThreatGroupRankDelegate.create(name, 
				securityThreatGroup);
	}

	/** {@inheritDoc} */
	@Override
	public List<SecurityThreatGroupRank> findRanksByGroup(SecurityThreatGroup group) {
		return this.securityThreatGroupRankDelegate.findRanksByGroup(group);
	}
}