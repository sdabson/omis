package omis.document.util;

import java.util.Date;

import omis.document.domain.Document;
import omis.document.domain.impl.DocumentImpl;

/** Creates mock documents.
 * @author Ryan JOhns
 * @version 0.1.0 (Dec 29, 2015)
 * @since OMIS 3.0 */
public final class MockDocumentFactory {
	/* Document X values. */
	
	/** Sample documents values. */
	public enum DocValues {
		/** Document X. */
		X(new Long(1L), "1.txt", 978332400000L /* 1/1/2001 */, "file1", "txt"),
		/** Document Y. */
		Y(new Long(2L), "2.jpg", 1012633200000L /* 2/2/2002 */, "My file", 
				"jpg"),
		/** Document Z. */
		Z(new Long(3L), "4.xls", 1451397030000L /* 12/29/2015 6:50:30 AM */, 
				"This file is special", "xls");
		
		private final Long documentId;
		private final String documentFileName;
		private final Date documentDate;
		private final String title;
		private final String documentExtension;
		
		/** Constructor.
		 * @param documentId - document id.
		 * @param documentFileName - document file name. 
		 * @param dateMilliseconds - date in milliseconds.
		 * @param title - title.
		 * @param documentExtension - document extension. */
		DocValues(final Long documentId, final String documentFileName, 
				final long dateMilliseconds, final String title, 
				final String documentExtension) {
			this.documentId = documentId;
			this.documentFileName = documentFileName;
			this.documentDate = new Date(dateMilliseconds);
			this.title = title;
			this.documentExtension = documentExtension;
		}
		
		/** Gets id. 
		 * @return id. */
		public Long getId() {
			return this.documentId; 
		}
		
		/** Gets filename.
		 * @return filename. */
		public String getFilename() {
			return this.documentFileName;
		}
		
		/** Gets date.
		 * @return date. */
		public Date getDate() {
			return this.documentDate;
		}
		
		/** Gets title.
		 * @return title. */
		public String getTitle() {
			return this.title;
		}
		
		/** Gets extension.
		 * @return extension. */
		public String getExtension() {
			return this.documentExtension;
		}
	}
	
	/* Constructor. */
	private MockDocumentFactory() { }
	
	/** Mockup document.
	 * @param docValues - doc values object. 
	 * @return document. */
	public static Document mockUp(final DocValues docValues) {
		return MockDocumentFactory.mockUp(docValues.getId(), 
				docValues.getFilename(), docValues.getDate(), 
				docValues.getTitle(), docValues.getExtension());
	}
	
	/** Mockup document.
	 * @param id - id.
	 * @param filename - file name.
	 * @param date - date. 
	 * @param title - title.
	 * @param extension - extension.
	 * @return document. */
	public static Document mockUp(final Long id, final String filename, 
			final Date date, final String title, final String extension) {
		Document document = new DocumentImpl();
		document.setId(id);
		document.setFilename(filename);
		document.setDate(date);
		document.setTitle(title);
		document.setFileExtension(extension);
		return document;
	}
			
}
