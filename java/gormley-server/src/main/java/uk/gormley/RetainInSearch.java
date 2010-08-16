/**
 * 
 */
package uk.gormley;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author bryan
 * Determines if a field is to be retained or stripped by the search method.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface RetainInSearch {

	
}
