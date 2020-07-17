package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.domain.repositories.AuthorRepository;
import guru.springframework.spring5webapp.domain.repositories.BookRepository;
import guru.springframework.spring5webapp.domain.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123213");
        Publisher shi = new Publisher("shi", "shi street CA");

        publisherRepository.save(shi);

        eric.getBooks().add(ddd);
        ddd.getAuthor().add(eric);

        ddd.setPublisher(shi);
        shi.getBooks().add(ddd);



        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(shi);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "122233");

        rod.getBooks().add(noEJB);
        noEJB.getAuthor().add(rod);
        noEJB.setPublisher(shi);
        shi.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(shi);


        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher Number of Books" + shi.getBooks().size());

    }
}
