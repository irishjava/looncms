package uk.mafu.loon.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import junit.framework.JUnit4TestAdapter;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import uk.mafu.loon.test.domain.DummyClient;
import uk.mafu.loon.test.domain.DummyProject;

public class AdminServiceImplHsqlDBIntegrationTest {
	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(AdminServiceImplHsqlDBIntegrationTest.class));
	}

	private static AdminService adminService;
	@SuppressWarnings("unused")
	private static DataService dataService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	@SuppressWarnings("unused")
	public static void setUpBeforeClass() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		PropertyPlaceholderConfigurer placeholder = new PropertyPlaceholderConfigurer();
		ClassPathResource r = new ClassPathResource("springtest.properties");
		Properties properties = new Properties();
		properties.load(r.getInputStream());
		placeholder.setProperties(properties);
		context.addBeanFactoryPostProcessor(placeholder);
		context.setConfigLocation("testcontext.xml");
		context.refresh();
		adminService = (AdminService) context.getBean("adminService", uk.mafu.loon.services.AdminService.class);
		dataService = (DataService) context.getBean("dataService", uk.mafu.loon.services.DataService.class);
		System.out.println("setUpBeforeClass");
		List<DummyProject> dummyProjects = new ArrayList<DummyProject>();
		for (int i = 0; i < 10; i++) {
			DummyProject p = new DummyProject();
			p.setTitle("title" + i);
			DummyProject save = (DummyProject) adminService.save(p);
			for (int j = 0; j < 5; j++) {
				DummyClient c = new DummyClient();
				c.setTitle("title" + j);
				DummyClient savedClient = (DummyClient) adminService.save(c);
			}
		}
	}

	@Test
	public final void testRemove() {}

	@Test
	public final void testLoad() {}

	@Test
	public final void testLoadOneToOne() {}

	@SuppressWarnings("unchecked")
	@Test
	public final void testGetAll() {
		List<DummyProject> all = (List<DummyProject>) adminService.getAll(DummyProject.class.getName(), new String[] {
				"pk", "title" });
		for (DummyProject dummyProject : all) {
			System.err.println(dummyProject);
		}
	}

	@Test
	public final void testSave() {}

	@Test
	public final void testIsSimple() {}

	@Test
	public final void testSaveOneToOne() {}

	@Test
	public final void testLoadOneToMany() {}

	@Test
	public final void testSaveOneToMany() {}

	@Test
	public final void testRemoveImage() {}

	@Test
	public final void testRemovePdf() {}

	@Test
	public final void testRemoveVideo() {}

	@Test
	public final void testLoadImage() {}

	@Test
	public final void testSaveSingleLink() {}

	@Test
	public final void testDeleteSingleLink() {}

	@Test
	public final void testSaveOneToManyImages() {}

	@Test
	public final void testLoadPdf() {}

	@Test
	public final void testLoadVideo() {}

	@Test
	public final void testSaveOneToManyPdfs() {}

	@Test
	public final void testSaveOneToManyVideos() {}
}
