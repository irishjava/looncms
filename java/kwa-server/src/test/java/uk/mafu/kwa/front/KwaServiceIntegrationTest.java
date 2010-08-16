package uk.mafu.kwa.front;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.junit.AfterClass;
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
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class KwaServiceIntegrationTest extends AbstractKwaServiceImplTest{
	
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
//		String url = "http://localhost:8666/kwa-server/loon/kwaService";
//		HessianProxyFactory factory = new HessianProxyFactory();
//		service = (KwaService) factory.create(KwaService.class, url);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Override
	public KwaService fetchService() {
		return service;
	}
	
	
	
	
	
	
	
	
	
	
}









 







