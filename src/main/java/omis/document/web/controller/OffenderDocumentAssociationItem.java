package omis.document.web.controller;

/** Controller item for document associations.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 27, 2015)
 * @since OMIS 3.0 */
public class OffenderDocumentAssociationItem {
	private final String documentAssociationNameKey;
	private final String listView;
	private final String createView;
	private final String listLinkLabelKey;
	private final String createLinkLabelKey;
	
	/** Constructor.
	 * @param documentAssociationNameKey - document association name key.
	 * @param listView - path to listing.
	 * @param listLinkLabelKey - list link label key.
	 * @param createView - create view.
	 * @param createLinkLabelKey - create link label key.
	 * @param offenderDocumentAssociationItemRegistry - 
	 * offender document association view item registry. 
	 * registry. */
	public OffenderDocumentAssociationItem(
			final String documentAssociationNameKey,
			final String listView, final String listLinkLabelKey,
			final String createView, final String createLinkLabelKey,
			final OffenderDocumentAssociationItemRegistry 
				offenderDocumentAssociationItemRegistry) {
		this.documentAssociationNameKey = documentAssociationNameKey;
		this.listView = listView;
		this.listLinkLabelKey = listLinkLabelKey;
		this.createView = createView;
		this.createLinkLabelKey = createLinkLabelKey;
		offenderDocumentAssociationItemRegistry.register(
					documentAssociationNameKey, this);
	}
	
	/** Gets document association name key.
	 * @return document association name key. */
	public String getDocumentAssociationNameKey() { 
		return this.documentAssociationNameKey;
	}
	
	/** Gets list view. 
	 * @return list view. */
	public String getListView() {
		return this.listView; 
	}
	
	/** Gets list link view.
	 * @return list link view. */
	public String getListLinkLabelKey() {
		return this.listLinkLabelKey;
	}
	
	/** Gets create view.
	 * @return create view. */
	public String getCreateView() {
		return this.createView;
	}
	
	/** Gets create link label.
	 * @return create link label. */
	public String getCreateLinkLabelKey() {
		return this.createLinkLabelKey;
	}
}
