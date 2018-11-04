package org.avb.whatsnew.java9.optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Optional;

public class NewOptionalMethodsDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewOptionalMethodsDemo.class);

    @Test
    public void testIfPresentOfElse() {
        User user1 = new User("stackman");

        /* Login: stackman */
        Optional.of(user1) // present
                .ifPresentOrElse(
                        u -> LOGGER.info("Login: {}", u.login),
                        () -> LOGGER.info("Not present"));

        User user2 = null;

        /* User not present */
        Optional.ofNullable(user2) // not present
                .ifPresentOrElse(
                        u -> LOGGER.info("Login: {}", u.login),
                        () -> LOGGER.info("User not present"));
    }

    @Test
    public void testOr() {
        User user1 = new User("stackman");

        /* Optional[User{login='stackman'}] */
        LOGGER.info("{}",
                Optional.of(user1)
                        .or(() -> Optional.of(new User("default"))));

        User user2 = null;

        /* Optional[User{login='default'}] */
        LOGGER.info("{}",
                Optional.ofNullable(user2)
                        .or(() -> Optional.of(new User("default"))));
    }

    @Test
    public void testToStream() {
        User user1 = new User("stackman");

        /* stackman */
        Optional.of(user1)
                .stream()
                .map(u -> u.login)
                .forEach(LOGGER::info);

        /* empty stream */
        long count = Optional.empty()
                .stream()
                .count();

        LOGGER.info("The number of elements: {}", count); // 0
    }
}

class User {
    final String login;

    User(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }
}