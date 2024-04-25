package com.javaIntro.SpringAppDatabase.database.repositories;

import static com.javaIntro.SpringAppDatabase.database.TestDataUtil.createTestAuthorA;
import static com.javaIntro.SpringAppDatabase.database.TestDataUtil.createTestAuthorB;
import static com.javaIntro.SpringAppDatabase.database.TestDataUtil.createTestAuthorC;

import java.util.Optional;

import org.hibernate.mapping.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.javaIntro.SpringAppDatabase.database.domain.Author;

/**
 * AuthorDAOImplIntegrationTest
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = createTestAuthorA();
        authorRepository.save(author);

        Optional<Author> persistedAuthor = authorRepository.findById(author.getId());
        System.out.println(persistedAuthor);
        assertEquals(Long.valueOf(1L), persistedAuthor.get().getId());
    }

    @Test
    public void testThatManyAuthorsCanBeRetrived() {
        Author authorX = createTestAuthorA();
        Author authorY = createTestAuthorB();
        Author authorZ = createTestAuthorC();
        authorRepository.save(authorX);
        authorRepository.save(authorY);
        authorRepository.save(authorZ);

        Iterable<Author> authors = authorRepository.findAll();
        for (Author author : authors) {
            System.out.println(author);
        }
    }
}
