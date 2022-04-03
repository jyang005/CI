package edu.odu.cs.cs350;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


/**
 * A book object as it might be recorded in a publisher's catalog or a library.
 * 
 * <p>
 * This is not a capture of the contents of the book, but of the metadata that
 * describes the book: authors, title, etc. 
 * 
 * @author zeil
 *
 */
public class Book implements Cloneable, Iterable<Author> {
  
  private String title;
  private ArrayList<Author> authors;
  private String isbn;
  private String publisher;
  

  /**
   * Create a "blank" book with empty strings for title, publisher, ISBN
   * and an empty (zero-length) list of authors.
   */
  public Book() {
    
    title = "";
    publisher = "";
    isbn = "";
    authors = new ArrayList<Author>();
  }

  /**
   * Create a new book.
   * @param title title of the book
   * @param publisher publisher of the book.
   * @param isbn ISBN identifier for the book. 
   * @param authors list of authors for this book.
   */
  public Book(String title, String publisher, String isbn, Author[] authors) {
  
    this.title = title;
    this.publisher = publisher;
    this.isbn = isbn;
    this.authors = new ArrayList<Author>(Arrays.asList(authors));
  }

  
  
  /**
   * Get the title of this book.
   * @return the title
   */
  public String getTitle() {
   
    return title;
  }

  /**
   * Set the title of this book.
   * @param title the title to set
   */
  public void setTitle(String title) {
    
    this.title = title;
  }

  /**
   * Get the ISBN of this book.
   * @return the ISBN
   */
  public String getISBN() {
    
    return isbn;
  }

  /**
   * Set the ISBN of this book.
   * @param isbn the isbn to set
   */
  public void setISBN(String isbn) {
    
    this.isbn = isbn;
  }

  /**
   * Get the publisher of this book.
   * @return the publisher
   */
  public String getPublisher() {
    
    return publisher;
  }

  /**
   * Set the publisher of this book.
   * @param publisher the publisher to set
   */
  public void setPublisher(String publisher) {
    
    this.publisher = publisher;
  }

  
  /**
   * How many authors does this book have?
   * @return number of authors
   */
  public int numAuthors() {
  
    return authors.size();
  }
  
  
  /**
   * Add an author to the end of the books's list (if that author is
   * not already in there). 
   *  
   * @param au author to be added
   */
  public void addAuthor(Author au) {
    boolean found = false;
    for (Author existingAuthor: authors) {
      if (existingAuthor.equals(au)) {
        found = true;
        break;
      }
    }
    if (!found) {
      authors.add(au);
    }
  }
  
  
  
  /**
   * Render the book as a string in a format guaranteed to
   * contain all fields.
   */
  public String toString() {
    StringBuffer buf = new StringBuffer();
    buf.append("title: ");
    buf.append(title);
    buf.append(",\nauthors: ");
    boolean firstTime = true;
    for (Author au: authors) {
      if (!firstTime) {
        buf.append(", ");
      }
      buf.append(au.toString());
      firstTime = false;
    }
    buf.append("\nISBN: ");
    buf.append(isbn);
    buf.append(",\npublished by: ");
    buf.append(publisher);
    return buf.toString();
  }
  
  

  // Comparison and hashing

  /**
   * Compares two books for equality. They are considered equal if
   * they have the same ISBN.
   *
   * @param obj object to be compared for equality with this duration
   * @return <tt>true</tt> if the specified object is equal to this one
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Book)) {
      return false;
    }
    Book other = (Book)obj;
    return isbn.equals(other.isbn);
  }

  /**
   * Returns the hash code value for this object.
   *
   * @return the hash code value for this book
   */
  public int hashCode() {
    
    return isbn.hashCode();
  }

  /**
   * Return a (deep) copy of this object.
   */
  @Override
  public Object clone()  {
    Book theClone = new Book(title, publisher, isbn, 
        new Author[0]);
    for (Author au: this) {
      theClone.addAuthor(au);
    }
   
    return theClone;
  }

  /**
   * Provide access to the list of authors. e.g.,
   *     Book book = new Book(...);
   *     for (Author au: book) {
   *        doSomethingWithAuthor (au);
   *     }
   * 
   * @return iterator over the authors.
   */
  public Iterator<Author> iterator() {
    
   return authors.iterator();
  }


}
