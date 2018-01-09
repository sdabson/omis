package omis.placementscreening.domain;


/** Program category.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public enum ProgramCategory {
	/** Prerelease category. */
	PRERELEASE(false),
	/** Elkhorn category. */
	ELKHORN(true),
	/** Nexus category. */
	NEXUS(true),
	/** Watch category. */
	WATCH(true),
	/** CCP category. */
	CCP(true),
	/** Passages category. */
	PASSAGES(true);
	
	private Boolean treatment;

	/** Constructor.
	 * @param treatment treatment.*/
	ProgramCategory(final boolean treatment) {
		this.treatment = treatment;
	}
	
	/** Gets indication of treatment category. 
	 * @return is treatment indicator. */
	public Boolean isTreatment() { 
		return this.treatment; 
	}
	
	
	/** Gets treatment flag.
	 * @return treatmentFlat treatmentFlag. */
	public Boolean getTreatmentFlag() { 
		return this.treatment; 
	}

	/** Returns treatment categories.
	 * @return list of program categories that are treatment programs. */
	public static ProgramCategory[] treatmentValues() {
		ProgramCategory[] treatmentCategories = {};
		for (ProgramCategory programCategory: ProgramCategory.values()) {
			if (programCategory.isTreatment()) {
				ProgramCategory[] temp = new ProgramCategory[treatmentCategories
				                                             .length + 1];
				System.arraycopy(treatmentCategories, 0, temp, 0, 
						treatmentCategories.length);
				temp[temp.length - 1] = programCategory;
				treatmentCategories = temp;
			}
		}
		return treatmentCategories;
	}
	
}
