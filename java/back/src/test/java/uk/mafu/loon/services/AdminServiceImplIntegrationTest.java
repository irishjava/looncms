package uk.mafu.loon.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import junit.framework.JUnit4TestAdapter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.domain.data.LoonVideo;
import com.caucho.hessian.client.HessianProxyFactory;

public class AdminServiceImplIntegrationTest {
	private static String url = "http://localhost:8666/epr-server/loon/adminService";
	// private static String url =
	// "http://www.mafunet.com:10000/epr-server-0.0.1-SNAPSHOT/loon/eprService";
	private static AdminService adminService;

	public static void main(String[] args) {
		junit.textui.TestRunner.run(new JUnit4TestAdapter(AdminServiceImplIntegrationTest.class));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setUser("epr-admin");
		factory.setPassword("epr-admin");
		adminService = (AdminService) factory.create(AdminService.class, url);
	}

	@Test
	public final void testRemove() {}

	@Test
	public final void testLoad() {}

	@Test
	public final void testLoadOneToOne() {}

	@Test
	public final void testGetAll() {}

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

	@Test
	public final void testUploadPdf() throws IOException {
		byte[] bytes = getBytesFromClassPathResource("rs232.pdf");
		LoonPdf p = new LoonPdf();
		p.setFilename("rs232.pdf");
		p.setData(bytes);
		adminService.uploadPdf(p);
	}

	private byte[] getBytesFromClassPathResource(String string) {
		try {
			ClassPathResource pdf = new ClassPathResource(string);
			File file;
			file = pdf.getFile();
			String filename = file.getAbsolutePath();
			System.err.println(filename);
			FileInputStream openInputStream = FileUtils.openInputStream(file);
			byte[] byteArray = IOUtils.toByteArray(openInputStream);
			System.err.println(byteArray.length);
			openInputStream.close();
			return byteArray;
		} catch (IOException e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Test
	public final void testUploadVideo() throws IOException {
		for (int i = 0; i < 100; i++) {
			byte[] bytes = getBytesFromClassPathResource("jedah.flv");
			LoonVideo p = new LoonVideo();
			p.setFilename("jedah.flv");
			p.setData(bytes);
			adminService.uploadVideo(p);
		}
	}
}
