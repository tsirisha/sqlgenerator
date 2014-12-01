package com.sqlgen.annotations;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
 * Annotation class for Join
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.FIELD})
public @interface DbJoinTo {
	//public abstract Class[] value();
	//public String fieldName() default "";
	public String joinName();
	//public String referencedFieldName();
	//public String referencedEntityName();
}
