package com.epam.homework3.controller.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateInConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface DateConstraint {
    String message() default "DateIn can`t be less than current";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
