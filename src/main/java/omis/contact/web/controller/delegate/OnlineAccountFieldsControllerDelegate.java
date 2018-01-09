package omis.contact.web.controller.delegate;

import java.util.List;

import org.springframework.ui.ModelMap;

import omis.contact.domain.OnlineAccountHost;

/**
 * Online account fields controller delegate.
 * 
 * @author Sheronda Vaughn
 * @version 0.1.0 (Jan 22, 2016)
 * @since OMIS 3.0
 */
public class OnlineAccountFieldsControllerDelegate {
	
	private String ONLINE_ACCOUNT_HOSTS_MODEL_KEY = "onlineAccountHosts";

	/**
	 * Instantiates a default instance of online account contact item fields 
	 * controller delegate.
	 */
	public OnlineAccountFieldsControllerDelegate() {
		// Default constructor.
	}

	/* Delegate methods. */	
	
	/**
	 * Prepare to edit online account fields.
	 * 
	 * @param map map 
	 * @param host host
	 * @return map
	 */
	public ModelMap prepareEditOnlineAccountFields(
			final ModelMap map, List<OnlineAccountHost> hosts) {
		map.addAttribute(ONLINE_ACCOUNT_HOSTS_MODEL_KEY, hosts);
		return map;		
	}
}