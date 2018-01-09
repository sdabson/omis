package omis.report.impl.spring.ws;

import java.util.Map;

import omis.report.ReportFormat;
import omis.report.ReportRunner;
import omis.web.http.SpringHeadersFactory;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/** Jasperserver webservice client report runner implementation.
 * @author Ryan Johns
 * @version 0.1.0 (Mar 24, 2014)
 * @since OMIS 3.0 */
public class JasperServerClientReportRunnerImpl implements ReportRunner {
	private final String reportServerUrl;
	private final String username;
	private final String password;

	/** Constructor.
	 * @param reportServerUrl url to report server.
	 * @param username username for report server.
	 * @param password password for report server. */
	public JasperServerClientReportRunnerImpl(final String reportServerUrl,
			final String username, final String password) {
		this.reportServerUrl = reportServerUrl;
		this.username = username;
		this.password = password;
	}

	/** {@inheritDoc} */
	@Override
	public byte[] runReport(final String report,
			final Map<String, String> parameters,
			final ReportFormat format) {
		final String url = this.reportServerUrl + report + "."
			+ format.getName() + this.parameterizeUrl(parameters);

		final RestTemplate restTemplate = new RestTemplate();

		final HttpHeaders headers = SpringHeadersFactory
				.createBasicAuthenticationHeaders(this.username, this.password);
		final HttpEntity<Map<String, String>> httpEntity =
				new HttpEntity<Map<String, String>>(headers);

        final ResponseEntity<byte[]> response = restTemplate.exchange(url,
        		HttpMethod.GET, httpEntity, byte[].class);

        return response.getBody();
	}

	// parameterizes the url. */
	private String parameterizeUrl(final Map<String, String> map) {
		String paramOperator = "?";
		String result = "";
		for (final String key: map.keySet()) {
			result += paramOperator + key + "=" + map.get(key);
			paramOperator = "&";
		}
		return result;
	}
}
