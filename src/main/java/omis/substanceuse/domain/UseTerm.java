package omis.substanceuse.domain;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.datatype.DateRange;

/**
 * User term.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 16, 2016)
 * @since OMIS 3.0
 */
public interface UseTerm extends Creatable, Updatable {

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	Long getId();
	
	/**
	 * Sets the id.
	 * 
	 * @param id id
	 */
	void setId(Long id);
	
	/**
	 * Returns the date range.
	 * 
	 * @return date range
	 */
	DateRange getDateRange();
	
	/**
	 * Sets the date range.
	 * 
	 * @param dateRange date range
	 */
	void setDateRange(DateRange dateRange);
	
	/**
	 * Returns the frequency.
	 * 
	 * @return frequency
	 */
	UseFrequency getFrequency();
	
	/**
	 * Sets the frequency.
	 * 
	 * @param frequency frequency
	 */
	void setFrequency(UseFrequency frequency);
	
	/**
	 * Returns the use allotment.
	 * 
	 * @return use allotment
	 */
	UseAllotment getAllotment();
	
	/**
	 * Sets the use allotment.
	 * 
	 * @param allotment use allotment
	 */
	void setAllotment(UseAllotment allotment);
	
	/**
	 * Returns the use term source.
	 * 
	 * @return use term source
	 */
	UseTermSource getSource();
	
	/**
	 * Set the use term source.
	 * 
	 * @param source use term source
	 */
	void setSource(UseTermSource source);
	
	/**
	 * Returns the use.
	 * 
	 * @return use
	 */
	SubstanceUse getUse();
	
	/**
	 * Sets the use.
	 * 
	 * @param use use
	 */
	void setUse(SubstanceUse use);
	
	/**
	 * Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} 
	 */
	@Override
	boolean equals(Object obj);
	
	/**
	 * Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null}
	 */
	@Override
	int hashCode();	
}