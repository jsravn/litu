/*
 * Copyright (c) 2015, James Ravn
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
