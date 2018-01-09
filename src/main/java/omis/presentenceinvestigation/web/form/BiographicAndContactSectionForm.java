package omis.presentenceinvestigation.web.form;

import java.util.Date;
import java.util.HashMap;

import omis.person.domain.PersonName;

/**
 * Used to capture contact information.
 * 
 * @author Jonny Santy
 * @version 0.1.1 (Nov 1, 2016)
 * @since OMIS 3.0
 */
public class BiographicAndContactSectionForm {

	private String name;
	private Long nameId;
	private Date dateOfSentence;
	private String address;
	private Long addressId;
	private String aka;
	private String phone;
	private String cellPhone;
	private HashMap<Long,String> alternativeNames = new HashMap<Long,String>();
	
	
	/** Instantiates a default caution form. */
	public BiographicAndContactSectionForm() {
		super();
		// Default instantiation
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getDateOfSentence() {
		return dateOfSentence;
	}


	public void setDateOfSentence(Date dateOfSentence) {
		this.dateOfSentence = dateOfSentence;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getAka() {
		return aka;
	}


	public void setAka(String aka) {
		this.aka = aka;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getCellPhone() {
		return cellPhone;
	}


	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}


	public void addAlternativeName(PersonName name2) {
		this.alternativeNames.put(name2.getId(),
				name2.getFirstName()==null?"":name2.getFirstName()
				+" "+name2.getMiddleName()==null?"":name2.getMiddleName()
				+" "+name2.getLastName()==null?"":name2.getLastName()
				+" "+name2.getSuffix()==null?"":name2.getSuffix());
	}


	public HashMap<Long,String> getAlternativeNames() {
		return alternativeNames;
	}


	public void setAlternativeNames(HashMap<Long,String> alternativeNames) {
		this.alternativeNames = alternativeNames;
	}


	public Long getAddressId() {
		return addressId;
	}


	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}


	public Long getNameId() {
		return nameId;
	}


	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}
	
	

	
}