package uk.mafu.loon.services;

import static org.junit.Assert.assertNotNull;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.EntityManager;

import org.easymock.IAnswer;
import org.easymock.IArgumentMatcher;
import org.easymock.classextension.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.orm.jpa.JpaTemplate;

public class AdminServiceImplMockTest {

	public static class IsClass implements IArgumentMatcher, Serializable {

		private static final long serialVersionUID = 1094930851962278376L;

		private final Class<?> expected;

		public IsClass(Class<?> expected) {
			this.expected = expected;
		}

		public boolean matches(Object actual) {
			boolean instance = expected.isInstance((Class<?>) actual)
					|| expected.equals((Class<?>) actual);
			System.err.println(instance);
			return instance;
		}

		public void appendTo(StringBuffer buffer) {
			buffer.append("same(");
			appendQuoting(buffer);
			buffer.append(expected);
			appendQuoting(buffer);
			buffer.append(")");
		}

		private void appendQuoting(StringBuffer buffer) {
			buffer.append("\"");
		}
	}

	private static AdminServiceImpl adminServiceImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		// entityManager.persist(EasyMock.isA(Serializable.class));
		// org.easymock.EasyMock.expectLastCall().andAnswer(new
		// IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();

		// entityManager.find(org.easymock.EasyMock.isA(Class.class), EasyMock
		// .isNull());
		//
		// org.easymock.EasyMock.expectLastCall().andAnswer(new
		// IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();
		//
		// entityManager.find(EasyMock.isA(Class.class), EasyMock
		// .isA(String.class));
		// org.easymock.EasyMock.expectLastCall().andAnswer(new
		// IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();
		//
		// entityManager.find(EasyMock.isA(NumericEntity.class.getClass()),
		// EasyMock
		// .isA(String.class));
		// org.easymock.EasyMock.expectLastCall().andAnswer(new
		// IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();
		//
		// entityManager.find(EasyMock.isA(Class.class), EasyMock
		// .isA(Integer.class));
		// org.easymock.EasyMock.expectLastCall().andAnswer(new
		// IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();

		AdminServiceImplMockTest.adminServiceImpl = createMockedAdminService(createMockJpaTemplate(createMockEntityManager()));
		System.err.println();
		// EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// throw new RuntimeException("no fucking way");
		//
		// // Object[] args = org.easymock.EasyMock.getCurrentArguments();
		// // return ((Class)args[0]).newInstance();
		// }
		// }).anyTimes();

		// UUIDEntity simpleEntity = new UUIDEntity();
		// simpleEntity.setTitle("test entity");
		// entityManager.persist(simpleEntity);
		// adminServiceImpl.setEntityManager(entityManager);
	}

	private static AdminServiceImpl createMockedAdminService(
			JpaTemplate jpaTemplate) throws NoSuchMethodException {
		// Method method = AdminServiceImpl.class.getMethod(
		// "internalCreateJpaTemplate", EntityManagerFactory.class);
		// AdminServiceImpl adminServiceImpl = EasyMock.createMock(
		// AdminServiceImpl.class, method);
		// EasyMock.expect(
		// adminServiceImpl.internalCreateJpaTemplate(EasyMock
		// .isA(EntityManagerFactory.class))).andAnswer(
		// new IAnswer<? extends JpaTemplate>() {
		// public JpaTemplate answer() throws Throwable {
		// Object[] args = org.easymock.EasyMock
		// .getCurrentArguments();
		// // return ((Class)args[0]).newInstance();
		// throw new UnsupportedOperationException(
		// "fuck you !!!");
		// }
		// });
		// EasyMock.replay(adminServiceImpl);
		AdminServiceImpl i = new AdminServiceImpl();
		i.setJpaTemplate(jpaTemplate);
		return i;
	}

	private static JpaTemplate createMockJpaTemplate(EntityManager entityManager) {
		JpaTemplate jpaTemplate = new JpaTemplate();// EasyMock.createMock(JpaTemplate.class);
		jpaTemplate.setExposeNativeEntityManager(true);
		jpaTemplate.setEntityManager(entityManager);

		// jpaTemplate.execute(EasyMock.isA(JpaCallback.class));
		// EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
		// public Object answer() throws Throwable {
		// Object[] args = EasyMock.getCurrentArguments();
		// if (args[0] == null) {
		// throw new UnsupportedOperationException(
		// "asked to persist a null object");
		// }
		// return args[0];
		// }
		// }).anyTimes();

		// EasyMock.replay(jpaTemplate);
		return jpaTemplate;
	}

	private static EntityManager createMockEntityManager() {
		@SuppressWarnings("unused")
		Class<NumericEntity> ne = NumericEntity.class;
		EntityManager entityManager = EasyMock.createMock(EntityManager.class);
		EasyMock.expect(
				entityManager.find(isClass(Class.class), EasyMock.isNull()))
				.andAnswer(new IAnswer<Object>() {
					public Object answer() throws Throwable {
						throw new IllegalArgumentException(
								"id to load is required for loading");
					}
				}).anyTimes();
		EasyMock.expect(
				entityManager.find(isClass(NumericEntity.class), EasyMock
						.isA(Integer.class))).andAnswer(
				new IAnswer<NumericEntity>() {
					public NumericEntity answer() throws Throwable {
						Object[] args = org.easymock.EasyMock
								.getCurrentArguments();
						NumericEntity newInstance = (NumericEntity) ((Class<?>) args[0])
								.newInstance();
						newInstance.setPk((Integer) args[1]);
						newInstance.setTitle("random" + new Date().toString());
						return newInstance;
					}
				}).anyTimes();
		EasyMock.expect(
				entityManager.find(isClass(UUIDEntity.class), EasyMock
						.isA(String.class))).andAnswer(
				new IAnswer<UUIDEntity>() {
					public UUIDEntity answer() throws Throwable {
						Object[] args = org.easymock.EasyMock
								.getCurrentArguments();
						UUIDEntity newInstance = (UUIDEntity) ((Class<?>) args[0])
								.newInstance();
						newInstance.setPk((String) args[1]);
						newInstance.setTitle("random" + new Date().toString());
						return newInstance;
					}
				}).anyTimes();

		entityManager.persist(EasyMock.isA(Object.class));
		EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
			public Object answer() throws Throwable {
				Object[] args = org.easymock.EasyMock.getCurrentArguments();

				Class<? extends Object> class1 = args[0].getClass();
				
				Field field = class1.getDeclaredField("pk");
				field.setAccessible(true);
				Object object = field.get(args[0]);
				if (object == null) {
					throw new IllegalArgumentException("pk is not set");
				}

				// UUIDEntity newInstance = (UUIDEntity) ((Class<?>) args[0])
				// .newInstance();
				// newInstance.setPk((String) args[1]);
				// newInstance.setTitle("random" + new Date().toString());
				return null;
			}
		}).anyTimes();

		entityManager.merge(EasyMock.isA(UUIDEntity.class));
		EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
			public Object answer() throws Throwable {
				@SuppressWarnings("unused")
				Object[] args = org.easymock.EasyMock.getCurrentArguments();
				// UUIDEntity newInstance = (UUIDEntity) ((Class<?>) args[0])
				// .newInstance();
				// newInstance.setPk((String) args[1]);
				// newInstance.setTitle("random" + new Date().toString());
				return null;
			}
		}).anyTimes();

		entityManager.flush();
		EasyMock.expectLastCall().anyTimes();

		entityManager.clear();
		org.easymock.EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(entityManager);
		EasyMock.verify(entityManager);
		return entityManager;
	}

	private static Class<Object> isClass(Class<?> clazz) {
		AdminServiceImplMockTest.IsClass matcher = new IsClass(clazz);
		EasyMock.reportMatcher(matcher);
		return null;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin() {

	}

	@Test
	public void testNonce() {

	}

	@Test
	public void testRemove() {

	}

	@Test
	public void testLoad() {
		Object load = adminServiceImpl.load(NumericEntity.class.getName(), 14);
		assertNotNull(load);
	}

	@Test
	public void testLoadOneToOne() {
		// adminServiceImpl.loadOneToOne(uuid, child_clazz, relationship_name,
		// parentId, fields, loadOptions)
	}

	@Test
	public void testGetAll() {

	}

	@Test
	public void testSave() {
		UUIDEntity e = new UUIDEntity();
		e.setTitle("test");
		adminServiceImpl.save(e);

		UUIDEntity e2 = new UUIDEntity();
		e.setTitle("test2");
		e2.setPk("existing-key");
		adminServiceImpl.save(e2);
	}

	@Test
	public void testIsSimple() {

	}

	@Test
	public void testSaveOneToOne() {

	}

	@Test
	public void testLoadOneToMany() {

	}

	@Test
	public void testSaveOneToMany() {

	}

	@Test
	public void testSaveManyToMany() {

	}

	@Test
	public void testRemoveImage() {

	}

	@Test
	public void testRemovePdf() {

	}

	@Test
	public void testRemoveVideo() {

	}

	@Test
	public void testLoadImage() {

	}

	@Test
	public void testSaveSingleLink() {

	}

	@Test
	public void testDeleteSingleLink() {

	}

	@Test
	public void testSaveOneToManyImages() {

	}

	@Test
	public void testLoadPdf() {

	}

	@Test
	public void testLoadVideo() {

	}

	@Test
	public void testSaveOneToManyPdfs() {

	}

	@Test
	public void testSaveOneToManyVideos() {

	}

	@Test
	public void testUploadPdf() {

	}

	@Test
	public void testUploadVideo() {

	}

	@Test
	public void testLoadImageThumb() {

	}

	@Test
	public void testLoadManyToMany() {

	}

}
