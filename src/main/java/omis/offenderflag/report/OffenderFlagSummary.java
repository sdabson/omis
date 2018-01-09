package omis.offenderflag.report;

import java.io.Serializable;

import omis.offenderflag.domain.FlagUsage;

/**
 * OffenderFlagSummary.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 15, 2016)
 *@since OMIS 3.0
 *
 */
public class OffenderFlagSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final Boolean offenderFlagValue;
	
	private final String offenderFlagCategoryName;
	
	private final FlagUsage offenderFlagUsage;

	/**
	 * @param offenderFlagValue
	 * @param offenderFlagCategoryName
	 * @param offenderFlagUsage
	 */
	public OffenderFlagSummary(
			final Boolean offenderFlagValue, final String offenderFlagCategoryName,
			final FlagUsage offenderFlagUsage) {
		this.offenderFlagValue = offenderFlagValue;
		this.offenderFlagCategoryName = offenderFlagCategoryName;
		this.offenderFlagUsage = offenderFlagUsage;
	}

	/**
	 * @return the offenderFlagValue
	 */
	public Boolean getOffenderFlagValue() {
		return offenderFlagValue;
	}

	/**
	 * @return the offenderFlagCategoryName
	 */
	public String getOffenderFlagCategoryName() {
		return offenderFlagCategoryName;
	}

	/**
	 * @return the offenderFlagUsage
	 */
	public FlagUsage getOffenderFlagUsage() {
		return offenderFlagUsage;
	}
	
	
}
