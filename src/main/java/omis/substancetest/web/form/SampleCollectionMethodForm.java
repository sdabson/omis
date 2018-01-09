package omis.substancetest.web.form;

import java.util.List;

import omis.substancetest.domain.TestKitParameter;

/**
 * Sample Collection Method Form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Jun 11, 2013)
 * @since OMIS 3.0
 */
public class SampleCollectionMethodForm {

	private String name;
	
	private String description;
	
	private List<TestKitParameter> testKitParameters;
	
	/**
	 * Instantiates a default instance of sample collection method form.
	 */
	public SampleCollectionMethodForm() {
		//Default Constructor
	}

	/**
	 * Return the name for the sample collection method form.
	 * 
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name form the sample collection method form.
	 * 
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Return the description for the sample collection method form.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * Set the description for the sample collection method form.
	 * 
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * Return the test kit parameters for the sample collection method form.
	 * 
	 * @return the testKitParameters
	 */
	public List<TestKitParameter> getTestKitParameters() {
		return this.testKitParameters;
	}

	/**
	 * Set the test kit parameters for the sample collection method form.
	 * 
	 * @param testKitParameters the testKitParameters to set
	 */
	public void setTestKitParameters(final 
			List<TestKitParameter> testKitParameters) {
		this.testKitParameters = testKitParameters;
	}
}