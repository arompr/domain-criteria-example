## Domain Criteria Query Builder

This is a demo project showcasing how to build a **composable criteria based query builder** in Java, inspired by Domain-Driven Design (DDD), the Specification pattern as well as the Query Object pattern.

It was created as an educational example to explore how queries can be expressed as domain logic using a declarative approach while remaining independant of any specific database implementation.

## Example

```java
// Find a user with a specific email
Query query = where(hasEmail("alice@example.com"));
List<User> results = userRepository.fetch(query);
```

We can also chain the criterias :

```java
// Find users with Alice's email OR Bob's email
Query query = where(hasEmail("alice@example.com").or(hasEmail("bob@example.com")));

// Exclude users with Alice's email
Query query = where(hasEmail("alice@example.com").not());
```

You can see more examples in the repo test class
