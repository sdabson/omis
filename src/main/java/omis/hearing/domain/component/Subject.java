package omis.hearing.domain.component;

import java.io.Serializable;

import omis.offender.domain.Offender;

/**
 * Subject.java
 * 
 *@author Annie Jacques 
 *@version 0.1.0 (Dec 27, 2016)
 *@since OMIS 3.0
 *
 */
public class Subject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Offender offender;
	
	private Boolean inAttendance;
	
	public Subject(){
		
	}

	/**
	 * @return the offender
	 */
	public Offender getOffender() {
		return offender;
	}

	/**
	 * @param offender the offender to set
	 */
	public void setOffender(Offender offender) {
		this.offender = offender;
	}

	/**
	 * @return the inAttendance
	 */
	public Boolean getInAttendance() {
		return inAttendance;
	}

	/**
	 * @param inAttendance the inAttendance to set
	 */
	public void setInAttendance(Boolean inAttendance) {
		this.inAttendance = inAttendance;
	}
	
	
	
}
