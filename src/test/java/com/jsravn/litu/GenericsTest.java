package com.jsravn.litu;

import org.assertj.core.internal.Numbers;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

public class GenericsTest {
    @Test
    public void canDowncastGenericCollections() {
        Collection<Integer> integers = new ArrayList<Integer>();
        Integer integer = 5;
        integers.add(integer);

        // Can't do this - inconvertible types.
        // Collection<Number> numbers = (Collection<Number>) integers;
        Collection<Numbers> numbers = Generics.uncheckedCast(integers);

        assertThat(numbers).isEqualTo(integers);
    }

    @Test(expected = ClassCastException.class)
    public void shouldGetRuntimeExceptionIfIncompatibleTypes() {
        Integer i = 9;

        Double d = Generics.uncheckedCast(i);
    }
}