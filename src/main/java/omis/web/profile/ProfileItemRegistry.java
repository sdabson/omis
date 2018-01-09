package omis.web.profile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Registry for profile items.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public class ProfileItemRegistry
		implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final Boolean defaultEnabled;
	
	private final List<ProfileItem> items = new ArrayList<ProfileItem>();
	
	/** Instantiates a default profile item registry. */
	public ProfileItemRegistry(
			final Boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
	}
	
	/**
	 * Registers the item.
	 * 
	 * @param item item to register
	 */
	public void register(final ProfileItem item) {
		this.items.add(item);
		Collections.sort(items);
	}
	
	/**
	 * Returns the unmodifiable items.
	 * 
	 * @return unmodifiable items
	 */
	public List<ProfileItem> getItems() {
		return Collections.unmodifiableList(this.items);
	}
	
	public Boolean getDefaultEnabled() {
		return this.defaultEnabled;
	}
}