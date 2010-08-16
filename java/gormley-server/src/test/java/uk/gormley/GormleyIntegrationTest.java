package uk.gormley;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import junit.framework.Assert;
import junit.framework.JUnit4TestAdapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import uk.gormley.domain.Show;
import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.services.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager")
@Transactional
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class GormleyIntegrationTest extends AbstractGormeyIntegrationTest {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(
				GormleyIntegrationTest.class));
	}

	@Autowired(required = true)
	private GormleyService service;

	@Autowired(required = true)
	@Qualifier(value = "adminService")
	private AdminService adminService;

	@SuppressWarnings("unused")
	@Autowired(required = true)
	private JpaTransactionManager txManager;

	@Autowired(required = true)
	EntityManagerFactory entityManagerFactory;

	@SuppressWarnings("unused")
	@Autowired(required = true)
	private DataSource datasource;

	public void setGormleyService(GormleyService gormleyService) {
		this.service = gormleyService;
	}

	@Before
	public void onSetUpInTransaction() throws Exception {
		// Website w = new Website();
		// String pk = "32323232323232";
		// w.setPk(pk);
		// adminService.save(w);
	}

	@Override
	public GormleyService getService() {
		return service;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testShowHasImages() {
		Show s = new Show();
		s.setTitle("test-show");
		ImageLink l = new ImageLink();
		l = (ImageLink) adminService.save(l);
		l.setTitle("test");
		List list = new ArrayList();
		list.add(l);
		Show savedShow  = (Show)adminService.save(s);
		String canonicalName = Show.class.getCanonicalName();
		System.err.println(canonicalName);
		
		String savedpk = savedShow.getPk();
		adminService.saveOneToManyImages(canonicalName,savedpk,
				"images", list);
		Show fetchShow = getService().fetchShow(savedpk);
		Assert.assertEquals(1,fetchShow.getImages().size());
	}

	// @After
	// public void after() throws Exception {
	// System.err.println("AFTER!!!!!!!!!!!!!!");
	// txManager.rollback(new SimpleTransactionStatus());
	// }

}