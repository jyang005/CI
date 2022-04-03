package edu.odu.cs.cs350;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Iterator;


public class TestBook {


  String defaultTitle = "a title";
  String defaultPublisher = "ODU Publishing";
  String defaultISBN = "1234567890123";
  Author[] emptyAuthors = {};
  Author jones = new Author("Jim", "Jones");
  Author smith = new Author("Sarah", "Smith");
  Author doe = new Author("John", "Doe");
  Author[] twoAuthors = {jones, smith};
  Book blank = new Book();
  
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  public void setUp() throws Exception {
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#Book()}.
   */
  @Test
  public final void testBook() {
    Book book = new Book();
    assertThat(book.getTitle(), is(""));
    assertThat(book.getISBN(), is(""));
    assertThat(book.getPublisher(), is(""));
    assertThat(book.numAuthors(), is(0));
    Iterator<Author> it = book.iterator();
    assertThat(it.hasNext(), is(false));
    assertThat(book, is(blank));
    assertEquals(book.hashCode(), blank.hashCode());
    assertEquals(book.toString(), blank.toString());
  }

  /**
   * Test method for 
   * {@link edu.odu.cs.cs350.Book#Book(java.lang.String, java.lang.String, java.lang.String, edu.odu.cs.cs350.Author[])}.
   */
  @Test
  public final void testBookStringStringStringAuthorArray() {
    Book book = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getISBN(), is(defaultISBN));
    assertThat(book.getPublisher(), is(defaultPublisher));
    assertThat(book.numAuthors(), is(2));

    int count = 0;
    for (Author au: book) {
      assertEquals (twoAuthors[count], au);
      ++count;
    }
    assertEquals (2, count);
    // or, more compactly, we can replace the above "paragraph" of code with
    assertThat(book, contains(twoAuthors));

    assertThat(book, not(is(blank)));
    // assertNotEquals(book.hashCode(), blank.hashCode());  // Can't rely on this. It should only probably be true.
    String bookRepresentation = book.toString();
    assertThat (bookRepresentation, containsString(defaultTitle));
    assertThat (bookRepresentation, containsString(defaultPublisher));
    assertThat (bookRepresentation, containsString(defaultISBN));
    assertThat (bookRepresentation, containsString("Jones"));
    assertThat (bookRepresentation, containsString("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#setTitle(java.lang.String)}.
   */
  @Test
  public final void testSetTitle() {
    // Done in older JUnit style without Hamcrest, for illustration
    Book book = new Book(defaultTitle, defaultPublisher, 
        defaultISBN, twoAuthors);
    book.setTitle("something else");
    assertEquals("something else", book.getTitle());
    assertEquals(defaultISBN, book.getISBN());
    assertEquals(defaultPublisher, book.getPublisher());
    assertEquals(2, book.numAuthors());
    assertIterableEquals(Arrays.asList(twoAuthors), book);

    assertNotEquals(book, blank);
    assertNotEquals(book.hashCode(), blank.hashCode());
    String bookRepresentation = book.toString();
    assertTrue (bookRepresentation.contains(defaultPublisher));
    assertTrue (bookRepresentation.contains(defaultISBN));
    assertTrue (bookRepresentation.contains("Jones"));
    assertTrue (bookRepresentation.contains("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#setISBN(java.lang.String)}.
   */
  @Test
  public final void testSetISBN() {
    Book book = new Book(defaultTitle, defaultPublisher, 
        defaultISBN, twoAuthors);
    book.setISBN("something else");
    assertThat(book.getISBN(), is("something else"));
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getPublisher(), is(defaultPublisher));
    assertThat(book.numAuthors(), is(2));
    assertThat(book, contains(twoAuthors));

    assertThat(book, not(is(blank)));
    String bookRepresentation = book.toString();
    assertThat (bookRepresentation, containsString(defaultTitle));
    assertThat (bookRepresentation, containsString(defaultPublisher));
    assertThat (bookRepresentation, containsString(book.getISBN()));
    assertThat (bookRepresentation, not(containsString(defaultISBN)));
    assertThat (bookRepresentation, containsString("Jones"));
    assertThat (bookRepresentation, containsString("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#setPublisher(java.lang.String)}.
   */
  @Test
  public final void testSetPublisher() {
    Book book = new Book(defaultTitle, defaultPublisher, 
        defaultISBN, twoAuthors);
    book.setPublisher("something else");
    assertThat(book.getPublisher(), is("something else"));
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getISBN(), is(defaultISBN));
    assertThat(book.numAuthors(), is(2));
    assertThat(book, contains(twoAuthors));

    assertThat(book, not(is(blank)));
    String bookRepresentation = book.toString();
    assertThat (bookRepresentation, containsString(defaultTitle));
    assertThat (bookRepresentation, containsString(book.getPublisher()));
    assertThat (bookRepresentation, containsString(defaultISBN));
    assertThat (bookRepresentation, not(containsString(defaultPublisher)));
    assertThat (bookRepresentation, containsString("Jones"));
    assertThat (bookRepresentation, containsString("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#addAuthor(edu.odu.cs.cs350.Author)}.
   */
  @Test
  public final void testAddNewAuthor() {
    Book book0 = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    Book book = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    
    book.addAuthor(doe); // a new author
    Author[] threeAuthors = {jones, smith, doe};
    assertThat(book.numAuthors(), is(3));
    assertThat(book, contains(threeAuthors));
    
    assertThat(book, is(book0)); // According to documentation of Book.equals, only the ISBN is significant
    
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getISBN(), is(defaultISBN));
    assertThat(book.getPublisher(), is(defaultPublisher));

    String bookRepresentation = book.toString();
    assertThat (bookRepresentation, containsString(defaultTitle));
    assertThat (bookRepresentation, containsString(defaultPublisher));
    assertThat (bookRepresentation, containsString(defaultISBN));
    assertThat (bookRepresentation, containsString("Doe"));
    assertThat (bookRepresentation, containsString("Jones"));
    assertThat (bookRepresentation, containsString("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#addAuthor(edu.odu.cs.cs350.Author)}.
   */
  @Test
  public final void testAddDuplicateAuthor() {
    Book book0 = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    Book book = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    
    book.addAuthor(smith); // already in the author list
    assertThat(book.numAuthors(), is(2));
    assertThat(book, contains(twoAuthors));
    assertThat(book, is(book0));
        
    assertThat(book, is(book0)); // According to documentation of Book.equals, only the ISBN is significant
    
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getISBN(), is(defaultISBN));
    assertThat(book.getPublisher(), is(defaultPublisher));

    String bookRepresentation = book.toString();
    assertThat (bookRepresentation, containsString(defaultTitle));
    assertThat (bookRepresentation, containsString(defaultPublisher));
    assertThat (bookRepresentation, containsString(defaultISBN));
    assertThat (bookRepresentation, containsString("Jones"));
    assertThat (bookRepresentation, containsString("Smith"));
  }

  /**
   * Test method for {@link edu.odu.cs.cs350.Book#clone()}.
   */
  @Test
  public final void testClone() {
    Book book0 = new Book(defaultTitle, defaultPublisher, defaultISBN, twoAuthors);
    Book book = (Book)book0.clone();

    assertThat (book, is(book0));
    assertThat(book.getTitle(), is(defaultTitle));
    assertThat(book.getISBN(), is(defaultISBN));
    assertThat(book.getPublisher(), is(defaultPublisher));
    assertThat(book.numAuthors(), is(2));
    assertThat(book, contains(twoAuthors));
    
    assertThat(book.toString(), is(book0.toString()));

    book.addAuthor(doe);
    Author[] threeAuthors = {jones, smith, doe};
    assertThat(book, contains(threeAuthors));
    assertThat(book0, contains(twoAuthors));  // changing book should not affect book0

  }

}