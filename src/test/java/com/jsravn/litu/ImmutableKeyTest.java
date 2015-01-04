/*
 * Copyright (c) 2015 James Ravn
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */

package com.jsravn.litu;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImmutableKeyTest {
    private Object first = new Object();
    private Object second = new Object();
    private Object third = new Object();

    @Test
    public void emptyKeysShouldBeEquivalent() {
        ImmutableKey one = ImmutableKey.of();
        ImmutableKey two = ImmutableKey.of();

        assertKeysEqual(one, two);
    }

    @Test
    public void singletonKeysShouldBeEquivalent() {
        ImmutableKey one = ImmutableKey.of(first);
        ImmutableKey two = ImmutableKey.of(first);

        assertKeysEqual(one, two);
    }

    @Test
    public void nonSingleKeysShouldBeEquivalent() {
        ImmutableKey one = ImmutableKey.of(second, third, first);
        ImmutableKey two = ImmutableKey.of(second, third, first);

        assertKeysEqual(one, two);
    }

    @Test
    public void differentKeysShouldNotBeEqual() {
        ImmutableKey one = ImmutableKey.of(first);
        ImmutableKey two = ImmutableKey.of(second);

        assertKeysNotEqual(one, two);
    }

    @Test
    public void differentSizeKeysShouldNotBeEqual() {
        ImmutableKey one = ImmutableKey.of(first);
        ImmutableKey two = ImmutableKey.of(first, second);

        assertKeysNotEqual(one, two);
    }

    @Test
    public void differentOrderKeysShouldNotBeEqual() {
        ImmutableKey one = ImmutableKey.of(first, second, third);
        ImmutableKey two = ImmutableKey.of(first, third, second);

        assertKeysNotEqual(one, two);
    }

    private void assertKeysEqual(ImmutableKey one, ImmutableKey two) {
        // cover cached hashcode branch
        for (int i = 0; i < 2; i++) {
            assertThat(one).isEqualTo(two);
            assertThat(two).isEqualTo(one);
            assertThat(one.hashCode()).isEqualTo(two.hashCode());
        }
    }

    private void assertKeysNotEqual(ImmutableKey one, ImmutableKey two) {
        // cover cached hashcode branch
        for (int i = 0; i < 2; i++) {
            assertThat(one).isNotEqualTo(two);
            assertThat(two).isNotEqualTo(one);
        }
    }
}