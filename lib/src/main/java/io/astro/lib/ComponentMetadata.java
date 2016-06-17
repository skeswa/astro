package io.astro.lib;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author skeswa
 */
class ComponentMetadata {
    private Map<String, AttributeMetadata> attributeMetadataMap = new HashMap<>();

    ComponentMetadata(Class<? extends Component> componentType) {
        // Get all of the fields.
        final Field[] fields = componentType.getFields();
        for (Field field : fields) {
            // Filter for fields that have an attribute annotation.
            final AttributeAnnotation attributeAnnotationAnnotation = field.getAnnotation(AttributeAnnotation.class);
            if (attributeAnnotationAnnotation == null) {
                continue;
            }

            // Get the attribute name.
            String attributeName = attributeAnnotationAnnotation.value();
            if (attributeName == null) {
                attributeName = field.getName();
            }
            attributeName = AttributeUtil.toAttributeName(attributeName);

            // Get the attribute type.
            final Class<?> attributeType = field.getType();

            // Add the attribute to the map.
            attributeMetadataMap.put(attributeName, new AttributeMetadata(attributeName, attributeType, field));
        }
    }

    boolean attributeExists(String name) {
        return attributeMetadataMap.containsKey(name);
    }

    Class<?> getAttributeType(String name) {
        final AttributeMetadata attributeMetadata = attributeMetadataMap.get(name);
        return attributeMetadata != null ? attributeMetadata.getType() : null;
    }
}
