package com.thy.backend.parent.framework.api.config;

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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.thy.backend.parent.base.enums.CodeEnum;
import com.thy.backend.parent.base.lang.CustomModule;
import com.thy.backend.parent.framework.api.serializer.TypeEnumDeserializer;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>CodeEnumModule</p>
 *
 * @author zzx
 * @version 1.0
 * @date 2023/4/28 11:03:55
 */
public class CustomModuleConfig {

    @Bean("javaTimeModule")
    public CustomModule javaTimeModule() {
        JavaTimeModule timeModule = new JavaTimeModule();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(pattern));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(pattern));
        return CustomModule.build(timeModule);
    }

    @Bean("codeEnumModule")
    public CustomModule codeEnumModule() {
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
        return CustomModule.build(sm);
    }
}
