package omis.audit.testng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/**
 * Tests audit component retriever.
 *
 * @author Stephen Abson
 * @version 0.0.1
 * @since OMIS 3.0
 */
@Test(groups = {"audit"})
public class AuditComponentRetrieverTests
		extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	@Qualifier("auditComponentRetriever")
	private AuditComponentRetriever auditComponentRetriever;
	
	/**
	 * Tests that the audit user account exists.
	 *
	 * <p>The existence of an audit user account is required to run other
	 * unit tests.
	 */
	public void testAuditUserAccountExists() {
		assert auditComponentRetriever.retrieveUserAccount() != null
				: "Audit user account does not exist";
	}
}