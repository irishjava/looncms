package uk.gormley;

import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import uk.gormley.domain.ArtGroup;
import uk.gormley.domain.Show;
import uk.mafu.loon.domain.data.ImageLink;

public class StripperUtilTest {

	@Test
	public void testStrip() {
		ArtGroup ag = new ArtGroup();
		ag.setPk("test");
		ag.setProgressImages(new ArrayList<ImageLink>());
		ag.setTitle("test");
		ag.setArtType("test");
		ag.setUrl1("test");
		ag.setArtSubType("test");
		StripperUtil.strip(ag);
		
		assertNotNull("pk",ag.getPk());
		assertNull("progress images",ag.getProgressImages());
		assertNotNull("title",ag.getTitle());
		assertNotNull("artType",ag.getArtType());
		assertNull("url",ag.getUrl1());
		assertNotNull("artSubType",ag.getArtSubType());
		
		Show show = new Show();
		show.setPermanent(true);
		StripperUtil.strip(show);
		assertEquals(true, show.isPermanent());
	}
}
