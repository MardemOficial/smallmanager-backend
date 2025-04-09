package br.com.SmallManager.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DTOMapperUtil {

    public static <S, T> T mapToEntity(S source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter DTO para entidade: " + e.getMessage(), e);
        }
    }

    public static <T> T mapToDTO(Object source, Class<T> targetClass) {
        try {
            RecordComponent[] components = targetClass.getRecordComponents();
            List<Object> values = new ArrayList<>();

            for (RecordComponent compoent: components){
                String fieldName = compoent.getName();
                var field = source.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                values.add(field.get(source));
            }

            Constructor<T> constructor = targetClass.getDeclaredConstructor(
                    Arrays.stream(components).map(RecordComponent::getType).toArray(Class[]::new)
            );

            return constructor.newInstance(values.toArray());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao converter entidade para DTO: " + e.getMessage(), e);
        }
    }
}
