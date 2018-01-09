package omis.presentenceinvestigation.domain.impl;

import java.util.Date;

import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.presentenceinvestigation.domain.BiographicAndContactSection;
import omis.presentenceinvestigation.domain.PresentenceInvestigationRequest;

/** Implementation of the Biographic And Contact Section.
 * @author Jonny Santy
 * @version 0.1.1 (Jun 27, 2016)
 * since OMIS 3.0 */
public class BiographicAndContactSectionImpl 
	implements BiographicAndContactSection {
	
	private static final long serialVersionUID = 1L;

	private static final String EMPTY_PSI_REQUEST_MSG
		= "Presentence Investigation Request is required";

	private static final String EMPTY_NAME_MSG
		= "Name is required";

	private static final String EMPTY_DATE_OF_REPORT_MSG
		= "Date of report is required";
	
	
	private Long id;
	private PresentenceInvestigationRequest presentenceInvestigationRequest;
	private String name;
	private Date dateOfReport;
	private String alsoKnownAs;
	private Date dateOfSentence;
	private String address;
	private String phoneNumber;
	private String cellPhoneNumber;
	private UpdateSignature updateSignature;
	private CreationSignature creationSignature;

	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the presentenceInvestigationRequest
	 */
	public PresentenceInvestigationRequest getPresentenceInvestigationRequest() {
		return presentenceInvestigationRequest;
	}

	/**
	 * @param presentenceInvestigationRequest the presentenceInvestigationRequest to set
	 */
	public void setPresentenceInvestigationRequest(PresentenceInvestigationRequest presentenceInvestigationRequest) {
		this.presentenceInvestigationRequest = presentenceInvestigationRequest;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the dateOfReport
	 */
	public Date getDateOfReport() {
		return dateOfReport;
	}

	/**
	 * @param dateOfReport the date Of the PSI Report to set
	 */
	public void setDateOfReport(Date dateOfReport) {
		this.dateOfReport = dateOfReport;
	}

	/**
	 * @return the alias
	 */
	public String getAlsoKnownAs() {
		return alsoKnownAs;
	}

	/**
	 * @param alsoKnownAs the alias to set
	 */
	public void setAlsoKnownAs(String alsoKnownAs) {
		this.alsoKnownAs = alsoKnownAs;
	}

	/**
	 * @return the date Of Sentence
	 */
	public Date getDateOfSentence() {
		return dateOfSentence;
	}

	/**
	 * @param dateOfSentence the date Of Sentence to set
	 */
	public void setDateOfSentence(Date dateOfSentence) {
		this.dateOfSentence = dateOfSentence;
	}

	/**
	 * @return the address of the PSI offender
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address of the PSI offender to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone Number of the PSI offender
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phone Number of the PSI offender to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the cell Phone Number of the PSI offender
	 */
	public String getCellPhoneNumber() {
		return cellPhoneNumber;
	}

	/**
	 * @param cellPhoneNumber the cell Phone Number of the PSI offender to set
	 */
	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}

	/**
	 * @return the updateSignature
	 */
	public UpdateSignature getUpdateSignature() {
		return updateSignature;
	}

	/**
	 * @param updateSignature the updateSignature to set
	 */
	public void setUpdateSignature(UpdateSignature updateSignature) {
		this.updateSignature = updateSignature;
	}

	/**
	 * @return the creationSignature
	 */
	public CreationSignature getCreationSignature() {
		return creationSignature;
	}

	/**
	 * @param creationSignature the creationSignature to set
	 */
	public void setCreationSignature(CreationSignature creationSignature) {
		this.creationSignature = creationSignature;
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (this == obj) {
			result = true;
		} else {
			if (obj instanceof BiographicAndContactSection) {
				this.checkState();
				BiographicAndContactSection that 
					= (BiographicAndContactSection) obj;
				if (
						this.getPresentenceInvestigationRequest().equals(that.getPresentenceInvestigationRequest())&&
						this.getName().equals(that.getName())&&
						this.getDateOfReport().equals(that.getDateOfReport())
						) {
					result = true;
				}
			}
		}
		
		return result;
	}
	
	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		this.checkState();
		int hashCode = 14;

		hashCode = 29 * hashCode + this.getPresentenceInvestigationRequest().hashCode();
		hashCode += 29 * hashCode + this.getName().hashCode();
		hashCode += 29 * hashCode + this.getDateOfReport().hashCode();
		
		return hashCode;
	}
	
	/* Checks state. */
	private void checkState() {
		
		if (this.getPresentenceInvestigationRequest() == null) {
			throw new IllegalStateException(EMPTY_PSI_REQUEST_MSG);
		}
		
		if (this.getName() == null) {
			throw new IllegalStateException(EMPTY_NAME_MSG);
		}
		
		if (this.getDateOfReport() == null) {
			throw new IllegalStateException(EMPTY_DATE_OF_REPORT_MSG);
		}
	}

		
		
}
