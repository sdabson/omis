package omis.contact.web.form;

import java.io.Serializable;

import omis.contact.domain.OnlineAccountHost;

/**
 * Online account fields.
 *
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 26, 2015)
 * @since OMIS 3.0
 */
public class OnlineAccountFields implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private OnlineAccountHost host;
	
	private Boolean active;
	
	private Boolean primary;
	
	/**
	 * Instantiates a default instance of online account fields.
	 */
	public OnlineAccountFields() {
		// Default constructor.
	}	
	
	public OnlineAccountFields(final String name, final OnlineAccountHost host,
			final Boolean active, final Boolean primary) {
		this.name = name;
		this.host = host;
		this.active = active;
		this.primary = primary;
	}

	/**
	 * Returns the name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Returns the online account host.
	 * 
	 * @return online account host
	 */
	public OnlineAccountHost getHost() {
		return this.host;
	}

	/**
	 * Sets the online account host.
	 * 
	 * @param host online account host
	 */
	public void setHost(final OnlineAccountHost host) {
		this.host = host;
	}

	/**
	 * Returns whether active applies.
	 * 
	 * @return active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Sets whether active applies.
	 * 
	 * @param active active
	 */
	public void setActive(final Boolean active) {
		this.active = active;
	}

	/**
	 * Returns primary online account.
	 * 
	 * @return primary 
	 */
	public Boolean getPrimary() {
		return primary;
	}

	/**
	 * Sets primary online account.
	 * 
	 * @param primary primary
	 */
	public void setPrimary(final Boolean primary) {
		this.primary = primary;
	}
}