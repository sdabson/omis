package omis.health.web.form;

import java.util.Date;

import omis.offender.domain.Offender;  

/** Form object for resolve lab work.
 * @author: Yidong Li
 * @version 0.1.0 (Nov 29,2014)
 * @since OMIS 3.0 */
public class SearchLabWorkForm {
	private Offender offender;
	private Date filterByStartDate;
	private Date filterByEndDate;
	private Boolean SampleTaken;
	
	/** Instantiates a vehicle association form. */
	public SearchLabWorkForm() {
		// Default instantiation
	}
	public Offender getOffender() {
		return this.offender;
	}
	public void setOffender(final Offender offender) {
		this.offender = offender;
	}
	public Date getFilterByStartDate() {
		return this.filterByStartDate;
	}
	public void setFilterByStartDate(final Date filterByStartDate) {
		this.filterByStartDate = filterByStartDate;
	}
	public Date getFilterByEndDate() {
		return this.filterByEndDate;
	}
	public void setFilterByEndDate(final Date filterByEndDate) {
		this.filterByEndDate = filterByEndDate;
	}
	public Boolean getSampleTaken() {
		return this.SampleTaken;
	}
	public void setSampleTaken(final Boolean SampleTaken) {
		this.SampleTaken = SampleTaken;
	}
}