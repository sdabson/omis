package omis.health.web.form;

import java.util.ArrayList;
import java.util.List;

/**
 * Referral lab work form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 6, 2014)
 * @since OMIS 3.0
 */
public class ReferralLabWorkForm {

	private List<LabWorkAppointmentItem> labWork
		= new ArrayList<LabWorkAppointmentItem>();

	/**
	 * Instantiates a default instance of referral lab work form.
	 */
	public ReferralLabWorkForm() {
		//Default constructor.
	}
	
	/**
	 * Returns a list of lab work appointment items.
	 * 
	 * @return list of lab work appointment items
	 */
	public List<LabWorkAppointmentItem> getLabWork() {
		return this.labWork;
	}

	/**
	 * Sets a list of lab work appointment items.
	 * 
	 * @param labWork list of lab work appointment items
	 */
	public void setLabWork(final List<LabWorkAppointmentItem> labWork) {
		this.labWork = labWork;
	}
}