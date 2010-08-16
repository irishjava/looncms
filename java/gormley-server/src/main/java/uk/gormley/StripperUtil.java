package uk.gormley;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.springframework.util.ReflectionUtils;

import uk.mafu.loon.util.EntityUtil;

public class StripperUtil {

	public static void strip(Object o) {
		EntityUtil.doWithFields(o, new EntityUtil.EnhancedFieldCallback() {
			public void doWith(Field field, Object target)
					throws IllegalArgumentException, IllegalAccessException {
				field.setAccessible(true);
				field.set(target, null);
			}
		}, new ReflectionUtils.FieldFilter() {
			public boolean matches(Field field) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(RetainInSearch.class)) {
					return false;
				}
				if (field.getType().isPrimitive()) {
					return false;
				}
				//Ignore static,native or final variables
				int modifiers = field.getModifiers();
				if (Modifier.isFinal(modifiers)
						|| Modifier.isStatic(modifiers)
						
						|| Modifier.isNative(modifiers)) {
					return false;
				}
				return true;
			}
		});
	}
}