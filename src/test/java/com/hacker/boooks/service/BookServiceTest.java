package com.hacker.boooks.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookServiceTest {

//    @Mock
//    private BookRepository bookRepository;
//    @Mock
//    private LogRepository logRepository;
//    @Mock
//    private MemberRepository memberRepository;
//    @Mock
//    private PublisherRepository publisherRepository;
//
//    @InjectMocks
//    private BookServiceImpl underTest;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @Test
//    void testGetAuthorProfile_returnsOkResponse_whenAuthorFound() {
//        // Setup
//        List<BookEntity> bookEntities = List.of(
//                new BookEntity(1, "Book 1", "Author 1", "Publication 1", "Genre 1", true, null),
//                new BookEntity(2, "Book 2", "Author 1", "Publication 2", "Genre 2", false, 1)
//        );
//
//        when(bookRepository.findByAuthor(anyString())).thenReturn(bookEntities);
//        when(logRepository.countByBookId(anyInt())).thenReturn(2);
//
//        // Execution
//        ResponseEntity<AuthorProfile> response = underTest.getAuthorProfile("Author");
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAuthorProfile_returnsInternalServerErrorResponse_whenBookRepositoryThrowsException() {
//        // Setup
//        when(bookRepository.findByAuthor(anyString())).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<AuthorProfile> response = underTest.getAuthorProfile("Author");
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAuthorProfile_returnsNotFoundResponse_whenAuthorNotFound() {
//        // Setup
//        when(bookRepository.findByAuthor(anyString())).thenReturn(Collections.emptyList());
//
//        // Execution
//        ResponseEntity<AuthorProfile> response = underTest.getAuthorProfile("Nonexistent Author");
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testGetAuthors_returnsAuthors_whenAuthorsExist() {
//        // Setup
//        List<BookEntity> bookEntities = List.of(
//                new BookEntity(1, "Book 1", "Author 1", "Publication 1", "Genre 1", true, null),
//                new BookEntity(2, "Book 2", "Author 2", "Publication 2", "Genre 2", false, 1),
//                new BookEntity(3, "Book 3", "Author 1", "Publication 3", "Genre 2", false, 2)
//        );
//
//        when(bookRepository.findAll()).thenReturn(bookEntities);
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getAuthors();
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(List.of("Author 1", "Author 2"), response.getBody());
//    }
//
//    @Test
//    void testGetAuthors_returnsNotFound_whenNoAuthorsFound() {
//        // Setup
//        when(bookRepository.findAll()).thenReturn(Collections.emptyList());
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getAuthors();
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAuthors_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        when(bookRepository.findAll()).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getAuthors();
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetBooksWrittenBy_returnsBooksList_whenAuthorFound() {
//        // Setup
//        String author = "John Doe";
//        BookEntity bookEntity = new BookEntity(1, "Book 1", "Author 1", "Publication 1", "Genre 1", true, null);
//
//        when(bookRepository.findByAuthor(author)).thenReturn(Collections.singletonList(bookEntity));
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getBooksWrittenBy(author);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        List<Book> books = response.getBody();
//        assertNotNull(books);
//        assertEquals(1, books.size());
//
//        Book book = books.get(0);
//        assertEquals(bookEntity.getTitle(), book.getTitle());
//        assertEquals(bookEntity.getAuthor(), book.getAuthor());
//        assertEquals(bookEntity.getPublication(), book.getPublication());
//
//    }
//
//    @Test
//    void testGetBooksWrittenBy_returnsNotFound_whenNoBooksFound_byAuthor() {
//        // Setup
//        String author = "John Doe";
//        when(bookRepository.findByAuthor(author)).thenReturn(Collections.emptyList());
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getBooksWrittenBy(author);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetBooksWrittenBy_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        String author = "John Doe";
//        when(bookRepository.findByAuthor(anyString())).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getBooksWrittenBy(author);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetGenreWiseBookCountByAuthor_returnsCorrectMapForAuthorWithBooks_whenBooksExist() {
//        // Setup
//        String authorName = "Author 1";
//        List<BookEntity> bookEntities = List.of(
//                new BookEntity(1, "Book 1", "Author 1", "Publication 1", "Genre 1", true, null),
//                new BookEntity(2, "Book 2", "Author 2", "Publication 2", "Genre 1", true, null),
//                new BookEntity(3, "Book 3", "Author 1", "Publication 2", "Genre 2", true, null)
//        );
//        when(bookRepository.findByAuthor(authorName)).thenReturn(bookEntities);
//
//        // Execution
//        ResponseEntity<Map<String, Integer>> response = underTest.getGenreWiseBookCountByAuthor(authorName);
//        Map<String, Integer> bookCountByGenre = response.getBody();
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assert bookCountByGenre != null;
//        assertEquals(2, bookCountByGenre.size());
//        assertEquals(2, bookCountByGenre.get("Genre 1"));
//        assertEquals(1, bookCountByGenre.get("Genre 2"));
//    }
//
//    @Test
//    void testGetGenreWiseBookCountByAuthor_returnsNotFoundForAuthor_whenNoBooks() {
//        // Setup
//        String authorName = "Author 2";
//        when(bookRepository.findByAuthor(authorName)).thenReturn(Collections.emptyList());
//
//        // Execution
//        ResponseEntity<Map<String, Integer>> response = underTest.getGenreWiseBookCountByAuthor(authorName);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetGenreWiseBookCountByAuthor_returnsNotFoundForUnknownAuthor_whenNoBooks() {
//        // Setup
//        String authorName = "Unknown Author";
//        when(bookRepository.findByAuthor(authorName)).thenReturn(null);
//
//        // Execution
//        ResponseEntity<Map<String, Integer>> response = underTest.getGenreWiseBookCountByAuthor(authorName);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetGenreWiseBookCountByAuthor_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        String authorName = "Unknown Author";
//        when(bookRepository.findByAuthor(authorName)).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<Map<String, Integer>> response = underTest.getGenreWiseBookCountByAuthor(authorName);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testAddBook_returnsSuccessMessage_whenSuccessfulAddition() {
//        // Setup
//        BookBO bookBO = new BookBO("Test Book", "Test Author", "Test Publication", "Test Genre");
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookID(1);
//        bookEntity.setTitle(bookBO.getName());
//        bookEntity.setAuthor(bookBO.getAuthor());
//        bookEntity.setPublication(bookBO.getPublication());
//        bookEntity.setGenre(bookBO.getGenre());
//        bookEntity.setAvailable(true);
//        bookEntity.setHolder(null);
//        when(bookRepository.save(any())).thenReturn(bookEntity);
//
//        // Execution
//        ResponseEntity<String> response = underTest.addBook(bookBO);
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Book added successfully", response.getBody());
//    }
//
//    @Test
//    void testAddBook_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        BookBO bookBO = new BookBO("Test Book", "Test Author", "Test Publication", "Test Genre");
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookID(1);
//        bookEntity.setTitle(bookBO.getName());
//        bookEntity.setAuthor(bookBO.getAuthor());
//        bookEntity.setPublication(bookBO.getPublication());
//        bookEntity.setGenre(bookBO.getGenre());
//        bookEntity.setAvailable(true);
//        bookEntity.setHolder(null);
//        when(bookRepository.save(any())).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<String> response = underTest.addBook(bookBO);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testRemoveBook_returnsSuccessMessage_whenSuccessfulRemoval() {
//        // Setup
//        int bookId = 1;
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookID(bookId);
//        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
//        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);
//
//        // Execution
//        ResponseEntity<String> response = underTest.removeBook(bookId);
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Book removed successfully", response.getBody());
//        verify(bookRepository, times(1)).delete(bookEntity);
//        verify(bookRepository, times(1)).findById(bookId);
//    }
//
//    @Test
//    void testRemoveBook_returnsNotFound_whenBookNotFound() {
//        // Setup
//        int bookId = 1;
//        Optional<BookEntity> optionalBookEntity = Optional.empty();
//        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);
//
//        // Execution
//        ResponseEntity<String> response = underTest.removeBook(bookId);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(bookRepository, times(0)).delete(any(BookEntity.class));
//        verify(bookRepository, times(1)).findById(bookId);
//    }
//
//    @Test
//    void testRemoveBook_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        int bookId = 1;
//        when(bookRepository.findById(bookId)).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<String> response = underTest.removeBook(bookId);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testUpdateBook_returnsSuccessMessage_whenSuccessfulUpdate() {
//        // Setup
//        int bookId = 1;
//        BookBO bookBO = new BookBO();
//        bookBO.setName("Updated Name");
//        bookBO.setAuthor("Updated Author");
//        bookBO.setPublication("Updated Publication");
//        bookBO.setGenre("Updated Genre");
//        BookEntity bookEntity = new BookEntity();
//        bookEntity.setBookID(bookId);
//        Optional<BookEntity> optionalBookEntity = Optional.of(bookEntity);
//        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);
//        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);
//
//        // Execution
//        ResponseEntity<String> response = underTest.updateBook(bookId, bookBO);
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Book updated successfully", response.getBody());
//        assertEquals(bookBO.getName(), optionalBookEntity.get().getTitle());
//        assertEquals(bookBO.getAuthor(), optionalBookEntity.get().getAuthor());
//        assertEquals(bookBO.getPublication(), optionalBookEntity.get().getPublication());
//        assertEquals(bookBO.getGenre(), optionalBookEntity.get().getGenre());
//        verify(bookRepository, times(1)).save(bookEntity);
//        verify(bookRepository, times(1)).findById(bookId);
//    }
//
//    @Test
//    void testUpdateBook_returnsNotFound_whenBookNotFound() {
//        // Setup
//        int bookId = 1;
//        BookBO bookBO = new BookBO();
//        bookBO.setName("Updated Name");
//        bookBO.setAuthor("Updated Author");
//        bookBO.setPublication("Updated Publication");
//        bookBO.setGenre("Updated Genre");
//        Optional<BookEntity> optionalBookEntity = Optional.empty();
//        when(bookRepository.findById(bookId)).thenReturn(optionalBookEntity);
//
//        // Execution
//        ResponseEntity<String> response = underTest.updateBook(bookId, bookBO);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        verify(bookRepository, times(0)).save(any(BookEntity.class));
//        verify(bookRepository, times(1)).findById(bookId);
//    }
//
//    @Test
//    void testUpdateBook_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        int bookId = 1;
//        BookBO bookBO = new BookBO();
//        bookBO.setName("Updated Name");
//        bookBO.setAuthor("Updated Author");
//        bookBO.setPublication("Updated Publication");
//        bookBO.setGenre("Updated Genre");
//        when(bookRepository.findById(bookId)).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<String> response = underTest.updateBook(bookId, bookBO);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAvailableBooks_returnsEmptyList_whenNoBooksAvailable() {
//        // Setup
//        when(bookRepository.findAll()).thenReturn(Arrays.asList(
//                new BookEntity(1, "Book1", "Author1", "Publisher1", "Fiction", false, null),
//                new BookEntity(2, "Book2", "Author2", "Publisher2", "Non-Fiction", false, null)
//        ));
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getAvailableBooks();
//
//        // Verification
//        verify(bookRepository, times(1)).findAll();
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    @Test
//    void testGetAvailableBooks_returnsNonEmptyList_whenBooksExist() {
//        // Setup
//        when(bookRepository.findAll()).thenReturn(Arrays.asList(
//                new BookEntity(1, "Book1", "Author1", "Publisher1", "Fiction", true, null),
//                new BookEntity(2, "Book2", "Author2", "Publisher2", "Non-Fiction", true, null)
//        ));
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getAvailableBooks();
//
//        // Verification
//        verify(bookRepository, times(1)).findAll();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(2, Objects.requireNonNull(response.getBody()).size());
//    }
//
//    @Test
//    void testGetAvailableBooks_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        when(bookRepository.findAll()).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<List<Book>> response = underTest.getAvailableBooks();
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetBookDetails_returnsBook_whenValidNameProvided() {
//        // Setup
//        String name = "Book1";
//        BookEntity bookEntity = new BookEntity(1, name, "Author1", "Publisher1", "Novel", true, null);
//
//        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));
//
//        // Execution
//        ResponseEntity<Book> response = underTest.getBookDetails(name);
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertNotNull(response.getBody());
//        assertEquals(name, response.getBody().getTitle());
//    }
//
//    @Test
//    void testGetBookDetails_returnsNotFound_whenBookNotFound() {
//        // Setup
//        String name = "Non-existent Book Name";
//        when(bookRepository.findAll()).thenReturn(List.of());
//
//        // Execution
//        ResponseEntity<Book> response = underTest.getBookDetails(name);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testGetBookDetails_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        String name = "The Great Gatsby";
//        when(bookRepository.findAll()).thenThrow(new RuntimeException("Database connection error"));
//
//        // Execution
//        ResponseEntity<Book> response = underTest.getBookDetails(name);
//
//        // Verification
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testWhoHoldsTheBook_returnsNotFound_whenBookNotFound() {
//        // Setup
//        when(bookRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        // Execution
//        ResponseEntity<Member> response = underTest.whoHoldsTheBook(1);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testWhoHoldsTheBook_returnsNotFound_whenBookNotHeldByAnyMember() {
//        // Setup
//        BookEntity bookEntity = mock(BookEntity.class);
//        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));
//        when(bookEntity.getHolder()).thenReturn(null);
//
//        // Execution
//        ResponseEntity<Member> response = underTest.whoHoldsTheBook(1);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testWhoHoldsTheBook_whenMemberHoldingTheBookNotFound_thenReturnNotFound() {
//        // Setup
//        BookEntity bookEntity = mock(BookEntity.class);
//        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));
//        when(bookEntity.getHolder()).thenReturn(1);
//        when(memberRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Execution
//        ResponseEntity<Member> response = underTest.whoHoldsTheBook(1);
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testWhoHoldsTheBook_whenMemberHoldingTheBookFound_thenReturnMember() {
//        // Setup
//        BookEntity bookEntity = new BookEntity(1, "Book 1", "Author 1", "Publisher 1", "Genre 1", true, 1);
//        MemberEntity memberEntity = new MemberEntity(1, "John Doe", java.sql.Date.valueOf("1990-01-01"), "johndoe@example.com", "+1-123-456-7890", null);
//        when(bookRepository.findById(anyInt())).thenReturn(Optional.of(bookEntity));
//        when(memberRepository.findById(1)).thenReturn(Optional.of(memberEntity));
//
//        // Execution
//        ResponseEntity<Member> response = underTest.whoHoldsTheBook(1);
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertTrue(response.hasBody());
//        assertEquals("John Doe", Objects.requireNonNull(response.getBody()).getName());
//    }
//
//    @Test
//    void testWhoHoldsTheBook_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        when(bookRepository.findById(anyInt())).thenThrow(new RuntimeException());
//        when(memberRepository.findById(1)).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<Member> response = underTest.whoHoldsTheBook(1);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }
//
//    @Test
//    void testGetBookNames_returnsNonEmptyList_whenBooksExist() {
//        // Setup
//        List<BookEntity> bookEntities = new ArrayList<>();
//        bookEntities.add(new BookEntity(1, "Book 1", "Author 1", "Publisher 1", "Genre 1", true, null));
//        bookEntities.add(new BookEntity(2, "Book 2", "Author 2", "Publisher 2", "Genre 2", true, null));
//        when(bookRepository.findAll()).thenReturn(bookEntities);
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getBookNames();
//
//        // Verification
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(Arrays.asList("Book 1", "Book 2"), response.getBody());
//    }
//
//    @Test
//    void testGetBookNames_returnsEmptyList_whenNoBooksExist() {
//        // Setup
//        List<BookEntity> bookEntities = new ArrayList<>();
//        when(bookRepository.findAll()).thenReturn(bookEntities);
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getBookNames();
//
//        // Verification
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(List.of(), response.getBody());
//    }
//
//    @Test
//    void testGetBookNames_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        when(bookRepository.findAll()).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<List<String>> response = underTest.getBookNames();
//
//        // Verification
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//        assertNull(response.getBody());
//    }
//
//    @Test
//    void testGetBookProfile_returnsBookProfile_whenValidBookIdProvided() {
//        // Setup
//        int bookId = 1;
//        BookEntity bookEntity = new BookEntity(1, "Book 1", "Author 1", "Publisher 1", "Genre 1", true, null);
//        PublisherEntity publisherEntity = new PublisherEntity("Publisher 1", "publisher1@test.com", "www.publisher1.com");
//        MemberEntity memberEntity = new MemberEntity(1, "John Doe", java.sql.Date.valueOf("1990-01-01"), "johndoe@example.com", "+1-123-456-7890", null);
//        BookProfile expectedBookProfile = new BookProfile(bookEntity, publisherEntity, memberEntity);
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
//        when(publisherRepository.findById(bookEntity.getPublication())).thenReturn(Optional.of(publisherEntity));
//        when(memberRepository.findById(bookEntity.getHolder())).thenReturn(Optional.of(memberEntity));
//
//        // Execution
//        ResponseEntity<BookProfile> response = underTest.getBookProfile(bookId);
//
//        // Verification
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertEquals(expectedBookProfile.getTitle(), response.getBody().getTitle());
//    }
//
//    @Test
//    void testGetBookProfile_returnsReturnNotFound_whenInvalidBookIdProvided() {
//        // Setup
//        int bookId = 1;
//        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());
//
//        // Execution
//        ResponseEntity<BookProfile> response = underTest.getBookProfile(bookId);
//
//        // Verification
//        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        Assertions.assertNull(response.getBody());
//    }
//
//    @Test
//    void testGetBookProfile_returnsBookProfile_whenPublisherMissing() {
//        // Setup
//        int bookId = 1;
//        BookEntity bookEntity = new BookEntity(1, "Book 1", "Author 1", "Publisher 1", "Genre 1", true, null);
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
//        when(publisherRepository.findById(bookEntity.getPublication())).thenReturn(Optional.empty());
//
//        // Execution
//        ResponseEntity<BookProfile> response = underTest.getBookProfile(bookId);
//
//        // Verification
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertNotNull(response.getBody());
//        Assertions.assertNull(response.getBody().getPublication());
//    }
//
//    @Test
//    void testGetBookProfile_returnsBookProfile_whenNoHolder() {
//        // Setup
//        int bookId = 1;
//        BookEntity bookEntity = new BookEntity(1, "Book 1", "Author 1", "Publisher 1", "Genre 1", true, null);
//        PublisherEntity publisherEntity = new PublisherEntity("Publisher 1", "publisher1@test.com", "www.publisher1.com");
//        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));
//        when(publisherRepository.findById(bookEntity.getPublication())).thenReturn(Optional.of(publisherEntity));
//
//        // Execution
//        ResponseEntity<BookProfile> response = underTest.getBookProfile(bookId);
//
//        // Verification
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//
//    @Test
//    void testGetBookProfile_returnsInternalServerError_whenExceptionThrown() {
//        // Setup
//        int bookId = 1;
//        when(bookRepository.findById(bookId)).thenThrow(new RuntimeException());
//        when(publisherRepository.findById(any())).thenThrow(new RuntimeException());
//
//        // Execution
//        ResponseEntity<BookProfile> response = underTest.getBookProfile(bookId);
//
//        // Verification
//        assertNotNull(response);
//        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//    }

}