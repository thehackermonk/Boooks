package com.hacker.boooks.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {

//    @Autowired
//    private BookRepository underTest;
//
//    @BeforeEach
//    void setUp() {
//
//        int bookID = 1;
//        String name = "In Search of Lost Time";
//        String author = "Marcel Proust";
//        int publication = 1;
//        String genre = "Romance";
//        boolean available = true;
//        int holder = -1;
//
//        BookEntity bookEntity = new BookEntity(bookID, name, author, publication, genre, available, holder);
//        underTest.save(bookEntity);
//
//    }
//
//    @Test
//    void getBookByName() {
//
//        BookEntity response = underTest.getBookByName("In Search of Lost Time");
//        assertThat(response.getBookID()).isEqualTo(1);
//
//    }
//
//    @Test
//    void getBooksPublishedBy() {
//
//        List<BookEntity> response = underTest.getBooksPublishedBy(1);
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void getAllGenres() {
//
//        List<String> response = underTest.getAllGenres();
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void getAllAuthors() {
//
//        List<String> response = underTest.getAllAuthors();
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void getBookCountByGenreAndPublication() {
//
//        int response = underTest.getBookCountByGenreAndPublication("Romance", 1);
//        assertThat(response).isEqualTo(1);
//
//    }
//
//    @Test
//    void getBookCountByAuthorAndPublication() {
//
//        int response = underTest.getBookCountByAuthorAndPublication("Marcel Proust", 1);
//        assertThat(response).isEqualTo(1);
//
//    }
//
//    @Test
//    void getLastBookID() {
//
//        int response = underTest.getLastBookID();
//        assertThat(response).isEqualTo(1);
//
//    }
//
//    @Test
//    void noOfBooksWrittenBy() {
//
//        int response = underTest.noOfBooksWrittenBy("Marcel Proust");
//        assertThat(response).isEqualTo(1);
//
//    }
//
//    @Test
//    void getBooksWrittenBy() {
//
//        List<BookEntity> response = underTest.getBooksWrittenBy("Marcel Proust");
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void getGenresByAuthor() {
//
//        List<String> response = underTest.getGenresByAuthor("Marcel Proust");
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void getGenreWiseBookCountByAuthor() {
//
//        int response = underTest.getGenreWiseBookCountByAuthor("Marcel Proust", "Romance");
//        assertThat(response).isEqualTo(1);
//
//    }
//
//    @Test
//    void searchBook() {
//
//        List<BookEntity> response = underTest.searchBook("lost");
//        assertThat(response).isNotNull();
//
//    }
//
//    @Test
//    void searchAuthor() {
//
//        List<BookEntity> searchResult = underTest.searchBook("proust");
//        assertThat(searchResult).isNotNull();
//
//    }
//
//    @Test
//    void getFiveBooksOfGenre() {
//
//        List<BookEntity> response = underTest.getFiveBooksOfGenre("Romance");
//        assertThat(response).isNotNull();
//
//    }
}