litu
====

Litu is a small collection of utility methods and classes for Java that I've
found useful on my travels.

It requires Java 1.6 and has no external dependencies.

To use, include a maven dependency like so:

```xml
<dependency>
    <groupId>com.jsravn</groupId>
    <artifactId>litu</artifactId>
    <version>1.0</version>
</dependency>
```

Or simply copy in java files you want to your project.

What's included
===============

More detail is in the javadocs. Here is the quick list:

* Generics#uncheckedCast - cast away your troubles with ease
* ImmutableKey - immutable key class with a cached hashcode for ad-hoc maps
