package library;

import datastructures.queues.CustomQueue;
import management.MemberManager;

import java.util.NoSuchElementException;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    private CustomQueue<Member> waitList;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
        this.waitList = new CustomQueue<>();
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public void addToWaitlist(Member member) {
        if (member == null) {
            throw new NullPointerException("Member cannot be null");
        }
        waitList.add(member);
    }

    public Member getNextInWaitlist() {
        return waitList.poll();
    }

    public boolean hasWaitlist() {
        return !waitList.isEmpty();
    }

    public void addToWaitlist(String memberId, MemberManager memberManager) {
        if (memberId == null) {
            throw new NullPointerException("Member ID cannot be null");
        }
        Member member = memberManager.getMember(memberId);
        if (member != null) {
            addToWaitlist(member);
        }
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + isAvailable +
                '}';
    }
}