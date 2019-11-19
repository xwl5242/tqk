package com.quanchong.common.base;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.TypeMappingBuilder;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Base DO
 */
public class BaseEntity {

    private static final Mapper dozerMapper = DozerBeanMapperBuilder.buildDefault();

    public <T> T transforTo(Class<?> clazz){
        return (T)dozerMapper.map(this, clazz);
    }

    public <T> T transforFrom(Object o){
        return (T)dozerMapper.map(o, this.getClass());
    }


    /**
     * 转换为哪个类型的类对象
     * @param clazz 要转换成的类型
     * @param keys 被转换类(this:调用者)和转换类(形参clazz)的属性对(属性关系匹配)
     *      *             被转换类属性在前,要转换成的类属性在后,属性对交替出现,如：f1,f11,f2,f22,f3,f33...
     * @param <T> 要转换成的类
     * @return
     */
    public <T> T transforTo(Class<?> clazz, String ... keys){
        Assert.isTrue(keys.length % 2 != 0, "keys(被转换类和要转换成类的属性对)不是偶数个,不能完全匹配！");
        // 构建mapper,设置转换关系
        final Class thisClazz = this.getClass();
        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingBuilder(new BeanMappingBuilder() {
                    @Override
                    protected void configure() {
                        TypeMappingBuilder tmb = mapping(thisClazz, clazz);
                        for(int i=0;i<keys.length;i+=2){
                            tmb.fields(keys[i], keys[i+1]);
                        }
                    }
                }).build();
        return (T) mapper.map(this, clazz);
    }

    /**
     * 从哪里转换
     * @param o 要转换成的类
     * @param keys 被转换类(this:调用者)和转换类(形参Object)的属性对(属性关系匹配)
     *             被转换类属性在前,要转换成的类属性在后,属性对交替出现,如：f1,f11,f2,f22,f3,f33...
     * @param <T> 要转换成的类
     * @return
     */
    public <T> T transforFrom(Object o, String... keys){
        Assert.isTrue(keys.length % 2 == 0, "keys(被转换类和要转换成类的属性对)不是偶数个,不能完全匹配！");
        // 构建mapper,设置转换关系
        final Class clazz = this.getClass();
        Mapper mapper = DozerBeanMapperBuilder.create()
                .withMappingBuilder(new BeanMappingBuilder() {
                    @Override
                    protected void configure() {
                        TypeMappingBuilder tmb = mapping(clazz, o.getClass());
                        for(int i=0;i<keys.length;i+=2){
                            tmb.fields(keys[i], keys[i+1]);
                        }
                    }
                }).build();
        return (T)mapper.map(o, this.getClass());
    }

    /**
     * list 转换
     * @param list 要转换的list
     * @param <T>
     * @return
     */
    public <T> List<T> transforList(List<?> list){
        return list.stream()
                .map(n -> (T)transforFrom(n))
                .collect(Collectors.toList());
    }

    /**
     * list 转换
     * @param list 要转换的list
     * @param keys 属性对, 详情见transforFrom(o, keys)方法注释
     * @param <T>
     * @return
     */
    public <T> List<T> transforList(List<?> list, String... keys){
        return list.stream()
                .map(n -> (T)transforFrom(n, keys))
                .collect(Collectors.toList());
    }

}
