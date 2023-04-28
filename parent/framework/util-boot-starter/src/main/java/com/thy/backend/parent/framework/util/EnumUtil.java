package com.thy.backend.parent.framework.util;

import com.thy.backend.parent.base.enums.CodeEnum;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author root
 */
public class EnumUtil {

    private static final Map<Class<? extends Enum<?>>, Map<Integer, ? extends Enum<? extends CodeEnum>>>
            CLASS_ENUM_MAP =
            new ConcurrentHashMap<>(16);

    @SuppressWarnings("unchecked")
    public static <E extends Enum<E> & CodeEnum> E match(Class<E> enumClass, Integer type) {
        var enumMap = CLASS_ENUM_MAP.get(enumClass);
        if (Objects.isNull(enumMap)) {
            var unmodifiableMap = Arrays.stream(enumClass.getEnumConstants())
                    .collect(Collectors.toUnmodifiableMap(CodeEnum::getCode, v -> v));
            CLASS_ENUM_MAP.putIfAbsent(enumClass, unmodifiableMap);
            return unmodifiableMap.get(type);
        }
        return (E) enumMap.get(type);
    }
}