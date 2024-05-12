package com.project.Library.Management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "publication_year")
    private String publicationYear;
    @Column(name = "isbn")
    private String isbn;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private Set<BorrowingRecord> borrowingRecords;
    @ManyToMany
    @JoinTable(name = "book_patron",joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "patron_id"))
    private Set<Patron> patrons;

    public Book(String title, String author, String publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public Book(Long id, String title, String author, String publicationYear, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public void addBorrowingRecord(BorrowingRecord borrowingRecord){
        if(borrowingRecords == null){
            borrowingRecords = new HashSet<>();
        }
        borrowingRecords.add(borrowingRecord);
        borrowingRecord.setBook(this);
    }
}
