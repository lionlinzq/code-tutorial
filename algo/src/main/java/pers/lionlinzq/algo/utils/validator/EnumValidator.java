package pers.lionlinzq.algo.utils.validator;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class EnumValidator implements ConstraintValidator<EnumValid, Object>{

    private EnumValid enumValid;
    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.enumValid = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null){
            return true;
        }
        Set<Object> codes = getEnumCodeSet(enumValid.value());
        if(codes == null || codes.size() == 0){
            log.warn("枚举【{}】没有枚举值，请检查！", enumValid.value());
            return false;
        }
        if(!codes.iterator().next().getClass().equals(s.getClass())){
            log.warn("枚举值类型【{}】与被检验参数类型【{}】不一致，请检查！", codes.iterator().next().getClass(), s.getClass());
            return false;
        }
        return codes.contains(s);
    }

    public Set<Object> getEnumCodeSet(Class<? extends IEnum<?>> iEnumClass){
        Set<Object> codes = new HashSet<>();
        IEnum<?>[] enumConstants = iEnumClass.getEnumConstants();
        for(IEnum<?> iEnum : enumConstants){
            codes.add(iEnum.getCode());
        }
        return codes;
    }
}
