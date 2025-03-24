package pers.lionlinzq.algo.utils.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {EnumValidator.class})
@Documented
public @interface EnumValid {
/**
 * 枚举的类型
 */
Class< ? extends IEnum<?>> value();

        /**
         * 错误消息
         *
         * @return
         */
        String message();

        Class<?>[] groups() default { };

        Class<? extends Payload>[] payload() default { };

}
