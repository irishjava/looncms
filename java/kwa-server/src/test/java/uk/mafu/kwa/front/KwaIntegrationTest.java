package uk.mafu.kwa.front;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import uk.mafu.loon.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager",defaultRollback=true)
@Transactional
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class KwaIntegrationTest extends AbstractKwaServiceImplTest{

	@Autowired(required = true)
	private KwaService service;
	
	@SuppressWarnings("unused")
	@Autowired(required = true)
	@Qualifier(value="adminService")
	private AdminService adminService;
	
	@SuppressWarnings("unused")
	@Autowired(required = true)
	private JpaTransactionManager txManager;

	@Autowired(required = true)
	EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unused")
	@Autowired(required = true)
	private DataSource datasource;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		 
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		 
	}
	
	@Before
	public void onSetUpInTransaction() throws Exception {
//		Website w = new Website();
//		String pk = "32323232323232";
//		w.setPk(pk);
//		adminService.save(w);
	}

	public void setService(KwaService service) {
		this.service = service;
	}

	@Override
	public KwaService fetchService() {
		return service;
	}

	// @After
	// public void after() throws Exception {
	// System.err.println("AFTER!!!!!!!!!!!!!!");
	// txManager.rollback(new SimpleTransactionStatus());
	// }
	
}