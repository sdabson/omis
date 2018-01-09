package omis.report.web.controller.delegate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import omis.report.ReportFormat;

/**
 * Report controller delegate.
 * 
 * @author Joel Norris
 * @version 0.1.0 (Sept 1, 2016)
 * @since OMIS 3.0
 */
public class ReportControllerDelegate {

	/* Constructors. */
	
	/**
	 * Instantiates a default instance of report controller delegate.
	 */
	public ReportControllerDelegate() {
		//Default constructor.
	}
	
	/* Delegate methods. */
	
	/**
	 * Constructs the response entity for a report document with the
	 * HTTP headers and content type for the specified report format.
	 * 
	 * @param doc report document
	 * @param format report format
	 * @return response entity
	 */
	public ResponseEntity<byte []>  constructReportResponseEntity(
			final byte[] doc, final ReportFormat format) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType(format.getContentType()));
		ResponseEntity<byte []> response
			= new ResponseEntity<byte[]>(doc, headers, HttpStatus.OK);
		return response;
	}
}