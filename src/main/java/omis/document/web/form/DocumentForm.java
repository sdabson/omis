package omis.document.web.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** Form for document.
 * @author Ryan Johns
 * @version 0.1.0 (Nov 20, 2015)
 * @since OMIS 3.0 */
public interface DocumentForm extends Serializable {
	/** Gets date.
	 * @return data. */
	byte[] getData();
	
	/** Gets title.
	 * @return title. */
	String getTitle();
	
	/** Gets date.
	 * @return date. */
	Date getDate();
	
	/** Gets file extension.
	 * @return file extension. */
	String getFileExtension();
	
	/** Gets document tag items.
	 * @return document tag items. */
	List<DocumentTagItem> getDocumentTagItems();
	
	/** Sets title.
	 * @param title - title. */
	void setTitle(String title);
	
	/** Sets date.
	 * @param date - date. */
	void setDate(Date date);
	
	/** Sets data.
	 * @param data - data byte array. */
	void setData(byte[] data);
	
	/** Sets file extension.
	 * @param fileExtension - file extension. */
	void setFileExtension(String fileExtension);
	
	/** Sets document tag items.
	 * @param documentTagItems - document tag items. */
	void setDocumentTagItems(List<DocumentTagItem> documentTagItems);
	
}
