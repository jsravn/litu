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

/**
 * Functions for dealing with some of the ugliness of Java generics.
 */
public final class Generics {
    private Generics() {}

    /**
     * This is semantically equivalent to casting, i.e. {@code T t = (T) object}.
     * However it provides a few extra benefits:
     * <ol>
     *     <li>Works where casts don't, such as with capture types (where the compiler would generate inconvertible
     *     type errors).</li>
     *     <li>Prevents compiler warnings on unchecked casts, instead of having to do @SuppressWarnings.</li>
     *     <li>Distinguishes itself from a safe up-cast.</li>
     * </ol>
     * It is suggested to use {@code ()} for safe casts, and only use this where the compiler
     * would generate a warning and you're sure it will always succeed. This is useful when
     * dealing with legacy APIs, particularly with collections (e.g. you need to convert
     * a {@code Collection<T>} to {@code Collection<S>}, where S is a superclass of T).
     * <p>
     * Ideally you should design any new code to avoid the need for unchecked casts (see Effective Java).
     * @param object to cast to T
     * @param <T> type to cast to, generally inferred by the compiler
     * @return object as T
     */
    @SuppressWarnings("unchecked")
    public static <T> T uncheckedCast(Object object) {
        return (T) object;
    }
}
