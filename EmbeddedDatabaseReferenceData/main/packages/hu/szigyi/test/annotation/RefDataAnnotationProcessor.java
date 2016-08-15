package hu.szigyi.test.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RefDataAnnotationProcessor {

	public <T> List<String> process(Class<T> clazz) {
		List<String> refs = new ArrayList<>();
		
		Method[] methods = clazz.getClass().getMethods();

        for (Method method : methods) {
        	RefData ref = method.getAnnotation(RefData.class);
            if (ref != null) {
                String value = ref.value();
                refs.add(value);
            }
        }
		
		return refs;
	}
}
