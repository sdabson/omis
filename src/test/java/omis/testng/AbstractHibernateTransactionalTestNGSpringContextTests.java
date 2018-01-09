package omis.testng;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Provides Spring transaction management and access to application context for
 * TestNG tests that use Hibernate persistence.
 * 
 * @author Stephen Abson
 * @version 0.1.1 (Nov 2, 2012)
 * @since OMIS 3.0
 */
@ContextConfiguration(
		locations = { "file:src/test/config/applicationContext-test.xml" },
		loader = GenericXmlContextLoader.class
)
public abstract class AbstractHibernateTransactionalTestNGSpringContextTests
		extends AbstractTransactionalTestNGSpringContextTests {

	// No additional functionality
}