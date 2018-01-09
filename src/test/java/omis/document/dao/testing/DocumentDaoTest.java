package omis.document.dao.testing;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import omis.audit.AuditComponentRetriever;
import omis.audit.domain.CreationSignature;
import omis.audit.domain.UpdateSignature;
import omis.document.dao.DocumentDao;
import omis.document.domain.Document;
import omis.document.util.MockDocumentFactory;
import omis.testng.AbstractHibernateTransactionalTestNGSpringContextTests;

/** Tests for data access of documents.
 * @author Ryan JOhns
 * @author Stephen Abson
 * @version 0.1.1 (May 9, 2017)
 * @since OMIS 3.0 */
public class DocumentDaoTest
	extends AbstractHibernateTransactionalTestNGSpringContextTests {

	@Autowired
	private DocumentDao documentDao;
	
	@Autowired
	private AuditComponentRetriever auditComponentRetriever;
	
	/** Test document persistence. */
	@Test
	public void testPersistence() {
		Document x = MockDocumentFactory.mockUp(
				MockDocumentFactory.DocValues.X);
		
		// Sets ID to null - otherwise INSERT will not be invoked. This should
		// be revisited as it doesn't test full functionality of DAO - SA
		x.setId(null);
		x.setCreationSignature(this.createSampleCreationSignature());
		x.setUpdateSignature(this.createSampleUpdateSignature());
		
	}
	
	/** Tests find methods. */
	@Test(dependsOnMethods = {"testPersistence"})
	public void testFind() {
		Document x = MockDocumentFactory.mockUp(
				MockDocumentFactory.DocValues.X);
		
		// Sets ID to null - otherwise INSERT will not be made. This should
		// be revisited as it doesn't test full functionality of DAO - SA
		x.setId(null);
		x.setCreationSignature(this.createSampleCreationSignature());
		x.setUpdateSignature(this.createSampleUpdateSignature());
		x = this.documentDao.makePersistent(x);
		Long id = x.getId();
		List<Document> documents = this.documentDao.findByFileName(
				MockDocumentFactory.DocValues.X.getFilename());
		assert documents.size() == 1;
		documents = this.documentDao.findByFileNameExcluding(
				MockDocumentFactory.DocValues.X.getFilename(), x);
		assert documents.size() == 0;
		assert this.documentDao.findById(id, true) != null;
	}
	
	/** Test make transient. */
	@Test(dependsOnMethods = {"testFind"})
	public void testTransience() {
		Document x = MockDocumentFactory.mockUp(
				MockDocumentFactory.DocValues.X);
		
		// Sets ID to null - otherwise INSERT will not be made. This should
		// be revisited as it doesn't test full functionality of DAO - SA
		x.setId(null);
		x.setCreationSignature(this.createSampleCreationSignature());
		x.setUpdateSignature(this.createSampleUpdateSignature());
		x = this.documentDao.makePersistent(x);
		this.documentDao.makeTransient(x);
	}
	
	/** Test find after removal. */
	@Test(
			dependsOnMethods = {"testTransience"},
			expectedExceptions = {ObjectNotFoundException.class})
	public void testFindAfterRemove() {
		Document x = MockDocumentFactory.mockUp(
				MockDocumentFactory.DocValues.X);
		
		// Sets ID to null - otherwise INSERT will not be made. This should
		// be revisited as it doesn't test full functionality of DAO - SA
		x.setId(null);
		x.setCreationSignature(this.createSampleCreationSignature());
		x.setUpdateSignature(this.createSampleUpdateSignature());
		x = this.documentDao.makePersistent(x);
		Long id = x.getId();
		this.documentDao.makeTransient(x);
		
		// This should throw unchecked ObjectNotFoundException - this is an
		// Hibernate specific exception, this class should be renamed and
		// repackaged to indicate Hibernate specificity or this test should
		// be reformulated - SA
		this.documentDao.findById(id, true);
	}
	
	// Returns a new creation signature
	private CreationSignature createSampleCreationSignature() {
		return new CreationSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
	
	// Returns a new update signature
	private UpdateSignature createSampleUpdateSignature() {
		return new UpdateSignature(
				this.auditComponentRetriever.retrieveUserAccount(),
				this.auditComponentRetriever.retrieveDate());
	}
}
