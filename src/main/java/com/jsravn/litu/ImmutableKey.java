package com.jsravn.litu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * General purpose key container for maps and similar.
 * <p>
 * Useful when creating ad-hoc mappings and caches.
 * <p>
 * ImmutableKey objects are thread safe.
 */
public final class ImmutableKey {
    private final List<?> delegate;

    private ImmutableKey(List<?> delegate) {
        this.delegate = delegate;
    }

    public static ImmutableKey of() {
        return new ImmutableKey(Collections.emptyList());
    }

    public static ImmutableKey of(Object object) {
        return new ImmutableKey(Collections.singletonList(object));
    }

    public static ImmutableKey of(Object first, Object second, Object... rest) {
        int size = 2 + rest.length;
        List<Object> objects = new ArrayList<Object>(size);
        objects.add(first);
        objects.add(second);
        Collections.addAll(objects, rest);
        return new ImmutableKey(objects);
    }

    public static ImmutableKey copyOf(Collection<?> objects) {
        List<Object> copy = new ArrayList<Object>(objects);
        return new ImmutableKey(copy);
    }

    private int cachedHashCode;

    @Override
    public int hashCode() {
        // We avoid locking on purpose here to avoid contention, optimizing
        // for the single threaded use case.
        // The consequence is we may calculate hashcode multiple times if comparing
        // keys concurrently.
        if (cachedHashCode == 0) {
            cachedHashCode = delegate.hashCode();
        }
        return cachedHashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof ImmutableKey)) return false;

        ImmutableKey other = (ImmutableKey) obj;
        return hashCode() == other.hashCode()
                && delegate.equals(other.delegate);
    }
}
