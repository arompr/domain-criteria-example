package com.example.infra.mariadb;

import com.example.domain.query.Query;
import com.example.domain.user.User;
import com.example.domain.user.UserId;
import com.example.infra.mariadb.user.MariaUserRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static com.example.domain.query.Query.where;
import static com.example.domain.user.criteria.EmailEqualsCriteria.hasEmail;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class MariaUserRepositoryTest {
    public static final UserId ALICE_ID = new UserId("d1ef7f98-382a-43ae-abb8-d65d2b0920f4");
    public static final String ALICE_EMAIL = "alice@example.com";
    public static final UserId BOB_THE_BUILDER_ID = new UserId("99f17607-fe9a-4a21-9f26-6fca3cf6593a");
    public static final String BOB_THE_BUILDER_EMAIL = "bob@example.com";

    public static final User ALICE = createAlice();
    public static final User BOB_THE_BUILDER = createBobTheBuilder();


    private MariaUserRepository repository;

    @BeforeEach
    @Transactional
    void setUp() {
        repository = new MariaUserRepository();
        repository.deleteAll();
        repository.save(ALICE);
        repository.save(BOB_THE_BUILDER);
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "carol@example.com",
                "Carol",
                "Danvers"));
        repository.save(new User(
                new UserId(UUID.randomUUID()), "dave@example.com", "Dave", "Grohl"));
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "eve@example.com",
                "Eve",
                "Polastri"));
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "frank@example.com",
                "Frank",
                "Castle"));
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "grace@example.com",
                "Grace",
                "Hopper"));
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "heidi@example.com",
                "Heidi",
                "Klum"));
        repository.save(new User(
                new UserId(UUID.randomUUID()), "ivan@example.com", "Ivan", "Drago"));
        repository.save(new User(
                new UserId(UUID.randomUUID()), "judy@example.com", "Judy", "Hopps"));
        repository.save(new User(
                new UserId(UUID.randomUUID()),
                "mallory@example.com",
                "Mallory",
                "Knox"));
    }

    @Test
    void givenQueryWithNonMatchingCriteria_whenFetch_thenReturnsEmptyList() {
        Query query = where(hasEmail("not-" + ALICE_EMAIL));

        List<User> users = repository.fetch(query);

        assertThat(users).isEmpty();
    }

    @Test
    void givenQueryWithMatchingCriteria_whenFetch_thenReturnsMatchingUser() {
        Query query = where(hasEmail(ALICE_EMAIL));

        List<User> users = repository.fetch(query);

        assertThat(users)
                .singleElement()
                .usingRecursiveComparison()
                .isEqualTo(ALICE);
    }

    @Test
    void givenQueryWithMatchingAndNonMatchingCriterias_whenFetch_thenReturnsEmptyList() {
        Query query = where(hasEmail(ALICE_EMAIL).and(hasEmail("not-" + ALICE_EMAIL)));

        List<User> users = repository.fetch(query);

        assertThat(users).isEmpty();
    }

    @Test
    void givenQueryWithMatchingOrNonMatchingConditions_whenFetch_thenReturnsMatchingUser() {
        Query query = where(hasEmail(ALICE_EMAIL).or(hasEmail("not-" + ALICE_EMAIL)));

        List<User> users = repository.fetch(query);

        assertThat(users)
                .singleElement()
                .usingRecursiveComparison()
                .isEqualTo(ALICE);
    }

    @Test
    void givenQueryMatchingMultipleUsers_whenFetch_thenReturnsAllMatchingUsers() {
        Query query = where(hasEmail(ALICE_EMAIL).or(hasEmail(BOB_THE_BUILDER_EMAIL)));

        List<User> users = repository.fetch(query);

        assertThat(users)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(ALICE, BOB_THE_BUILDER);
    }

    @Test
    void givenNotQueryWithMatchingCondition_whenFetch_thenDoesNotReturnMatchingUser() {
        Query query = where(hasEmail(ALICE_EMAIL).not());

        List<User> users = repository.fetch(query);

        assertThat(users)
                .usingRecursiveFieldByFieldElementComparator()
                .doesNotContain(ALICE);
    }

    @Test
    void givenNotQueryWithMatchingCondition_whenFetch_thenReturnsOtherUsers() {
        Query query = where(hasEmail(ALICE_EMAIL).not());

        List<User> users = repository.fetch(query);

        assertThat(users)
                .usingRecursiveFieldByFieldElementComparator()
                .contains(BOB_THE_BUILDER)
                .doesNotContain(ALICE);
    }


    private static User createAlice() {
        return new User(ALICE_ID, ALICE_EMAIL, "Alice", "In Wonderland");
    }
    private static User createBobTheBuilder() {
        return new User(BOB_THE_BUILDER_ID, BOB_THE_BUILDER_EMAIL, "Bob", "Builder");
    }
}
