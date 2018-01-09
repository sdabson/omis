package omis.web.http;

import java.nio.charset.Charset;

import omis.report.ReportFormat;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/** Factory for Spring HTTP headers.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 20, 2014)
 * @since OMIS 3.0 */
public final class SpringHeadersFactory {
	
	private SpringHeadersFactory() {
		
	}

	/** Create a basic authentication headers.
	 * @param username username.
	 * @param password password.
	 * @return HTTP headers. */
	public static HttpHeaders createBasicAuthenticationHeaders(
					final String username, final String password) {
	    final HttpHeaders headers =  new HttpHeaders() {
	    	private static final long serialVersionUID = 1L;
	    	{
	            final String auth = username + ":" + password;
	            final byte[] encodedAuth = Base64.encodeBase64(
	                auth.getBytes(Charset.forName("US-ASCII")));
	            final String authHeader = "Basic " + new String(encodedAuth);
	            this.set("Authorization", authHeader);
	    	}
	    };
	    return headers;
	}

	/** Creates report response headers.
	 * @param reportFormat - report format.
	 * @return http headers. */
	public static HttpHeaders createReportResponseHeaders(
					final ReportFormat reportFormat) {
		final HttpHeaders headers =  new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				this.setContentType(MediaType.parseMediaType(
									reportFormat.getContentType()));
			} };
		return headers;
	}
}