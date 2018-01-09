package omis.substanceuse.report;

import java.io.Serializable;

/**
 * Substance use summary.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 28, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Long id;
	private final String substanceName;
	private final Long termCount;
	private final Long affirmationCount;
	
	/**
	 * Instantiates an instance of substance use summary.
	 * 
	 * @param id id
	 * @param substanceName substance name
	 * @param termCount term count
	 * @param affirmationCount affirmation count
	 */
	public SubstanceUseSummary(final Long id, final String substanceName,
			final Long termCount, final Long affirmationCount) {
		this.id = id;
		this.substanceName = substanceName;
		this.termCount = termCount;
		this.affirmationCount = affirmationCount;
	}

	/**
	 * Returns the id.
	 * 
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Returns the substance name.
	 * 
	 * @return  substance name
	 */
	public String getSubstanceName() {
		return this.substanceName;
	}

	/**
	 * Returns the term count.
	 * 
	 * @return term count
	 */
	public Long getTermCount() {
		return this.termCount;
	}

	/**
	 * Returns the affirmation count.
	 * 
	 * @return affirmation count
	 */
	public Long getAffirmationCount() {
		return this.affirmationCount;
	}
}