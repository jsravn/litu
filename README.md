litu
====

Litu is a small collection of utility methods and classes for Java that I've
found useful on my travels.

It requires Java 1.7 and has no external dependencies.

To use add the jcenter repository and include a dependency like so:

```gradle
repositories {
    maven {
        url  "http://jcenter.bintray.com" 
    }
}

dependencies {
    compile 'com.jsravn:litu:1.0'
}
```

Or simply copy in java files you want to your project.

What's included
===============

More detail is in the javadocs. Here is the quick list:

* Generics#uncheckedCast - cast away your troubles with ease
* ImmutableKey - immutable key class with a cached hashcode for ad-hoc maps
