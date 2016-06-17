package io.astro.lib;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author skeswa
 */
class ComponentMetadataCache {
    private static final Map<Class<? extends Component>, ComponentMetadata> cache = new WeakHashMap<>();

    static ComponentMetadata get(Class<? extends Component> componentType) {
        ComponentMetadata metadata = cache.get(componentType);

        if (metadata == null) {
            metadata = new ComponentMetadata(componentType);
            cache.put(componentType, metadata);
        }

        return metadata;
    }
}
