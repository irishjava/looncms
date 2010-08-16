package uk.mafu.loon.services;

import java.util.List;

import uk.mafu.loon.domain.data.ImageLink;
import uk.mafu.loon.domain.data.LoonAudio;
import uk.mafu.loon.domain.data.LoonImage;
import uk.mafu.loon.domain.data.LoonPdf;
import uk.mafu.loon.domain.data.LoonVideo;
import uk.mafu.loon.domain.data.PdfLink;
import uk.mafu.loon.domain.data.VideoLink;
import uk.mafu.loon.dto.ImageThumb;
import uk.mafu.loon.dto.ManyToManyResult;
import uk.mafu.loon.dto.OneToManyResult;
import uk.mafu.loon.dto.OneToOneResult;

public interface AdminService {
	public void remove(String clazz, Object id);

	public boolean login(String username, String md5password);

	public String nonce();

	public void removeImage(Object pk);

	public void removeVideo(Object pk);

	public void removePdf(Object pk);

	public Object load(String clazz, Object pk);

	public LoonImage loadImage(Object pk);

	public ImageThumb loadImageThumb(Object pk);

	public LoonPdf loadPdf(Object pk);

	public LoonVideo loadVideo(Object pk);

	public OneToOneResult loadOneToOne(final String parent_clazz,
			final String child_clazz, final String relationship_name,
			final Object parentId, final String[] fields,
			final boolean loadOptions);

	public OneToManyResult loadOneToMany(String parent_clazz,
			String child_clazz, String relationship_name, Object parentId,
			String[] fields);

	public ManyToManyResult loadManyToMany(String parent_clazz,
			String child_clazz, String relationship_name, Object parentId,
			String[] fields);

	public List<? extends Object> getAll(String clazz, String[] fields);

	public Object save(Object o);

	public void saveOneToOne(String parent_class, String child_class,
			Object parent_pk, Object child_pk, String relationship);

	public void saveOneToMany(String parent_class, Object parentId,
			String relationship_name, List<Object> child_pks);

	public void saveManyToMany(String parent_class, Object parentId,
			String relationship_name, List<Object> child_pks);

	public void saveOneToManyImages(String parent_class, Object parentId,
			String relationship_name, List<ImageLink> children);

	public Object uploadPdf(LoonPdf p);

	public Object uploadVideo(LoonVideo v);
	
	public Object uploadAudio(LoonAudio v);
	

	public void saveOneToManyPdfs(String parent_class, Object parentId,
			String relationship_name, List<PdfLink> children);

	public void saveOneToManyVideos(String parent_class, Object parentId,
			String relationship_name, List<VideoLink> children);

	public void saveSingleLink(Object child, String child_clazz,
			String parent_clazz, Object parentId, String relationship);

	public void deleteSingleLink(String parent_clazz, Object parentId,
			String relationship);
}
