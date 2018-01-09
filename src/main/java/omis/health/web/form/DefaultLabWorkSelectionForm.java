package omis.health.web.form;

import java.io.Serializable;
import java.util.Date;

import omis.facility.domain.Facility;
import omis.health.domain.Lab;
import omis.health.domain.ProviderAssignment;

/**
 * Form for default lab work values.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 5, 2014)
 * @since OMIS 3.0
 */
public class DefaultLabWorkSelectionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int index;
	
	private Facility facility;

	private Date defaultOrderDate;
	
	private Date defaultSampleDate;
	
	private Lab defaultSampleLab;
	
	private ProviderAssignment defaultOrderedBy;
	
	private Boolean defaultNoLeaky;
	
	private Boolean defaultNoMeds;
	
	private Boolean defaultNothingPerOral;
	
	/**
	 * Instantiates a default instance of the default lab work selecion form.
	 */
	public DefaultLabWorkSelectionForm() {
		//Default constructor.
	}

	/**
	 * Returns the index.
	 * 
	 * @return index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Sets the index.
	 * 
	 * @param index index
	 */
	public void setIndex(final int index) {
		this.index = index;
	}

	/**
	 * Returns the facility.
	 * 
	 * @return facility
	 */
	public Facility getFacility() {
		return this.facility;
	}

	/**
	 * Sets the facility.
	 * 
	 * @param facility facility
	 */
	public void setFacility(final Facility facility) {
		this.facility = facility;
	}

	/**
	 * Returns the default order date.
	 * 
	 * @return default order date
	 */
	public Date getDefaultOrderDate() {
		return this.defaultOrderDate;
	}

	/**
	 * Sets the default order date.
	 * 
	 * @param defaultOrderDate default order date
	 */
	public void setDefaultOrderDate(final Date defaultOrderDate) {
		this.defaultOrderDate = defaultOrderDate;
	}

	/**
	 * Returns the default sample date.
	 * 
	 * @return default sample date
	 */
	public Date getDefaultSampleDate() {
		return this.defaultSampleDate;
	}

	/**
	 * Sets the default sample date.
	 * 
	 * @param defaultSampleDate default sample date
	 */
	public void setDefaultSampleDate(final Date defaultSampleDate) {
		this.defaultSampleDate = defaultSampleDate;
	}

	/**
	 * Returns the default sample lab.
	 * 
	 * @return default sample lab
	 */
	public Lab getDefaultSampleLab() {
		return this.defaultSampleLab;
	}

	/**
	 * Sets the default sample lab.
	 * 
	 * @param defaultSampleLab default sample lab
	 */
	public void setDefaultSampleLab(final Lab defaultSampleLab) {
		this.defaultSampleLab = defaultSampleLab;
	}

	/**
	 * Returns the default ordered by provider assignment.
	 * 
	 * @return default ordered by provider assignment
	 */
	public ProviderAssignment getDefaultOrderedBy() {
		return this.defaultOrderedBy;
	}

	/**
	 * Sets the default ordered by provider assignment.
	 * 
	 * @param defaultOrderedBy default ordered by provider assignment
	 */
	public void setDefaultOrderedBy(final ProviderAssignment defaultOrderedBy) {
		this.defaultOrderedBy = defaultOrderedBy;
	}

	/**
	 * Returns whether, by default, the no leaky restriction should be applied.
	 * 
	 * @return default no leaky restriction
	 */
	public Boolean getDefaultNoLeaky() {
		return this.defaultNoLeaky;
	}

	/**
	 * Sets whether, by default, the no leaky restriction should be applied.
	 * 
	 * @param defaultNoLeaky default no leaky restriction
	 */
	public void setDefaultNoLeaky(final Boolean defaultNoLeaky) {
		this.defaultNoLeaky = defaultNoLeaky;
	}

	/**
	 * Returns whether, by default, the no medications restriction should be
	 * applied.
	 * 
	 * @return default no medications restriction
	 */
	public Boolean getDefaultNoMeds() {
		return this.defaultNoMeds;
	}

	/**
	 * Sets whether, by default, the no medications restriction should be
	 * applied.
	 * 
	 * @param defaultNoMeds default no medications restrictions
	 */
	public void setDefaultNoMeds(final Boolean defaultNoMeds) {
		this.defaultNoMeds = defaultNoMeds;
	}

	/**
	 * Returns whether, by default, the nothing per oral restriction should be
	 * applied.
	 * 
	 * @return default nothing per oral restriction
	 */
	public Boolean getDefaultNothingPerOral() {
		return this.defaultNothingPerOral;
	}

	/**
	 * Sets whether, by default, the nothing per oral restriction should be
	 * applied.
	 * 
	 * @param defaultNothingPerOral default nothing per oral restriction
	 */
	public void setDefaultNothingPerOral(final Boolean defaultNothingPerOral) {
		this.defaultNothingPerOral = defaultNothingPerOral;
	}
}