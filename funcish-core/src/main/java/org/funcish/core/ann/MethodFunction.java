package org.funcish.core.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.funcish.core.fn.Function;

/**
 * Annotation used to "tag" a method as the one to be called when constructing 
 * a {@link Function} from an arbitrary object. Example:<p>
 *
<pre>
Object plus = new Object() {
	{@literal @MethodFunction} 
	public int add(int lhs, int rhs) {
		return lhs + rhs;
	}
};
Function{@code <?>} f = Functions.fn(plus);
</pre>
 * 
 * @author robin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MethodFunction {
}
