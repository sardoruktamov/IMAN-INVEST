package uz.java.springdatajpa.utils;

import lombok.extern.slf4j.Slf4j;
import uz.java.springdatajpa.dto.UsersDto;

import java.lang.reflect.Field;

@Slf4j
public class ReflectionUtils {
    public static String getValue(Object object, String fieldName){
        Class clazz = object.getClass();
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object).toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            log.error("Error while getting object's field by reflection: {}", e.getMessage());
            return "";
        }
    }
}
