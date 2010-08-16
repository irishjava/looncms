package uk.mafu.kwa.front;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.caucho.hessian.client.HessianProxyFactory;

public class LocalKwaServiceIntegrationTest extends AbstractKwaServiceImplTest{

	private static KwaService service;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String url = "http://localhost:8666/kwa-server/loon/kwaService";
		HessianProxyFactory factory = new HessianProxyFactory();
		service = (KwaService) factory.create(KwaService.class, url);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	@Override
	public KwaService fetchService() {
		return service;
	}
 

}