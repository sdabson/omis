package omis.presentenceinvestigation.domain;

import java.util.Date;

import omis.audit.domain.Creatable;
import omis.audit.domain.Updatable;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/** Biographic and Contact Section
 * 
 * @author Jonny Santy
 * @version 0.1.0 (Oct 27, 2016)
 * @since OMIS 3.0 
 * */
public interface BiographicAndContactSection 
	extends Creatable, Updatable {
	
	
	
	/*--------------GETTERS-------------------*/
	/** Gets id.
	 * @return id. */
	public Long getId();
	
	/** Gets the presentence investigation request associated with this contact section										
 	 * @return  presentence investigation request . 
 	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest();
	
	/** Gets offender Name according to PSI
	 * @return offender PSI Name
	 */
	public String getName();
	
	/** Gets the date of the report
	 * @return report date. */
	public Date getDateOfReport();
	
	/** Gets AKA
	 * @return offender PSI alias. */
	public String getAlsoKnownAs();
	
	/** Gets date of sentence hearing
	 * @return date of sentence. */
	public Date getDateOfSentence();
	
	/** Gets offender address.
	 * @return address. */
	public String getAddress();
	
	/** Gets phone number.
	 * @return phone number. */
	public String getPhoneNumber();
	
	/** Gets cell phone number
	 * @return cell phone number. */
	public String getCellPhoneNumber();
	
	/*--------------SETTERS-------------------*/
	/** Sets id.
	 * @param id - id. */
	public void setId(Long id);
	
	/** Sets the presentence investigation request associated with this contact section	
 	 */
	public void setPresentenceInvestigationRequest(PresentenceInvestigationRequest presentenceInvestigationRequest);
	
	/** sets offender Name according to PSI
	 */
	public void setName(String name);
	
	/** sets the date of the report
	 * */
	public void setDateOfReport(Date report);
	
	/** sets AKA
	 * */
	public void setAlsoKnownAs(String aka);
	
	/** sets date of sentence hearing
	 * */
	public void setDateOfSentence(Date sentence);
	
	/** sets offender address.
	 * */
	public void setAddress(String address);
	
	/** sets phone number.
	 * */
	public void setPhoneNumber(String phone);
	
	/** sets cell phone number
	 * */
	public void setCellPhoneNumber(String cell);
	
	 /** Compares {@code this} and {@code obj} for equality.
	 * <p>
	 * Any mandatory property may be used in the comparison. If a  mandatory
	 * property of {@code this} that is used in the comparison is {@code null}
	 * an {@code IllegalStateException} will be thrown.
	 * @param obj reference object with which to compare {@code this}
	 * @return {@code true} if {@code this} and {@code obj} are equal;
	 * {@code false} otherwise
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the comparison is {@code null} */
	@Override
	boolean equals(Object obj);
	
	/** Returns a hash code for {@code this}.
	 * <p>
	 * Any mandatory property of {@code this} may be used in the hash code. If
	 * a mandatory property that is used in the hash code is {@code null} an
	 * {@code IllegalStateException} will be thrown.
	 * @return hash code
	 * @throws IllegalStateException if a mandatory property of {@code this}
	 * that is used in the hash code is {@code null} */
	@Override
	int hashCode();
}
