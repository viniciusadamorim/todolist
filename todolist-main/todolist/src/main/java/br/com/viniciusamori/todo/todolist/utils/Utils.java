package br.com.viniciusamori.todo.todolist.utils;

import org.springframework.beans.*;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Utils {

    public static void copyNonNullProperties (Object object, Object target){
        BeanUtils.copyProperties(object, target, getNullPropertyNames(object));
    }

    public static String [] getNullPropertyNames (Object object){
        final BeanWrapper src = new BeanWrapperImpl(object);
        PropertyDescriptor [] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd: pds ) {
           Object srcValue = src.getPropertyValue(pd.getName());
           if(srcValue == null){
               emptyNames.add(pd.getName());
           }
        }
        String [] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
