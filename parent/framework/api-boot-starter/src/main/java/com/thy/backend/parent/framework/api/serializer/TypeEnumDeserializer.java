package com.thy.backend.parent.framework.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.thy.backend.parent.base.enums.CodeEnum;
import com.thy.backend.parent.framework.util.EnumUtil;

import java.io.IOException;

/**
 * @author root
 */
public class TypeEnumDeserializer extends StdDeserializer<CodeEnum> implements ContextualDeserializer {

    public TypeEnumDeserializer() {
        super((JavaType) null);
    }

    public TypeEnumDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {
        return new TypeEnumDeserializer(property.getType());
    }

    @Override
    @SuppressWarnings("all")
    public CodeEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return (CodeEnum) EnumUtil.match((Class) _valueClass, p.getIntValue());
    }
}