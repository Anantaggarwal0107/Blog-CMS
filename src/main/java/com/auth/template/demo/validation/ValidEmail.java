/*

    Copyright and credit goes to Eugen  https://github.com/eugenp


    https://github.com/Baeldung/spring-security-registration

 */

package com.auth.template.demo.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, FIELD, ANNOTATION_TYPE , PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

    String message() default "{validation.ValidEmail.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
