package com.jsravn.litu;

/**
 * Functions for dealing with some of the ugliness of Java generics.
 */
public class Generics {
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
