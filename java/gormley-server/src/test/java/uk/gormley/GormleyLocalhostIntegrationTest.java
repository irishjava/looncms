package uk.gormley;

import java.net.MalformedURLException;

import org.junit.BeforeClass;

import com.caucho.hessian.client.HessianProxyFactory;

public class GormleyLocalhostIntegrationTest extends AbstractGormeyIntegrationTest{

	private static GormleyService service;

	@BeforeClass
	public static void setup() throws MalformedURLException{
		String url = "http://127.0.0.1:8666/gormley-server/loon/gormleyService";
		HessianProxyFactory factory = new HessianProxyFactory();
		service = (GormleyService) factory.create(GormleyService.class,url);
		
	}
	
	@Override
	public GormleyService getService() {
		return  service;
	}

	
}