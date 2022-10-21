import java.util.NoSuchElementException;

//On my honor:
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project
//with anyone other than my partner (in the case of a joint
//submission), instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
//
//-- Gabriel Escobar (gabrielse)
//-- Liam McBride (mailmcbride)

/**
 * @author OpenDSA
 * 
 * @modifier gabrielescobar
 * @modifier Liam McBride 
 *
 * @version 2022.09.30
 */
class LList {
  private Link head;         // Pointer to list header
  private Link tail;         // Pointer to last element
  private Link curr;         // Access to current element
  private int listSize;      // Size of list

  public LList() {
      clear();
  }

  /**
   * Removes all elements from
   * the LinkedList
   */
  public void clear() {
    curr = tail = new Link(null);
    head = new Link(tail); 
    listSize = 0;
  }
  
  /**
   * Appends integers to the end 
   * of the LinkList
   * 
   * @param it
   *    the integer to be appended
   *    
   * @return true 
   *    returns true if the integer 
   *    is successfully added
   *    to the LinkList
   */
  public boolean append(HashObject it) {
    tail.setNext(new Link(null));
    tail.setElement(it);
    tail = tail.next();
    listSize++;
    return true;
  }

  /**
   * Removes an element and returns
   * the current element
   *    
   * @return it 
   *    returns the current element in 
   *    the LinkedList
   */
  public HashObject remove () throws NoSuchElementException {
    if (curr == tail)
      throw new NoSuchElementException("remove() in LList has current of " + curr + " and size of "
        + listSize + " that is not a a valid element");
    HashObject it = curr.element();             // Remember value
    curr.setElement(curr.next().element()); // Pull forward the next element
    curr.setNext(curr.next().next());       // Point around unneeded link
    listSize--;                             // Decrement element count
    return it;                              // Return value
  }

  /**
   * Moves an element to the start
   * of the LinkedList
   */
  public void moveToStart() { 
      curr = head.next();
  }
  
  /**
   * Moves an element to the end
   * of the LinkedList
   */
  public void moveToEnd() {
      curr = tail;
      prev();
  }         

  /**
   * Moves the current pointer to the
   * left one and doesn't change if 
   * you are at the first element/front 
   * of the LinkedList
   */
  public void prev() {
    if (head.next() == curr) return;
    Link temp = head;
    while (temp.next() != curr) temp = temp.next();
    curr = temp;
  }

  /**
   * Moves the current pointer to the
   * right one and doesn't change if 
   * you are at the last element/end 
   * of the LinkedList
   */
  public void next() { 
      if (curr != tail) {
          curr = curr.next();
      }
  }

  /**
   * Returns the length of the LinkedList
   * 
   * @return listSize
   *    size of the LinkedList
   */
  public int length() { 
      return listSize;
  } // Return list length


  /**
   * Returns the position of the 
   * current element
   * 
   * @return i
   *    position of the current element
   */
  public int currPos() {
    Link temp = head.next();
    int i;
    for (i=0; curr != temp; i++)
      temp = temp.next();
    return i;
  }
  
  /**
   * Move down list to "pos" position
   * 
   * @param pos
   *    position to be moved to
   * 
   * @return true
   *    returns true if it was able to
   *    move to "pos" position
   */
  public boolean moveToPos(int pos) {
    if ((pos < 0) || (pos > listSize)) return false;
    curr = head.next();
    for(int i=0; i<pos; i++) curr = curr.next();
    return true;
  }

  /**
   * Return true if current position 
   * is at end of the list
   * 
   * @return true or false
   *    returns whether 
   *    curr is equal to tail
   */
  public boolean isAtEnd() { return curr == tail; }

  /**
   * Returns value of the current element
   * 
   * @throws NoSuchElementException
   *    throws an exception if there is
   *    no valid element
   * 
   * @return curr.element
   *    returns the current element
   *    value
   */
  public HashObject getValue() throws NoSuchElementException {
    if (curr == tail) // No current element
      throw new NoSuchElementException("getvalue() in LList has current of " + curr + " and size of "
        + listSize + " that is not a a valid element\n" + this.toString());
    return curr.element(); 
  }
  
  /**
   * Returns the LinkedList as a 
   * String
   * 
   * @return output
   *    returns the LinkedList
   *    as a String
   */
  public String toString() {
      String output = "";
      
      for(int i = length() - 1; i >= 0; i--) {
          moveToPos(i);
          output += getValue().toString() + " ";              
          
      }
      
      
      return output;
  }
  
  /**
   * Returns whether to LinkedLists are
   * equal to each other
   * 
   * @param l2
   *    the LinkedList the current 
   *    LinkedList is being compared to
   * 
   * @return true
   *    returns true if they are equal
   *    to each other
   */
  public boolean isEqual(LList l2) {
      
      if(l2 == null || this.length() != l2.length()) {
          return false;
      }
      
      for(int i = 0; i < this.length(); i++) {
          if(this.getValue() != l2.getValue()) {
              l2.moveToStart();
              this.moveToStart();
              return false;
          }
          this.next();
          l2.next();
      }
      
      l2.moveToStart();
      this.moveToStart();
      return true;
  }

  /**
   * Returns whether the LinkList 
   * is empty/List size is zero
   * 
   * @return true or false
   *    returns true if the list size 
   *    is equal to zero
   */
  public boolean isEmpty() { return listSize == 0; }
  
  public LList deepCopy() {
      
      LList newList = new LList();
      
      while(!this.isAtEnd()) {
          newList.append(this.getValue());
          this.next();
      }
      
      this.moveToStart();
      
      return newList;
  }
  
  
  /**
   * Link class to be used 
   * for the LinkedList
   */
  class Link {         // Singly linked list node class
      private HashObject e;  // Value for this node
      private Link n;    // Point to next node in list

      /**
       * Link constructor with two 
       * parameters
       * 
       * @param it
       *    value of the new link
       * 
       * @param inn
       *    the next node the list 
       *    should point to
       */
      Link(HashObject it, Link inn) { e = it; n = inn; }
      
      /**
       * Link constructor with one 
       * parameter
       * 
       * @param inn
       *    the next node the list 
       *    should point to
       */
      Link(Link inn) { e = null; n = inn; }

      /**
       * Returns the value of 
       * the link
       * 
       * @return e
       *    the value of the link
       */
      HashObject element() { return e; }                  // Return the value
      
      /**
       * Sets a new value for 
       * the link
       * 
       * @param it
       *    new value of 
       *    the link
       * 
       * @return e
       *    the new value of 
       *    the link
       */
      HashObject setElement(HashObject it) { return e = it; } // Set element value
      
      /**
       * Returns the next element in the list
       * 
       * @return n
       *    the next element in the 
       *    list
       */
      Link next() { return n; }                       // Return next link
      
      /**
       * Sets the next Link in 
       * the list
       * 
       * @param inn
       *    the next link to be set
       * 
       * @return n
       *    the next link
       */
      Link setNext(Link inn) { return n = inn; }      // Set next link
    }
  
}