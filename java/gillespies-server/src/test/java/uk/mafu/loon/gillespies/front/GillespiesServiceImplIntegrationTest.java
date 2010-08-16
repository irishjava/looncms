/**
 * 
 */
package uk.mafu.loon.gillespies.front;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @author bryan
 * 
 */
public class GillespiesServiceImplIntegrationTest extends AbstractGillespiesIntegrationTest{
	
	private static GillespiesService gillespiesService;

	public GillespiesServiceImplIntegrationTest() throws Throwable {
		//String url = "http://localhost:8666/gillespies-server/loon/gillespiesService";
		//String url = "http://www.mafunet.com:10000/gillespies-server-0.0.1-SNAPSHOT/loon/gillespiesService";
		String url = "http://www.mafunet.com/gillespies-server-0.0.1-SNAPSHOT/loon/gillespiesService";
		HessianProxyFactory factory = new HessianProxyFactory();
		gillespiesService = (GillespiesService) factory.create(GillespiesService.class, url);
	}

	
	@Override
	public GillespiesService getService() {
		return gillespiesService;
	}
}
