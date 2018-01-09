package omis.sentence.service.delegate;

import java.util.Date;
import java.util.List;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.conviction.domain.Conviction;
import omis.conviction.domain.component.ConvictionCredit;
import omis.courtcase.domain.CourtCase;
import omis.instance.factory.InstanceFactory;
import omis.person.domain.Person;
import omis.sentence.dao.SentenceDao;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.SentenceExistsException;
import omis.term.domain.component.Term;

/**
 * Delegate for sentences.
 * 
 * @author Josh Divine
 * @author Stephen Abson
 * @version 0.0.1 (Oct 16, 2017)
 * @since OMIS 3.0
 */
public class SentenceDelegate {

	private final SentenceDao sentenceDao;
	
	private final InstanceFactory<Sentence> sentenceInstanceFactory;
	
	private final AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Instantiates delegate to manage convictions.
	 * 
	 * @param sentenceDao data access object for convictions
	 * @param sentenceInstanceFactory instance factory for convictions
	 * @param auditComponentRetriever audit component retriever
	 */
	public SentenceDelegate(
			final SentenceDao sentenceDao,
			final InstanceFactory<Sentence> sentenceInstanceFactory,
			final AuditComponentRetriever auditComponentRetriever) {
		this.sentenceDao = sentenceDao;
		this.sentenceInstanceFactory = sentenceInstanceFactory;
		this.auditComponentRetriever = auditComponentRetriever;
	}
	

	/**
	 * Creates a new sentence.
	 * 
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @return sentence
	 * @throws SentenceExistsException thrown when entity exists
	 */
	public Sentence sentence(final Conviction conviction, 
			final SentenceConnection connection,
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate,
			final Integer changeOrder) 
					throws SentenceExistsException {
		if (this.sentenceDao.find(conviction, effectiveDate) != null) {
			throw new SentenceExistsException("Sentence already exists.");
		}
		Sentence sentence = this.sentenceInstanceFactory.createInstance();
		sentence.setCreationSignature(this.getCreationSignature());
		sentence.setUpdateSignature(this.getUpdateSignature());
		this.populateSentence(sentence, conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, true, changeOrder);
		return this.sentenceDao.makePersistent(sentence);
	}
	
	/**
	 * Updates an existing sentence.
	 * 
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @return sentence
	 * @throws SentenceExistsException thrown when entity exists
	 */
	public Sentence updateSentence(final Sentence sentence, 
			final SentenceConnection connection,
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate) 
					throws SentenceExistsException {
		if (connection.getSentence() != null && sentence.equals(connection
				.getSentence().getConnection().getSentence())) {
			throw new IllegalArgumentException(
					"Circular sentence connections are not allowed.");
		}
		if (this.sentenceDao.findExcluding(sentence, sentence.getConviction(), 
				effectiveDate) != null) {
			throw new SentenceExistsException("Sentence already exists.");
		}
		sentence.setUpdateSignature(this.getUpdateSignature());
		this.populateSentence(sentence, sentence.getConviction(), connection, 
				prisonTerm, probationTerm, deferredTerm, category, 
				lengthClassification, legalDispositionCategory, effectiveDate, 
				pronouncementDate, credit, turnSelfInDate, true, 
				sentence.getChangeOrder());
		return this.sentenceDao.makePersistent(sentence);
	}

	/**
	 * Returns sentence for court case.
	 * 
	 * @param conviction court case
	 * @return sentence
	 */
	public List<Sentence> findByConviction(final Conviction conviction) {
		return this.sentenceDao.findByConviction(conviction);
	}
	
	public Sentence findActiveByConviction(final Conviction conviction) {
		return this.sentenceDao.findActiveByConviction(conviction);
	}
	
	/**
	 * Removes sentence.
	 * 
	 * @param sentence sentence
	 */
	public void remove(final Sentence sentence) {
		this.sentenceDao.makeTransient(sentence);
	}
	
	/**
	 * Amends the specified sentence.
	 * 
	 * @param sentence sentence to amend
	 * @param connection sentence connection
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param credit credit for time served
	 * @param effectivedDate effective date
	 * @param turnSelfInDate turn self in date
	 * @param PrisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @return amended sentence
	 */
	public Sentence amendSentence(final Sentence sentence, 
			final SentenceConnection connection, 
			final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory, 
			final Date pronouncementDate, final ConvictionCredit credit, 
			final Date effectiveDate, final Date turnSelfInDate, 
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final Integer changeOrder)
				throws SentenceExistsException {
		if (this.sentenceDao.find(sentence.getConviction(), effectiveDate) 
				!= null) {
			throw new SentenceExistsException("Sentence already exists.");
		}
		sentence.setActive(false);
		sentence.setConnection(null);
		sentence.setUpdateSignature(this.getUpdateSignature());
		this.sentenceDao.makePersistent(sentence);
		return this.sentence(sentence.getConviction(), connection, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, changeOrder);
	}
	
	/**
	 * Returns all active sentences except for the specified court case.
	 * 
	 * @param person person
	 * @param courtCase excluded court case
	 * @return active sentences
	 */
	public List<Sentence> findActiveSentencesForOtherCourtCases(
			final Person person, final CourtCase courtCase) {
		return this.sentenceDao.findActiveSentencesForOtherCourtCases(person, 
				courtCase);
	}
	
	/**
	 * Returns all active sentences except for the specified person.
	 * 
	 * @param person person
	 * @return active sentences
	 */
	public List<Sentence> findActiveSentences(final Person person) {
		return this.sentenceDao.findActiveSentences(person);
	}
	
	/**
	 * Removes all sentences associated with the specified conviction.
	 * 
	 * @param conviction conviction
	 */
	public void removeByConviction(final Conviction conviction) {
		this.sentenceDao.removeByConviction(conviction);
	}
	
	public Integer countSentencesByConviction(final Conviction conviction) {
		return this.sentenceDao.countSentencesByConviction(conviction);
	}
	
	/**
	 * Creates a new sentence with all parameters.
	 * 
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @param active active
	 * @param changeOrder change order
	 * @return sentence
	 * @throws DuplicateEntityFoundException thrown if the entity already exists
	 */
	public Sentence create(final Conviction conviction, 
			final SentenceConnection connection, final Term prisonTerm, 
			final Term probationTerm, final Term deferredTerm, 
			final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate,
			final Boolean active, final Integer changeOrder) 
					throws SentenceExistsException {
		if (this.sentenceDao.find(conviction, effectiveDate) != null) {
			throw new SentenceExistsException("Sentence already exists.");
		}
		final Sentence sentence = this.sentenceInstanceFactory.createInstance();
		this.populateSentence(sentence, conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, active, changeOrder);
		sentence.setCreationSignature(this.getCreationSignature());
		sentence.setUpdateSignature(this.getUpdateSignature());
		return this.sentenceDao.makePersistent(sentence);
	}
	
	/**
	 * Updates an existing sentence with all parameters.
	 * 
	 * @param sentence sentence
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param effectiveDate effective date
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param turnSelfInDate turn self in date
	 * @param active active
	 * @param changeOrder change order
	 * @return sentence
	 * @throws DuplicateEntityFoundException thrown if the entity already exists
	 */
	public Sentence update(final Sentence sentence, final Conviction conviction, 
			final SentenceConnection connection, final Term prisonTerm, 
			final Term probationTerm, final Term deferredTerm, 
			final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate,
			final Boolean active, final Integer changeOrder) 
			throws SentenceExistsException {
		if (this.sentenceDao.findExcluding(sentence, conviction, 
				effectiveDate) != null) {
			throw new SentenceExistsException("Sentence already exists.");
		}
		this.populateSentence(sentence, conviction, connection, prisonTerm, 
				probationTerm, deferredTerm, category, lengthClassification, 
				legalDispositionCategory, effectiveDate, pronouncementDate, 
				credit, turnSelfInDate, active, changeOrder);
		sentence.setUpdateSignature(this.getUpdateSignature());
		return this.sentenceDao.makePersistent(sentence);
	}
	
	/**
	 * Counts connected sentences.
	 * 
	 * @param sentence sentence to which connections are to be counted
	 * @return count of connected sentences
	 */
	public long countConnected(final Sentence sentence) {
		return this.sentenceDao.countConnected(sentence);
	}
	
	/* Helper Methods */
	
	// Populates sentence
	private Sentence populateSentence(final Sentence sentence,
			final Conviction conviction, final SentenceConnection connection,
			final Term prisonTerm, final Term probationTerm, 
			final Term deferredTerm, final SentenceCategory category, 
			final SentenceLengthClassification lengthClassification, 
			final LegalDispositionCategory legalDispositionCategory,
			final Date effectiveDate, final Date pronouncementDate, 
			final ConvictionCredit credit, final Date turnSelfInDate,
			final Boolean active, final Integer changeOrder) {
		sentence.setActive(active);
		sentence.setCategory(category);
		sentence.setChangeOrder(changeOrder);
		sentence.setConviction(conviction);
		sentence.setConnection(connection);
		sentence.setCredit(credit);
		sentence.setDeferredTerm(deferredTerm);
		sentence.setEffectiveDate(effectiveDate);
		sentence.setLegalDispositionCategory(legalDispositionCategory);
		sentence.setLengthClassification(lengthClassification);
		sentence.setPrisonTerm(prisonTerm);
		sentence.setProbationTerm(probationTerm);
		sentence.setPronouncementDate(pronouncementDate);
		sentence.setTurnSelfInDate(turnSelfInDate);
		return sentence;
	}
	
	// Returns the current creation signature
	private CreationSignature getCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	// Returns the current update signature
	private UpdateSignature getUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}
