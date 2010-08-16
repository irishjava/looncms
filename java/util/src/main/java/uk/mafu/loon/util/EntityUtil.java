package uk.mafu.loon.util;

import java.lang.reflect.Field;

import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils.FieldFilter;

public class EntityUtil {
	public static interface EnhancedFieldCallback {
		public void doWith(Field field, Object target)
				throws IllegalArgumentException, IllegalAccessException;
	}

	/**
	 * Attempt to find a {@link Field field} on the supplied {@link Class} with
	 * the supplied <code>name</code>. Searches all superclasses up to
	 * {@link Object}.
	 * 
	 * @param clazz
	 *            the class to introspect
	 * @param name
	 *            the name of the field
	 * @return the corresponding Field object, or <code>null</code> if not found
	 */
	public static Field findField(Class<? extends Object> clazz, String name) {
		return findField(clazz, name, null);
	}

	/**
	 * Attempt to find a {@link Field field} on the supplied {@link Class} with
	 * the supplied <code>name</code> and/or {@link Class type}. Searches all
	 * superclasses up to {@link Object}.
	 * 
	 * @param clazz
	 *            the class to introspect
	 * @param name
	 *            the name of the field (may be <code>null</code> if type is
	 *            specified)
	 * @param type
	 *            the type of the field (may be <code>null</code> if name is
	 *            specified)
	 * @return the corresponding Field object, or <code>null</code> if not found
	 */
	public static Field findField(Class<? extends Object> clazz, String name,
			Class<? extends Object> type) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.isTrue(name != null || type != null,
				"Either name or type of the field must be specified");
		Class<? extends Object> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Field[] fields = searchType.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if ((name == null || name.equals(field.getName()))
						&& (type == null || type.equals(field.getType()))) {
					field.setAccessible(true);
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	public static void doWithFields(Object target, EnhancedFieldCallback fc,
			FieldFilter ff) throws IllegalArgumentException {
		Class<? extends Object> targetClass = target.getClass();
		// Keep backing up the inheritance hierarchy.
		do {
			// Copy each field declared on this class unless it's static or
			// file.
			Field[] fields = targetClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// Skip static and final fields.
				if (ff != null && !ff.matches(fields[i])) {
					continue;
				}
				try {
					fc.doWith(fields[i], target);
				} catch (IllegalAccessException ex) {
					throw new IllegalStateException(
							"Shouldn't be illegal to access field '"
									+ fields[i].getName() + "': " + ex);
				}
			}
			targetClass = targetClass.getSuperclass();
		} while (targetClass != null && targetClass != Object.class);
	}

	public static void generatePermalinkText(final Object o) {
		EnhancedFieldCallback fc = new EnhancedFieldCallback() {
			String pk = null;
			String titleOrName = null;
			String permalink = "";

			public void doWith(Field field, Object target)
					throws IllegalArgumentException, IllegalAccessException {
				if ("name".equals(field.getName())
						|| "title".equals(field.getName())
						|| "pk".equals(field.getName())) {
					field.setAccessible(true);
					// We gotta match... quick, check if it has the
					// @Column
					// annotation ...
					// Ok, we skip that for now .. lets just presume
					// ..
					Object identifier = field.get(o);

					// Should be an instance of String, but we need
					// to make
					// sure..
					// 
					// ----------------------------------------------------------
					// We removed the check that only set the value
					// if the
					// existing value was null, permalink field is
					// now updated
					// with every save
					// identifier != null &&
					if (identifier instanceof String) {
						titleOrName = (String) identifier;
					}
					if (identifier instanceof Number) {
						pk = ((Number) identifier).toString();
					}
				}
				if ("permalink".equals(field.getName())) {
					if ("permalink".equals(field.getName())) {
						field.setAccessible(true);
						if (pk != null) {
							permalink = pk + "-";
						}
						if (titleOrName != null) {
							permalink = permalink + titleOrName;
						}
						String lowerCase = permalink.toLowerCase();
						lowerCase.replaceAll("\\s$", "");
						String replaceAll = lowerCase.replaceAll("[^a-z0-9]",
								"-");
						field.set(o, replaceAll);
					}
				}
			}
		};
		// First pass, find the name/title and pk fields
		doWithFields(o, fc, null);
		// Second pass, set the permalink
		doWithFields(o, fc, null);
	}

	public static void setProperty(Object obj, String name, Object value) {
		Field findField = findField(obj.getClass(), name);
		try {
			findField.set(obj, value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		}

	}

	public static Object getProperty(Object find, String relationship_name) {
		Field findField = EntityUtil.findField(find.getClass(),
				relationship_name);
		try {
			if (findField == null) {
				throw new UnsupportedOperationException("Cannot fid field:'"
						+ relationship_name + "'");
			}
			return findField.get(find);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new UnsupportedOperationException(e);
		}
	}
}
