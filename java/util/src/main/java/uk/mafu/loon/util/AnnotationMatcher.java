package uk.mafu.loon.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Helper class, given a class and a property, tells you if any of the listed
 * Annotations are present for the given property.
 * 
 * @author Bryan
 */
public class AnnotationMatcher {
	private boolean matched = false;
	 

	

	public AnnotationMatcher(Field field, Class<? extends Annotation> class1) {
		 if(field.getAnnotation(class1) != null){
			 matched = true;
		 }
	}

	public AnnotationMatcher(Field field,
			Class<? extends Annotation>[] complexAnnotations) {
		for (Class<? extends Annotation> class1 : complexAnnotations) {
			if(field.getAnnotation(class1) != null){
				matched = true;
				break;
			}
		}
	}

	public boolean isMatch() {
		return this.matched;
	}
}