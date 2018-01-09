package omis.contact.report;

import java.io.Serializable;

import omis.contact.domain.OnlineAccountCategory;

/**
 * Summarizes online account details.
 *
 * @author Stephen Abson
 * @version 0.0.1 (May 11, 2016)
 * @since OMIS 3.0
 */
public interface OnlineAccountSummarizable
		extends Serializable {

	/**
	 * Returns name of online account.
	 * 
	 * @return name of online account
	 */
	String getOnlineAccountName();
	
	/**
	 * Returns name of host of online account.
	 * 
	 * @return name of host of online account
	 */
	String getOnlineAccountHostName();
	
	/**
	 * Returns category of online account.
	 * 
	 * @return category of online account
	 */
	OnlineAccountCategory getOnlineAccountCategory();

	/**
	 * Returns whether online account is active.
	 * 
	 * @return whether online account is active
	 */
	Boolean getOnlineAccountActive();
	
	/**
	 * Returns whether online account is primary.
	 * 
	 * @return whether online account is primary
	 */
	Boolean getOnlineAccountPrimary();
}