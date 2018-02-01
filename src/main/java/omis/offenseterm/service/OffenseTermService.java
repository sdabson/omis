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
package omis.offenseterm.service;

import java.util.Date;
import java.util.List;

import omis.conviction.domain.Conviction;
import omis.conviction.domain.OffenseSeverity;
import omis.conviction.domain.component.ConvictionCredit;
import omis.conviction.domain.component.ConvictionFlags;
import omis.conviction.exception.ConvictionExistsException;
import omis.court.domain.Court;
import omis.courtcase.domain.CourtCase;
import omis.courtcase.domain.JurisdictionAuthority;
import omis.courtcase.domain.OffenderDangerDesignator;
import omis.courtcase.domain.component.CourtCaseFlags;
import omis.courtcase.domain.component.CourtCasePersonnel;
import omis.courtcase.exception.CourtCaseExistsException;
import omis.docket.domain.Docket;
import omis.docket.exception.DocketExistsException;
import omis.exception.DuplicateEntityFoundException;
import omis.offense.domain.Offense;
import omis.person.domain.Person;
import omis.region.domain.State;
import omis.sentence.domain.LegalDispositionCategory;
import omis.sentence.domain.Sentence;
import omis.sentence.domain.SentenceCategory;
import omis.sentence.domain.SentenceLengthClassification;
import omis.sentence.domain.component.SentenceConnection;
import omis.sentence.exception.ConnectedSentenceExistsException;
import omis.sentence.exception.SentenceExistsException;
import omis.term.domain.component.Term;

/**
 * Service to manage court cases, convictions and sentences.
 *
 * <p>Offense terms are a traditional treatment of court case, convictions
 * and sentences. Charges/amendments to original charges are not managed.
 *
 * @author Stephen Abson
 * @author Josh Divine
 * @version 0.0.3 (Sept 15, 2017)
 * @since OMIS 3.0
 */
public interface OffenseTermService {

	/**
	 * Creates a court case.
	 * 
	 * @param docket docket
	 * @param interStateNumber inter State number
	 * @param interState inter State
	 * @param pronouncementDate pronouncement date
	 * @param sentenceReviewDate sentence review date
	 * @param jurisdictionAuthority jursidiction authority
	 * @param dangerDesignator danger designator
	 * @param personnel personnel
	 * @param comments comments
	 * @return created court case
	 * @throws CourtCaseExistsException if court case exists
	 */
	CourtCase create(Docket docket, String interStateNumber, State interState,
			Date pronouncementDate, Date sentenceReviewDate,
			JurisdictionAuthority jurisdictionAuthority,
			OffenderDangerDesignator dangerDesignator,
			CourtCasePersonnel personnel, CourtCaseFlags flags, String comments)
					throws CourtCaseExistsException;
	
	/**
	 * Creates docket.
	 * 
	 * @param person person
	 * @param court court
	 * @param value value
	 * @return created docket
	 * @throws DocketExistsException if docket exists
	 */
	Docket createDocket(Person person, Court court, String value)
			throws DocketExistsException;
	
	/**
	 * Updates a court case.
	 * 
	 * @param courtCase court case to update
	 * @param interStateNumber inter State number
	 * @param interState inter State
	 * @param pronouncementDate pronouncement date
	 * @param sentenceReviewDate sentence review date
	 * @param jurisdictionAuthority jurisdiction authority
	 * @param dangerDesignator danger designator
	 * @param personnel personnel
	 * @param comments comments
	 * @return updated court case
	 */
	CourtCase update(CourtCase courtCase, String interStateNumber,
			State interState, Date pronouncementDate, Date sentenceReviewDate,
			JurisdictionAuthority jurisdictionAuthority,
			OffenderDangerDesignator dangerDesignator,
			CourtCasePersonnel personnel, CourtCaseFlags flags, 
			String comments);
	
	/**
	 * Creates conviction.
	 * 
	 * @param courtCase court case
	 * @param offense offense
	 * @param date date
	 * @param counts counts
	 * @param severity severity
	 * @param flags conviction flags
	 * @return created conviction
	 * @throws ConvictionExistsException if conviction exists
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	Conviction createConviction(CourtCase courtCase, Offense offense,
			Date date, Integer counts, OffenseSeverity severity, 
			ConvictionFlags flags)
					throws ConvictionExistsException;
	
	/**
	 * Updates conviction.
	 * 
	 * @param conviction conviction to update
	 * @param offense offense
	 * @param date date
	 * @param counts counts
	 * @param severity severity
	 * @param flags conviction flags
	 * @return updated conviction
	 * @throws DuplicateEntityFoundException if duplicate entity exists
	 */
	Conviction updateConviction(Conviction conviction, Offense offense,
			Date date, Integer counts, OffenseSeverity severity, 
			ConvictionFlags flags)
					throws ConvictionExistsException;
	
	/**
	 * Removes conviction.
	 * 
	 * @param conviction conviction
	 */
	void removeConviction(Conviction conviction) 
			throws ConnectedSentenceExistsException;

	/**
	 * Creates a sentence.
	 * 
	 * @param conviction conviction
	 * @param connection sentence connection
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param effectiveDate effective date
	 * @param turnSelfInDate turn self in date
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @return consecutive sentence
	 * @throws DuplicateEntityFoundException if duplicate entity is found
	 */
	Sentence sentence(Conviction conviction, 
			SentenceConnection connection, SentenceCategory category, 
			SentenceLengthClassification lengthClassification, 
			LegalDispositionCategory legalDispositionCategory,
			Date pronouncementDate, ConvictionCredit credit, Date effectiveDate, 
			Date turnSelfInDate, Term prisonTerm, Term probationTerm, 
			Term deferredTerm) 
					throws SentenceExistsException;

	/**
	 * Updates an existing sentence. Circular sentence connections are not 
	 * allowed and will cause an {@code IllegalArgumentException} to be thrown.
	 * 
	 * @param conviction conviction
	 * @param category sentence category
	 * @param lengthClassification sentence length classification
	 * @param legalDispositionCategory legal disposition category
	 * @param pronouncementDate pronouncement date
	 * @param credit conviction credit
	 * @param effectiveDate effective date
	 * @param turnSelfInDate turn self in date
	 * @param prisonTerm prison term
	 * @param probationTerm probation term
	 * @param deferredTerm deferred term
	 * @return concurrent sentence
	 * @throws DuplicateEntityFoundException if duplicate entity is found
	 */
	Sentence updateSentence(Sentence sentence, SentenceConnection connection, 
			SentenceCategory category, 
			SentenceLengthClassification lengthClassification, 
			LegalDispositionCategory legalDispositionCategory,
			Date pronouncementDate, ConvictionCredit credit, Date effectiveDate, 
			Date turnSelfInDate, Term prisonTerm, Term probationTerm, 
			Term deferredTerm) 
					throws SentenceExistsException;
	
	/**
	 * Removes sentence.
	 * 
	 * @param sentence sentence to remove
	 * @throws ConnectedSentenceExistsException if connected sentences exist 
	 */
	void removeSentence(Sentence sentence) 
			throws ConnectedSentenceExistsException;
	
	/**
	 * Returns courts.
	 * 
	 * @return courts
	 */
	List<Court> findCourts();

	/**
	 * Returns dockets without court case.
	 * 
	 * @return dockets without court case
	 */
	List<Docket> findDocketsWithoutCourtCase(Person person);
	
	/**
	 * Returns States.
	 * 
	 * @return States
	 */
	List<State> findHomeStates();

	/**
	 * Returns offenses.
	 * 
	 * @param query query
	 * @return offenses
	 */
	List<Offense> findOffenses(String query);

	/**
	 * Returns sentence length categories.
	 * 
	 * @return sentence length categories
	 */
	List<SentenceCategory> findSentenceCategories();
	
	/**
	 * Returns judges for the specified date.
	 * 
	 * @param query judges name
	 * @param effectiveDate effective date
	 * @return judges
	 */
	List<Person> findJudges(String query, Date effectiveDate);
	
	/**
	 * Returns convictions for the specified court case.
	 * 
	 * @param courtCase court case
	 * @return convictions
	 */
	List<Conviction> findConvictions(CourtCase courtCase);
	
	/**
	 * Returns the sentences for the specified conviction.
	 * 
	 * @param conviction conviction
	 * @return sentences
	 */
	List<Sentence> findSentences(Conviction conviction);
	
	/**
	 * Returns the active sentence for the specified conviction.
	 * 
	 * @param conviction conviction
	 * @return active sentence
	 */
	Sentence findActiveSentence(Conviction conviction);
	
	/**
	 * Calculates the sentence effective date for the specified pronouncement
	 * date and credit for time served.
	 * 
	 * @param sentencePronouncementDate sentence pronouncement date
	 * @param credit credit for time served
	 * @return sentence effective date
	 */
	Date calculateSentenceEffectiveDate(Date sentencePronouncementDate,
			ConvictionCredit credit);
	
	/**
	 * Amends the specified sentence.
	 * 
	 * <p>The original sentence will become inactive and will lose its
	 * connection to other, active sentences. A new active sentence will be
	 * returned as the amendment. Other sentences referencing the original
	 * sentence consecutively will be updated to reference the amendment.
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
	Sentence amendSentence(Sentence sentence, SentenceConnection connection, 
			SentenceCategory category, 
			SentenceLengthClassification lengthClassification, 
			LegalDispositionCategory legalDispositionCategory, 
			Date pronouncementDate, ConvictionCredit credit, 
			Date effectiveDate, Date turnSelfInDate, Term prisonTerm, 
			Term probationTerm, Term deferredTerm)
				throws SentenceExistsException;
	
	/**
	 * Returns all active sentences except for the specified court case.
	 * 
	 * @param person person
	 * @param courtCase excluded court case
	 * @return active sentences
	 */
	List<Sentence> findActiveSentencesForOtherCourtCases(Person person, 
			CourtCase courtCase);
	
	/**
	 * Returns all legal disposition categories.
	 * 
	 * @return legal disposition categories
	 */
	List<LegalDispositionCategory> findLegalDispositionCategories();
	
	/**
	 * Returns all active sentences for a person.
	 * 
	 * @param person person
	 * @return active sentences
	 */
	List<Sentence> findActiveSentences(Person person);

	/**
	 * Calculates total days from term.
	 * 
	 * @param term term
	 * @return total days from term
	 */
	Integer calculateTotalDays(Term term);
	
	/**
	 * Removes court case.
	 * 
	 * @param courtCase court case
	 * @throws ConnectedSentenceExistsException if sentences exist that are
	 * connected to sentences on court case
	 */
	void remove(CourtCase courtCase) throws ConnectedSentenceExistsException;
	
	/**
	 * Dismisses the specified court case.
	 * 
	 * @param courtCase court case
	 * @return dismissed court case
	 */
	CourtCase dismiss(CourtCase courtCase);
}