package uk.gormley.maintenance;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;

import uk.gormley.domain.Art;
import uk.mafu.loon.domain.data.LoonImage;

public class ArtDateModification {
	private static Pattern p = Pattern
			.compile(".*?([0-9]{4})_([0-9]{3})(\\.|_).*");

	public static void main(String[] args) {

		// System.err.println(p.toString());

		ClassPathXmlApplicationContext c = new ClassPathXmlApplicationContext(
				"application-context.xml");
		EntityManagerFactory bean = (EntityManagerFactory) c.getBean(
				"entityManagerFactory", EntityManagerFactory.class);
		new JpaTemplate(bean).execute(new JpaCallback() {
			@SuppressWarnings("unchecked")
			public Object doInJpa(EntityManager em) throws PersistenceException {
				List<Art> arts = (List<Art>) em.createQuery("from Art as art")
						.getResultList();
				for (Art art : arts) {
					if (art.getImage() != null
							&& art.getImage().getImageId() > 0) {
						LoonImage image = em.find(LoonImage.class, art
								.getImage().getImageId());
						String filename = image.getFilename();

						String match = "";
						Matcher matcher = p.matcher(filename);
						if (matcher.matches()) {
							//System.out.println("got a match");
							if (matcher.groupCount()> 0) {
								//System.out.println("with groups count");
								for (int i = 1; i < matcher.groupCount(); i++) {
									if(i==1){
									match = match + matcher.group(i);
									}
									else {
										match = match + "," + matcher.group(i);
									} 
								}
							}
						}
						System.err.println("\"" + art.getPk() + "\":\""
								+ art.getTitle() + "\":\"" + art.getDate()
								+ "\":\"" + filename + "\":\"" + match + "\"");
					} else {
						System.err.println("no featured image set");
					}
					em.clear();
				}
				return null;
			}
		});
	}
}