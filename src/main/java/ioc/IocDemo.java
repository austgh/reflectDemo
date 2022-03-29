package ioc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.stream.Stream;

/**
 * @author think
 * @version 1.0
 * @date 2022/3/29 16:49
 */
public class IocDemo {
    /**
     * 模拟自动注入功能
     * 假设从GhController开始解析，跳过扫描的步骤
     */
    public static void main(String[] args){

        GhController ghController = new GhController();
        //如何完成自动注入呢
//        思路 判断上面的属性是否有自定义的注解，如果有自定义的注解，取出对应的属性，然后实例化对应的属性，调用属性的set方法(可以是Field.set方法或者set属性名)
        //如何获取ghController上的属性呢 获取属性，需要获取对应的Class 类 通过Class 类 获取对应上面的属性
        final Class<? extends GhController> controllerClass = ghController.getClass();
        //获取对应的属性
        final Field[] fields = controllerClass.getDeclaredFields();
        Stream.of(fields).forEach(field->{
            final GhAnnotation annotation = field.getAnnotation(GhAnnotation.class);
            if (annotation != null) {
                field.setAccessible(true);
                //获取属性的名字 类型等
                try {
                    Class<?> fieldClass = field.getType();
                    final Object ghService = fieldClass.newInstance();
                    inject(field,controllerClass,fieldClass,ghController,ghService);
                    //第二种思路 通过field.set方法 public void set(Object obj, Object value)
//                field.set(ghController, ghService);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(ghController.getGhService());
    }

    /**
     * 直接调用setGhService 方法，必须有setGhService 方法
     */
    public static void inject(Field  field, Class<? extends GhController> controllerClass, Class<?> fieldClass,
                              GhController ghController,Object ghService) throws Exception {
        String fieldName = field.getName();
        //set+fieldName 调用对应的方法  必须有setFiled 方法
        String name = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        // public Method getMethod(String name,Class<?>... parameterTypes)
        final Method method = controllerClass.getMethod(name, fieldClass);
        //public Object invoke(Object obj,Object... args) 第一个参数为调用者对象，第二个参数为参数
        method.invoke(ghController, ghService);
    }
}
