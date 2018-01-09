package omis.substanceuse.service;

import java.util.Date;
import java.util.List;

import omis.datatype.DateRange;
import omis.exception.DateRangeException;
import omis.exception.DuplicateEntityFoundException;
import omis.offender.domain.Offender;
import omis.substance.domain.Substance;
import omis.substanceuse.domain.SubstanceUse;
import omis.substanceuse.domain.UseAffirmation;
import omis.substanceuse.domain.UseAffirmationSource;
import omis.substanceuse.domain.UseAllotment;
import omis.substanceuse.domain.UseFrequency;
import omis.substanceuse.domain.UseMeasurement;
import omis.substanceuse.domain.UseTerm;
import omis.substanceuse.domain.UseTermSource;

/**
 * Substance use service.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public interface SubstanceUseService {

	/**
	 * Creates a substance use for the specified offender.
	 * 
	 * @param offender offender
	 * @param substance substance
	 * @return substance use
	 * @throws DuplicateEntityFoundException thrown when a duplicate substance
	 * use is found
	 */
	SubstanceUse create(Offender offender, Substance substance)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified substance use.
	 * 
	 * @param use substance use
	 */
	void remove(SubstanceUse use);
	
	/**
	 * Creates a use term for the specified substance use.
	 * 
	 * @param dateRange date range
	 * @param frequency use frequency
	 * @param allotment use allotment
	 * @param source use term source
	 * @param use substance use
	 * @return use term
	 * @throws DuplicateEntityFoundException thrown when a duplicate use term
	 * is found
	 * @throws DateRangeException thrown when the date range of use terms
	 * for the same substance use over lap
	 */
	UseTerm createUseTerm(DateRange dateRange, UseFrequency frequency, 
			UseAllotment allotment, UseTermSource source, SubstanceUse use) 
		throws DuplicateEntityFoundException,  DateRangeException;
	
	/**
	 * Updates the specified use term.
	 * 
	 * @param term use term
	 * @param dateRange date range
	 * @param allotment use allotment
	 * @param frequency use frequency
	 * @param source use term source
	 * @return use term
	 * @throws DuplicateEntityFoundException thrown when a duplicate use term
	 * is found
	 * @throws DateRangeException thrown when the date range of use terms for
	 * the same substance use over lap
	 */
	UseTerm updateUseTerm(UseTerm term, DateRange dateRange, 
			UseAllotment allotment, UseFrequency frequency, 
			UseTermSource source) 
		throws DuplicateEntityFoundException, DateRangeException;
	
	/**
	 * Removes the specified use term.
	 * 
	 * @param term use term
	 */
	void removeUseTerm(UseTerm term);
	
	/**
	 * Creates a use affirmation for the specified substance use.
	 * 
	 * @param date date
	 * @param allotment use allotment
	 * @param source use affirmation source
	 * @param use substance use
	 * @return use affirmation
	 * @throws DuplicateEntityFoundException thrown when a duplicate use
	 * affirmation is found
	 */
	UseAffirmation createUseAffirmation(Date date, UseAllotment allotment,
			UseAffirmationSource source, SubstanceUse use)
			throws DuplicateEntityFoundException;
	
	/**
	 * Updates the specified use affirmation.
	 * 
	 * @param affirmation use affirmation
	 * @param date date
	 * @param allotment use allotment
	 * @param source use affirmation source
	 * @return use affirmation
	 * @throws DuplicateEntityFoundException thrown when a duplicate use
	 * affirmation is found
	 */
	UseAffirmation updateUseAffirmation(UseAffirmation affirmation, Date date,
			UseAllotment allotment, UseAffirmationSource source)
		throws DuplicateEntityFoundException;
	
	/**
	 * Removes the specified use affirmation.
	 * 
	 * @param affirmation use affirmation
	 */
	void removeUseAffirmation(UseAffirmation affirmation);
	
	/**
	 * Returns use affirmations for the specified substance use.
	 * 
	 * @param substanceUse substance use
	 * @return list of use affirmations
	 */
	List<UseAffirmation> findUseAffirmationsBySubstanceUse(
			SubstanceUse substanceUse);
	
	/**
	 * Returns use terms for the specified substance use.
	 * 
	 * @param substanceUse substance use
	 * @return list of use terms
	 */
	List<UseTerm> findUseTermBySubstanceUse(SubstanceUse substanceUse);
	
	/**
	 * Returns substance uses for the specified offender.
	 * 
	 * @param offender offender
	 * @return list of substance uses.
	 */
	List<SubstanceUse> findSubstanceUsesByOffender(Offender offender);
	
	/**
	 * Returns use measurements.
	 * 
	 * @return list of use measurements
	 */
	List<UseMeasurement> findUseMeasurements();
	
	/**
	 * Returns use frequencies.
	 * 
	 * @return list of use frequencies
	 */
	List<UseFrequency> findUseFrequencies();
}