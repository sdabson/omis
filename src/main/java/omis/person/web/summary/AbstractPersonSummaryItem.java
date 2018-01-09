package omis.person.web.summary;

/**
 * Abstract implementation of person summary item.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Feb 12, 2016)
 * @since OMIS 3.0
 */
public abstract class AbstractPersonSummaryItem implements PersonSummaryItem {

	private static final long serialVersionUID = 1L;

	private final String includedPageName;
	
	private final PersonSummaryItemRegistry registry;
	
	private final int order;
	
	/**
	 * Instantiates an instance of abstract person summary item.
	 * 
	 * @param includedPageName included page name
	 * @param registry person summary item registry
	 * @param order order
	 */
	public AbstractPersonSummaryItem(final String includedPageName,
			final PersonSummaryItemRegistry registry, final int order) {
		this.includedPageName = includedPageName;
		this.registry = registry;
		this.order = order;
		this.registry.register(this);
	}
	
	/** {@inheritDoc} */
	@Override
	public int getOrder() {
		return this.order;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getIncludedPageName() {
		return this.includedPageName;
	}
	
	/** {@inheritDoc} */
	@Override
	public int compareTo(final PersonSummaryItem that) {
		return this.getOrder() - that.getOrder();
	}
}