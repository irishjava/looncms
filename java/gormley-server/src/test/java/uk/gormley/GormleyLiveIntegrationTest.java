package uk.gormley;

import java.net.MalformedURLException;

import org.junit.BeforeClass;

import com.caucho.hessian.client.HessianProxyFactory;

public class GormleyLiveIntegrationTest extends AbstractGormeyIntegrationTest{

	private static GormleyService service;

	@BeforeClass
	public static void setup() throws MalformedURLException{
		String url = "http://www.mafunet.com/gormley-server-0.0.1-SNAPSHOT/loon/gormleyService";
		HessianProxyFactory factory = new HessianProxyFactory();
		service = (GormleyService) factory.create(GormleyService.class,url);
	}
	
	@Override
	public GormleyService getService() {
		return  service;
	}
}