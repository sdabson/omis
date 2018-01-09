package omis.presentenceinvestigation.report;

public class BiographicAndContactSummary {

	private final String name;
	private final Long personName;
	private final String address;
	private final Long adddressId;
	private final String alsoKnownAs;
	private final Long alternativePersonNameId;

	/**Constructor
	 * 
	 * @param name - name of offender
	 * @param personName - ID which points to the name of the person
	 * @param address
	 * @param adddressId
	 * @param alsoKnownAs
	 * @param alternativePersonNameId
	 */
	public BiographicAndContactSummary(String name, Long personName, String address, Long adddressId,
			String alsoKnownAs, Long alternativePersonNameId) {
		super();
		this.name = name;
		this.personName = personName;
		this.address = address;
		this.adddressId = adddressId;
		this.alsoKnownAs = alsoKnownAs;
		this.alternativePersonNameId = alternativePersonNameId;
	}


	/**
	 * Gets offender name
	 * @return offender name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Gets the person name so you have reference to the personName to link back to
	 * @return specific person name
	 */
	public Long getPersonName() {
		return personName;
	}

	/**
	 *  The address of the offender
	 * @return
	 */
	public String getAddress() {
		return address;
	}


	/**
	 * Gets the specific address so you have reference to the address to link back to
	 * @return specific address
	 */
	public Long getAdddressId() {
		return adddressId;
	}


	/**
	 * The AKA of the offender
	 * @return  return the AKA of the offender
	 */
	public String getAlsoKnownAs() {
		return alsoKnownAs;
	}
	
	
	/**
	 * The specific alias of the offender
	 * @return  return the specific alias of the offender
	 */
	public Long getAlternativePersonNameId() {
		return alternativePersonNameId;
	}
	
	
	
	
}
