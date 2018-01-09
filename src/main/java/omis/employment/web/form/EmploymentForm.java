package omis.employment.web.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import omis.address.web.form.AddressFields;
import omis.address.domain.Address;
import omis.audit.domain.VerificationMethod;
import omis.employment.domain.Employer;
import omis.employment.domain.EmploymentChangeReason;
import omis.employment.domain.WagePaymentFrequency;
import omis.employment.domain.component.WorkShiftDays;
import omis.employment.domain.WorkShiftFrequency;
import omis.employment.web.controller.EmploymentAddressOperation;
import omis.user.domain.UserAccount;

/** Edit form for employment history.
 * @author: Yidong Li
 * @version 0.1.0 (Feb 10, 2015)
 * @since OMIS 3.0 */
public class EmploymentForm implements Serializable {
	private static final long serialVersionUID = 1L;
	private Employer existingEmployer;
	private String employerName;
	private EmploymentAddressOperation employmentAddressOperation;
	private AddressFields addressFields;
	private Address address;
	private String addressQuery;
	private Date startDate;
	private Date endDate;
	private EmploymentChangeReason terminationReason;
	private String extension;
	private String jobTitle;
	private String supervisorName;
	private WorkShiftFrequency workShift;
	private WorkShiftDays daysOff;
	private Boolean varies;
	private Date startTime;
	private Date endTime;
	private Integer hoursPerWeek;
	private BigDecimal wageAmount;
	private WagePaymentFrequency paymentFrequency;
	private UserAccount verificationUserAccount;
	private String verificationUserAccountLabel;
	private Date verificationDate;
	private Boolean verified;
	private VerificationMethod verificationMethod;
	private Boolean convictedOfEmployerTheft;
	private String userName;
	private Date modifiedDate;
	private String telephoneNumber;
	
	/** Instantiates an employment history edit form. */
	public EmploymentForm() {
		// Default instantiation
	}
	
	/** Get existing employer. */
	public Employer getExistingEmployer() {
		return this.existingEmployer;
	}
	
	/** Set existing employer name. */
	public void setExistingEmployer(final Employer existingEmployer) {
		this.existingEmployer = existingEmployer;
	}
	
	/** Get employer name. */
	public String getEmployerName() {
		return this.employerName;
	}
	
	/** Set employer name. */
	public void setEmployerName(final String employerName) {
		this.employerName = employerName;
	}
	
	/** Get employment address operation. */
	public EmploymentAddressOperation getEmploymentAddressOperation() {
		return this.employmentAddressOperation;
	}
	
	/** Set employment address operation. */
	public void setEmploymentAddressOperation(
		final EmploymentAddressOperation employmentAddressOperation) {
		this.employmentAddressOperation = employmentAddressOperation;
	}
	
	/** Get address fields. */
	public AddressFields getAddressFields() {
		return this.addressFields;
	}
	
	/** Set address fields. */
	public void setAddressFields(final AddressFields addressFields) {
		this.addressFields = addressFields;
	}
	
	/** Get Address. */
	public Address getAddress() {
		return this.address;
	}
	
	/** Set address. */
	public void setAddress(final Address address) {
		this.address = address;
	}
	
	/** Get address query. */
	public String getAddressQuery() {
		return this.addressQuery;
	}
	
	/** Set address query. */
	public void setAddressQuery(final String addressQuery) {
		this.addressQuery = addressQuery;
	}
	
	/** Get start date. */
	public Date getStartDate() {
		return this.startDate;
	}
	
	/** Set start date. */
	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}
	
	/** Get end date. */
	public Date getEndDate() {
		return this.endDate;
	}
	
	/** Set end date. */
	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}
	
	/** Get employment termination reason. */
	public EmploymentChangeReason getTerminationReason() {
		return this.terminationReason;
	}
	
	/** Set employment termination reason. */
	public void setTerminationReason(final EmploymentChangeReason 
		terminationReason) {
		this.terminationReason = terminationReason;
	}
	
	/** Get phone extension */
	public String getExtension() {
		return this.extension;
	}
	
	/** Set phone extension*/
	public void setExtension(final String extension) {
		this.extension = extension;
	}
	
	/** Get job title */
	public String getJobTitle() {
		return this.jobTitle;
	}
	
	/** Set job title */
	public void setJobTitle(final String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/** Get supervisor name */
	public String getSupervisorName() {
		return this.supervisorName;
	}
	
	/** Set supervisor name */
	public void setSupervisorName(final String supervisorName) {
		this.supervisorName = supervisorName;
	}
	
	/** Get work shift */
	public WorkShiftFrequency getWorkShift() {
		return this.workShift;
	}
	
	/** Set work shift */
	public void setWorkShift(final WorkShiftFrequency workShift) {
		this.workShift = workShift;
	}
	
	/** Get the value of if work shift varies or not */
	public Boolean getVaries() {
		return this.varies;
	}
	
	/** set the value of if work shift varies or not */
	public void setVaries(final Boolean varies) {
		this.varies = varies;
	}
	
	/** Get the info of which days are off */
	public WorkShiftDays getDaysOff() {
		return this.daysOff;
	}
	
	/** Set the which days are on or off */
	public void setDaysOff(final WorkShiftDays daysOff) {
		this.daysOff = daysOff;
	}
	
	/** Get start time */
	public Date getStartTime() {
		return this.startTime;
	}
	
	/** Set start time */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}
	
	/** Get end time */
	public Date getEndTime() {
		return this.endTime;
	}
	
	/** Set end time */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}
	
	/** Get the value of how hours per week */
	public Integer getHoursPerWeek() {
		return this.hoursPerWeek;
	}
	
	/** Set the value of how hours per week */
	public void setHoursPerWeek(final Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}
	
	/** Get the amount of wage */
	public BigDecimal getWageAmount() {
		return this.wageAmount;
	}
	
	/** Set the amount of wage */
	public void setWageAmount(final BigDecimal wageAmount) {
		this.wageAmount = wageAmount;
	}
	
	/** Get the info on how often this offender get paid */
	public WagePaymentFrequency getPaymentFrequency() {
		return this.paymentFrequency;
	}
	
	/** Set the info on how often this offender get paid */
	public void setPaymentFrequency(final WagePaymentFrequency paymentFrequency){
		this.paymentFrequency = paymentFrequency;
	}
	
	/** Get the user account */
	public UserAccount getVerificationUserAccount() {
		return this.verificationUserAccount;
	}
	
	/** Set the user account */
	public void setVerificationUserAccount(
		final UserAccount verificationUserAccount) {
		this.verificationUserAccount = verificationUserAccount;
	}
	
	/** Get the user account label*/
	public String getVerificationUserAccountLabel() {
		return this.verificationUserAccountLabel;
	}
	
	/** Set the user account label*/
	public void setVerificationUserAccountLabel(
		final String verificationUserAccountLabel) {
		this.verificationUserAccountLabel = verificationUserAccountLabel;
	}
	
	/** Get the verification date*/
	public Date getVerificationDate() {
		return this.verificationDate;
	}
	
	/** Set the verification date*/
	public void setVerificationDate(final Date verificationDate) {
		this.verificationDate = verificationDate;
	}
	
	/** Get the info on if this "verified" has been set or not */
	public Boolean getVerified() {
		return this.verified;
	}
	
	/** Set "verified" */
	public void setVerified(final Boolean verified) {
		this.verified = verified;
	}
	
	/** Get the verification method */
	public VerificationMethod getVerificationMethod() {
		return this.verificationMethod;
	}
	
	/** Set the verification method */
	public void setVerificationMethod(
		final VerificationMethod verificationMethod) {
		this.verificationMethod = verificationMethod;
	}
	
	/** Get the info on if this offender has been convicted of employment theft*/
	public Boolean getConvictedOfEmployerTheft() {
		return this.convictedOfEmployerTheft;
	}
	
	/** Set if this offender has been convicted of employment theft*/
	public void setConvictedOfEmployerTheft(
		final Boolean convictedOfEmployerTheft) {
		this.convictedOfEmployerTheft = convictedOfEmployerTheft;
	}
	
	/** Get the user name*/
	public String getUserName() {
		return this.userName;
	}
	
	/** Set the user name*/
	public void setUserName(final String userName) {
		this.userName = userName;
	}
	
	/** Get the modified date*/
	public Date getModifiedDate() {
		return this.modifiedDate;
	}
	
	/** Set the modified date*/
	public void setModifiedDate(final Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	/** Get the telephone number*/
	public String getTelephoneNumber() {
		return this.telephoneNumber;
	}
	
	/** Set the telephone number*/
	public void setTelephoneNumber(final String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
}