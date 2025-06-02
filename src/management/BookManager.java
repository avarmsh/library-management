package management;

import datastructures.maps.CustomHashMap;
import library.Book;
import library.Member;

public class BookManager {
    private CustomHashMap<String, Book> books;
    private MemberManager memberManager;

    public BookManager(MemberManager memberManager) {
        this.books = new CustomHashMap<>();
        this.memberManager = memberManager;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book field is null");
        }
        books.put(book.getIsbn(), book);
    }

    public Book getBookByIsbn(String isbn) {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN field is null");
        }
        return books.get(isbn);
    }

    public boolean isBookAvailable(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book != null) {
            return book.isAvailable();
        } else {
            throw new IllegalArgumentException(isbn + " not found");
        }
    }

    public void setBookAvailability(String isbn, boolean available) {
        Book book = getBookByIsbn(isbn);
        if (book != null) {
            book.setAvailable(available);
        } else {
            throw new IllegalArgumentException(isbn + " not found");
        }
    }

    public void addToWaitlist(String isbn, String memberId) {
        Book book = getBookByIsbn(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book " + isbn + " not found");
        }

        Member member = memberManager.getMember(memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member " + memberId + " not found");
        }

        book.addToWaitlist(member);
    }

    public Member getNextFromWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book == null) {
            throw new IllegalArgumentException("Book " + isbn + " not found");
        }
        return book.getNextInWaitlist();
    }

    public boolean hasWaitlist(String isbn) {
        Book book = getBookByIsbn(isbn);
        if (book != null) {
           return book.hasWaitlist();
        } else {
            throw new IllegalArgumentException("Book" + isbn + " not found");
        }
    }
}