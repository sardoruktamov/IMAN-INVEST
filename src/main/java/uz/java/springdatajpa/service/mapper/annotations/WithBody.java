package uz.java.springdatajpa.service.mapper.annotations;

import org.mapstruct.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Qualifier
@Target(ElementType.METHOD)
public @interface WithBody {
}
