package omis.substanceuse.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.substance.domain.Substance;

/**
 * Substance use form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Nov 29, 2016)
 * @since OMIS 3.0
 */
public class SubstanceUseForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private Substance substance;
	private List<UseAffirmationItem> useAffirmationItems
		= new ArrayList<UseAffirmationItem>();
	
	/**
	 * Instantiates a default instance of substance use form.
	 */
	public SubstanceUseForm() {
		//Default constructor.
	}

	/**
	 * Returns the substance.
	 * 
	 * @return substance
	 */
	public Substance getSubstance() {
		return this.substance;
	}

	/**
	 * Sets the substance.
	 * 
	 * @param substance substance
	 */
	public void setSubstance(final Substance substance) {
		this.substance = substance;
	}

	/**
	 * Returns the use affirmation items.
	 * 
	 * @return use affirmation items
	 */
	public List<UseAffirmationItem> getUseAffirmationItems() {
		return this.useAffirmationItems;
	}

	/**
	 * Sets the use affirmation items.
	 * 
	 * @param useAffirmationItems use affirmation items
	 */
	public void setUseAffirmationItems(
			final List<UseAffirmationItem> useAffirmationItems) {
		this.useAffirmationItems = useAffirmationItems;
	}
}