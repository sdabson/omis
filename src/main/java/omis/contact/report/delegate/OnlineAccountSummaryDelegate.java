package omis.contact.report.delegate;

import java.io.Serializable;

import omis.contact.domain.OnlineAccount;
import omis.contact.domain.OnlineAccountCategory;

/**
 * Delegate to summarize online account details.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2016)
 * @since OMIS 3.0
 */
public class OnlineAccountSummaryDelegate
		implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String name;
	
	private final String hostName;
	
	private final OnlineAccountCategory category;
	
	private final Boolean active;
	
	private final Boolean primary;
	
	/**
	 * Instantiates delegate to summarize online account details.
	 * 
	 * @param onlineAccount online account to summarize
	 */
	public OnlineAccountSummaryDelegate(
			final OnlineAccount onlineAccount) {
		if (onlineAccount != null) {
			this.name = onlineAccount.getName();
			this.hostName = onlineAccount.getHost().getName();
			this.category = onlineAccount.getHost().getCategory();
			this.active = onlineAccount.getActive();
			this.primary = onlineAccount.getPrimary();
		} else {
			this.name = null;
			this.hostName = null;
			this.category = null;
			this.active = null;
			this.primary = null;
		}
	}

	/**
	 * Returns name.
	 * 
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns host name.
	 * 
	 * @return host name
	 */
	public String getHostName() {
		return this.hostName;
	}

	/**
	 * Returns category.
	 * 
	 * @return category
	 */
	public OnlineAccountCategory getCategory() {
		return this.category;
	}

	/**
	 * Returns whether active.
	 * 
	 * @return whether active
	 */
	public Boolean getActive() {
		return this.active;
	}

	/**
	 * Returns whether primary.
	 * 
	 * @return whether primary
	 */
	public Boolean getPrimary() {
		return this.primary;
	}
}