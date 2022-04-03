/**
 * 
 */
package edu.odu.cs.cs350;

/**
 * An author of a book.  Currently, this contains only the author's name.
 * 
 * @author zeil
 *
 */
public class Author implements Cloneable, Comparable<Author> {

  /**
   * The surname ("family name") of this author.
   */
  private String surname;
  
  /**
   * The given name ("personal name" or "first name") of the author.
   */
  private String givenName;
  
  /**
   * Create an author.
   * @param givenName  the personal name of the author
   * @param surname the family name of the author
   */
  public Author (String givenName, String surname) {
    this.givenName = givenName;
    this.surname = surname;
  }
  
  /**
   * Compare two authors for equality.
   * 
   * @param obj another author
   * @return true ff they have the same name
   */
  public boolean equals(Object obj) {
    if (obj instanceof Author) {
      Author au = (Author) obj;
      return givenName.equals(au.givenName)
          && surname.equals(au.surname);
    } else {
      return false;
    }
  }
  
  /**
   * Get the name of the author in a form suitable for sorting.
   *     surname, givenName
   * @return sortable name of the author
   */
  public String getSortingName() {
    return surname + ", " + givenName;
  }
  
  
  /**
   * Return the author's name in conventional form.
   *    givenName surname
   */
  public String toString() {
    return givenName + " " + surname;
  }
  
  /**
   * Return the surname of this author
   * @return the surname
   */
  public String getSurname() {
    return surname;
  }
  
  /**
   * Return the given name of this author 
   * @return the given name
   */
  public String getGivenName() {
    return givenName;
  }
  
  
  /**
   * Compare this author to another, returning a negative
   * value if this author should follow the other in a sorted list,
   * zero if they are equal, and a positive value if this author should
   * precede the other in a sorted list.
   * 
   * @param au the other author to be compared against.
   * @return number whose sign indicates the comparison result
   */
  public int compareTo(Author au) {
    return getSortingName().compareTo(au.getSortingName());
  }
  
  

}