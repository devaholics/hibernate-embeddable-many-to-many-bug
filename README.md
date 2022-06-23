# Hibernate ManyToMany collections in Embeddable

Hibernate doesn't store a `@ManyToMany` relation when the collection resides in `@Embeddable`. Although the collection
is filled, after calling `merge()`, the collection field of the return value is `null`. 

To see this bug in action, execute `JPAUnitTestCase::shouldStoreUsersCollection`. One can see that Hibernates merges
the collection as expected, if it resides directly within an `@Entity`. But if the collection resides in an `@Embeddable`
the test fails.

The javadoc of the `@ManyToMany` annotation states
> The ManyToMany annotation may be used within an embeddable class contained within an entity class to specify a
relationship to a collection of entities. (...)

So this is expected to work.
