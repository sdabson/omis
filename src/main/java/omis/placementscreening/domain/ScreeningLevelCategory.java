package omis.placementscreening.domain;


/** Screening Level category.
 * @author Ryan Johns
 * @version 0.1.0 (Oct 30, 2014)
 * @since OMIS 3.0 */
public enum ScreeningLevelCategory  {
	/** State screening level category. */
	 STATE(true),
	/** Local state screening level category. */
		LOCAL(false),
	/** Assessment screening level category. */
		ASSESSMENT(true);
	
	private final Boolean isInstitutional;
	
	/** Constructor. 
	 * @param isInstitutional - is institutional.*/
	ScreeningLevelCategory(final Boolean isInstitutional) {
		this.isInstitutional = isInstitutional;
	}
	
	/** Gets institutional flag. 
	 * @return institutionalFlag institutional flag. */
	public Boolean getIsInstitutional() { 
		return this.isInstitutional; 
	}

	/** Returns screening level categories that are institutional. 
	 * @return institutional screening category levels. */
	public static ScreeningLevelCategory[] institutionalValues() {
		ScreeningLevelCategory[] institutionalCategories = {};
		for (ScreeningLevelCategory cats: ScreeningLevelCategory.values()) {
			if (cats.isInstitutional) {
				ScreeningLevelCategory[] temp = 
					new ScreeningLevelCategory[
					institutionalCategories.length + 1];
				System.arraycopy(institutionalCategories, 0, temp, 0,
					institutionalCategories.length);
				temp[temp.length - 1] = cats;
				institutionalCategories = temp;
			}
		}
		return institutionalCategories;
	}
}
