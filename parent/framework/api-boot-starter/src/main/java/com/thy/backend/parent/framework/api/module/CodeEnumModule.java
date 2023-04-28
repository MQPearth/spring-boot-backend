package com.thy.backend.parent.framework.api.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.thy.backend.parent.base.enums.CodeEnum;
import com.thy.backend.parent.framework.api.serializer.TypeEnumDeserializer;
import com.thy.backend.parent.framework.web.lang.CustomModule;

import java.io.IOException;

/**
 * <p>CodeEnumModule</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 14:44:30
 */
public class CodeEnumModule extends CustomModule {
    public CodeEnumModule() {
        var sm = new SimpleModule("codeEnumSimpleModule");
        // 自定义查找规则
        sm.setDeserializers(new SimpleDeserializers() {
            @Override
            public JsonDeserializer<?> findEnumDeserializer(Class<?> type, DeserializationConfig config,
                                                            BeanDescription beanDesc) throws JsonMappingException {
                var enumDeserializer = super.findEnumDeserializer(type, config, beanDesc);
                if (enumDeserializer != null) {
                    return enumDeserializer;
                }
                // 遍历枚举实现的接口, 查找反序列化器
                for (var typeInterface : type.getInterfaces()) {
                    enumDeserializer = this._classMappings.get(new ClassKey(typeInterface));
                    if (enumDeserializer != null) {
                        return enumDeserializer;
                    }
                }
                return null;
            }
        });

        sm.addDeserializer(CodeEnum.class, new TypeEnumDeserializer());

        sm.addSerializer(CodeEnum.class, new JsonSerializer<>() {
            @Override
            public void serialize(CodeEnum value, JsonGenerator gen, SerializerProvider serializers)
                    throws IOException {
                gen.writeStartObject();
                gen.writeNumberField(value.getCodePropertyName(), value.getCode());
                gen.writeStringField(value.getDescPropertyName(), value.getDesc());
                gen.writeEndObject();
            }

            @Override
            public void serializeWithType(CodeEnum value, JsonGenerator gen, SerializerProvider serializers,
                                          TypeSerializer typeSer) throws IOException {
                var typeIdDef = typeSer.writeTypePrefix(gen, typeSer.typeId(value, JsonToken.VALUE_STRING));
                serialize(value, gen, serializers);
                typeSer.writeTypeSuffix(gen, typeIdDef);
            }
        });
        this.simpleModule = sm;
    }
}
