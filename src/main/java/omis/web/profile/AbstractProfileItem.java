package omis.web.profile;

import java.util.Set;

/**
 * Abstract profile item.
 * 
 * @author Stephen Abson
 * @version 0.1.0 (Nov 15, 2013)
 * @since OMIS 3.0
 */
public abstract class AbstractProfileItem
		implements ProfileItem {

	private static final long serialVersionUID = 1L;
	
	private final String requiredAuthorization;
	
	private final boolean requiredAuthorizationSupported;
	
	private final Set<String> requiredAuthorizations;
	
	private final String includePage;
	
	private final String name;
	
	private final Boolean enabled;
	
	/**
	 * Instantiates an abstract profile item optionally enabled.
	 * 
	 * @param requiredAuthorizations required authorizations
	 * @param includePage include page
	 * @param order - order.
	 * @param profileItemRegistry profile item registry
	 * @param enabled whether enabled
	 */
	public AbstractProfileItem(
			final Set<String> requiredAuthorizations,
			final String includePage,
			final String name,
			final ProfileItemRegistry profileItemRegistry,
			final Boolean enabled) {
		this.requiredAuthorization = null;
		this.requiredAuthorizationSupported = false;
		this.requiredAuthorizations = requiredAuthorizations;
		this.includePage = includePage;
		this.name = name;
		this.enabled = enabled;
		profileItemRegistry.register(this);
	}
	
	/** {@inheritDoc} */
	@Override
	public String getRequiredAuthorization() {
		if (!this.requiredAuthorizationSupported) {
			throw new UnsupportedOperationException(
					"Single required authorization not supported");
		}
		return this.requiredAuthorization;
	}
	
	/** {@inheritDoc} */
	@Override
	public Set<String> getRequiredAuthorizations() {
		return this.requiredAuthorizations;
	}

	/** {@inheritDoc} */
	@Override
	public String getIncludePage() {
		return this.includePage;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getName() {
		return this.name;
	}
	
	/** {@inheritDoc} */
	@Override
	public int compareTo(ProfileItem o) {
		return this.getName().compareTo(o.getName());
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean getEnabled() {
		return this.enabled;
	}
}