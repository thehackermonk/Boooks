package com.hacker.boooks.serviceImpl;

import com.hacker.boooks.bean.Book;
import com.hacker.boooks.bean.Member;
import com.hacker.boooks.bean.Publication;
import com.hacker.boooks.bean.PublicationProfile;
import com.hacker.boooks.constant.Response;
import com.hacker.boooks.entity.BookEntity;
import com.hacker.boooks.entity.MemberEntity;
import com.hacker.boooks.entity.PublisherEntity;
import com.hacker.boooks.repository.BookRepository;
import com.hacker.boooks.repository.MemberRepository;
import com.hacker.boooks.repository.PublisherRepository;
import com.hacker.boooks.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.*;

@Service
@SuppressWarnings("unused")
public class PublicationServiceImpl implements PublicationService {

    Logger log = LoggerFactory.getLogger(PublicationServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * @return Next publisher ID
     * @apiNote Get next publisher ID
     * @author [@thehackermonk]
     * @since 1.0
     */
    private int getNextPublisherID() {

        int lastPublisherID = publisherRepository.getLastPublisherID();
        return lastPublisherID + 1;

    }


    /**
     * @param publicationName Name of the publication
     * @return True is addition succeeds and false otherwise
     * @apiNote Add new publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> addPublication(String publicationName) {

        PublisherEntity publication = publisherRepository.getPublisher(publicationName);

        if (publication == null) {

            PublisherEntity newPublication = new PublisherEntity(getNextPublisherID(), publicationName);
            publisherRepository.save(newPublication);

            log.info(publicationName + " added successfully!");

            return Collections.singletonMap("result", true);

        } else {

            log.info(publicationName + " already exist!");

            return Collections.singletonMap("result", false);

        }

    }

    /**
     * @param publicationID Unique ID of the publication
     * @return Publication details
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Publication getPublication(int publicationID) {

        Publication publication;
        PublisherEntity publicationEntity = publisherRepository.getById(publicationID);

        publication = new Publication(publicationEntity);

        log.info("Details of " + publicationID + " fetched.");

        return publication;

    }

    /**
     * @param publicationName Name of the publication
     * @return Publication details
     * @apiNote Get publication details
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Publication getPublication(String publicationName) {

        Publication publication;
        PublisherEntity publicationEntity = publisherRepository.getPublisher(publicationName);

        if (publicationEntity != null) {

            publication = new Publication(publicationEntity);

            log.info("Details of " + publicationName + " fetched.");

            return publication;

        } else
            return null;

    }

    /**
     * @return All publications
     * @apiNote Get all publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Publication> getPublications() {

        List<PublisherEntity> publicationEntities = publisherRepository.findAll();
        List<Publication> publicationList = new ArrayList<>();

        for (PublisherEntity publicationEntity : publicationEntities)
            publicationList.add(new Publication(publicationEntity));

        log.info(Response.PUBLICATIONS_FETCHED);

        return publicationList;

    }

    /**
     * @param publisherID        Unique identifier of publication
     * @param newPublicationName New name for publication
     * @return true if publication name changed successfully, and false otherwise
     * @apiNote Update publications
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> updatePublication(int publisherID, String newPublicationName) {

        PublisherEntity publisher = publisherRepository.getById(publisherID);
        String oldPublicationName;

        oldPublicationName = publisher.getName();

        publisher.setName(newPublicationName);
        publisherRepository.save(publisher);

        log.info("Publisher changed from " + oldPublicationName + " to " + newPublicationName + ".");

        return Collections.singletonMap("result", true);

    }

    /**
     * @param publicationName Name of the publication
     * @return true if publication removed successfully, and false otherwise
     * @apiNote Remove publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Map<String, Boolean> removePublication(String publicationName) {

        PublisherEntity publisher = publisherRepository.getPublisher(publicationName);

        if (publisher != null) {

            publisherRepository.delete(publisher);

            log.info("Publisher " + publicationName + " removed successfully!");

            return Collections.singletonMap("result", true);

        } else
            return Collections.singletonMap("result", false);

    }

    /**
     * @param publicationName name of the publication
     * @return List of books published by a publication
     * @apiNote Get books published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public List<Book> getBooksPublishedBy(String publicationName) {

        List<Book> books = new ArrayList<>();
        List<BookEntity> bookEntities;

        PublisherEntity publisher = publisherRepository.getPublisher(publicationName);
        bookEntities = bookRepository.getBooksPublishedBy(publisher.getPublisherId());

        for (BookEntity bookEntity : bookEntities) {

            int bookID = bookEntity.getBookID();
            String name = bookEntity.getName();
            String author = bookEntity.getAuthor();
            int publisherID = bookEntity.getPublication();
            String genre = bookEntity.getGenre();
            boolean available = bookEntity.isAvailable();
            int holder = bookEntity.getHolder();

            Book book;
            Member member;
            MemberEntity memberEntity;

            PublisherEntity publisherEntity = publisherRepository.getById(publisherID);
            Publication publication = new Publication(publisherEntity);

            if (holder == 0)
                memberEntity = null;
            else
                memberEntity = memberRepository.getById(holder);

            if (!(memberEntity == null)) {

                member = new Member(memberEntity);
                book = new Book(bookID, name, author, publication, genre, available, member);

            } else
                book = new Book(bookID, name, author, publication, genre, available, null);

            books.add(book);

        }

        log.info("Books published by " + publicationName + " fetched.");

        return books;

    }

    /**
     * @param publicationName name of the publication
     * @return Count of books by genre published by a publication
     * @apiNote Get count of books by genre published by a publication
     * @author [@thehackermonk]
     * @since 1.0
     */
    public Map<String, Integer> getBookCountGenreWise(@RequestHeader String publicationName) {

        Map<String, Integer> bookGenreCount = new HashMap<>();

        PublisherEntity publisher = publisherRepository.getPublisher(publicationName);

        List<String> genres = bookRepository.getAllGenres();

        for (String genre : genres) {

            int bookCount = bookRepository.getBookCountByGenreAndPublication(genre, publisher.getPublisherId());

            if (bookCount > 0)
                bookGenreCount.put(genre, bookCount);

        }

        log.info("Books count (genre-wise) published by " + publicationName + " fetched.");

        return bookGenreCount;

    }

    /**
     * @param publicationName name of the publication
     * @return Publication profile
     * @apiNote Get publication profile
     * @author [@thehackermonk]
     * @since 1.0
     */
    public PublicationProfile getPublicationProfile(String publicationName) {

        PublicationProfile publicationProfile = new PublicationProfile();
        publicationProfile.setPublicationName(publicationName);

        PublisherEntity publisher = publisherRepository.getPublisher(publicationName);
        List<BookEntity> bookEntities = bookRepository.getBooksPublishedBy(publisher.getPublisherId());

        publicationProfile.setNoOfBooksPublished(bookEntities.size());

        List<String> genres = bookRepository.getAllGenres();
        List<String> authors = bookRepository.getAllAuthors();

        Map<String, Integer> genreCount = new HashMap<>();
        Map<String, Integer> authorCount = new HashMap<>();

        for (String genre : genres) {

            int count = bookRepository.getBookCountByGenreAndPublication(genre, publisher.getPublisherId());
            if (count > 0)
                genreCount.put(genre, count);

        }

        for (String author : authors) {
            int count = bookRepository.getBookCountByAuthorAndPublication(author, publisher.getPublisherId());
            if (count > 0)
                authorCount.put(author, count);
        }

        String favoriteGenre = Collections.max(genreCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        publicationProfile.setFavoriteGenre(favoriteGenre);

        String favoriteAuthor = Collections.max(authorCount.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        publicationProfile.setFavoriteAuthor(favoriteAuthor);

        log.info("Profile of " + publicationName + " fetched.");

        return publicationProfile;

    }

    /**
     * @param publicationName Name of the publication
     * @return true if name doesn't exist, false otherwise
     * @apiNote Check if publication name already exists
     * @author [@thehackermonk]
     * @since 1.0
     */
    @Override
    public Map<String, Boolean> checkPublication(String publicationName) {

        PublisherEntity publisherEntity=publisherRepository.getPublisher(publicationName);

        if(publisherEntity==null)
            return Collections.singletonMap("result", true);
        else
            return Collections.singletonMap("result", false);

    }

}
