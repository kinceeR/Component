package com.xiaojinzi.component.impl.service;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xiaojinzi.component.service.IServiceLoad;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务的容器,使用这个服务容器你需要判断获取到的服务是否为空,对于使用者来说还是比较不方便的
 * 建议使用 Service 扩展的版本 RxService
 *
 * @author xiaojinzi 30212
 */
public class Service {

    private Service() {
    }

    /**
     * Service 的集合
     */
    private static Map<Class, IServiceLoad<?>> map = new HashMap<>();

    /**
     * 你可以注册一个服务,服务的初始化可以是 懒加载的
     *
     * @param tClass
     * @param iServiceLoad
     * @param <T>
     */
    public static <T> void register(@NonNull Class<T> tClass, @NonNull IServiceLoad<? extends T> iServiceLoad) {
        map.put(tClass, iServiceLoad);
    }

    @Nullable
    public static <T> T unregister(@NonNull Class<T> tClass) {
        return (T) map.remove(tClass);
    }

    @Nullable
    public static <T> T get(@NonNull Class<T> tClass) {
        IServiceLoad<?> serviceLoad = map.get(tClass);
        if (serviceLoad == null) {
            return null;
        } else {
            return (T) serviceLoad.get();
        }
    }

}
