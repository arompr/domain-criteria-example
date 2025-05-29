package com.example.infra.mariadb;

import com.example.domain.query.Query;
import com.example.domain.user.User;
import com.example.domain.user.UserId;
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
    public static final User ALICE = createAlice();

    private MariaUserRepository repository;

    @BeforeEach
    @Transactional
    void setUp() {
        repository = new MariaUserRepository();
        repository.deleteAll();
        repository.save(ALICE);
        repository.save(new User(
                new UserId(UUID.randomUUID()), "bob@example.com", "Bob", "Builder"));
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
    void fetch_users_by_email_returns_empty_list_when_no_match() {
        Query query = where(hasEmail("not-" + ALICE_EMAIL));

        List<User> users = repository.fetch(query);

        assertThat(users).isEmpty();
    }

    @Test
    void fetch_users_by_email_returns_matching_user() {
        Query query = where(hasEmail(ALICE_EMAIL));

        List<User> users = repository.fetch(query);

        assertThat(users).singleElement().usingRecursiveComparison().isEqualTo(ALICE);
    }

    private static User createAlice() {
        return new User(ALICE_ID, ALICE_EMAIL, "Alice", "In Wonderland");
    }
}
