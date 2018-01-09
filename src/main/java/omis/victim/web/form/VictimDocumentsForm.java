package omis.victim.web.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import omis.person.domain.Person;

/**
 * Victim documents form.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Aug 23, 2017)
 * @since OMIS 3.0
 */
public class VictimDocumentsForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private Person victim;
	private List<VictimDocumentItem> documentItems = new ArrayList<VictimDocumentItem>();
	
	/**
	 * Instantiates a default instance of victim documents form.
	 */
	public VictimDocumentsForm() {
		//Default constructor
	}

	/**
	 * Returns the victim.
	 * 
	 * @return victim
	 */
	public Person getVictim() {
		return this.victim;
	}

	/**
	 * Sets the victim.
	 * 
	 * @param victim victim
	 */
	public void setVictim(final Person victim) {
		this.victim = victim;
	}

	/**
	 * Returns the victim document items.
	 * 
	 * @return list of victim document items
	 */
	public List<VictimDocumentItem> getDocumentItems() {
		return documentItems;
	}

	/**
	 * Sets the victim document items.
	 * 
	 * @param documentItems victim document items
	 */
	public void setDocumentItems(List<VictimDocumentItem> documentItems) {
		this.documentItems = documentItems;
	}
}