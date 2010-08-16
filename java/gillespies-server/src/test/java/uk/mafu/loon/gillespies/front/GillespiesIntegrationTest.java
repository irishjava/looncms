package uk.mafu.loon.gillespies.front;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
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

import uk.mafu.domain.gillespies.Office;
import uk.mafu.loon.services.AdminService;

import com.caucho.hessian.io.HessianSerializerOutput;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "txManager",defaultRollback=true)
@Transactional
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class GillespiesIntegrationTest extends
		AbstractGillespiesIntegrationTest {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(
				GillespiesIntegrationTest.class));
	}

	@Autowired(required = true)
	private GillespiesService service;

	@SuppressWarnings("unused")
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

	public void setGillespiesService(GillespiesService gillespiesService) {
		this.service = gillespiesService;
	}
	
	private void hessianSerialize(List<Office> listOffices) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		HessianSerializerOutput hso = new HessianSerializerOutput(baos);
		try {
			hso.writeObject(listOffices);
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	/**
	 * Test method for {@link uk.mafu.loon.gillespies.front.getService()Impl#listOffices()}.
	 */
	@Test
	@Override
	public void testListOffices() {
		List<Office> listOffices = getService().listOffices();
		hessianSerialize(listOffices);
		assertTrue(listOffices.size() > 0);
	}

	@Before
	public void onSetUpInTransaction() throws Exception {
		// Website w = new Website();
		// String pk = "32323232323232";
		// w.setPk(pk);
		// adminService.save(w);
		//txManager.getDataSource().getConnection().setAutoCommit(false);
	}

	@Override
	public GillespiesService getService() {
		return service;
	}

	@After
	public void after() throws Exception {
		//txManager.getDataSource().getConnection().rollback();
	}
}