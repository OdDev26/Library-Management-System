package com.project.Library.Management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patrons")
@Data
@NoArgsConstructor
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone",unique = true)
    private String phone;
    @Column(name = "username",unique = true)
    private String username;
    @OneToMany(mappedBy = "patron",cascade = CascadeType.ALL)
    private Set<BorrowingRecord> borrowingRecords;
    @ManyToMany
    @JoinTable(name = "book_patron",joinColumns = @JoinColumn(name = "patron_id"),
               inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books;

    public Patron(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Patron(String firstName, String lastName, String email, String phone, String username) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.username = username;
    }

    public Patron(Long id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public void addBook(Book book){
        if (books == null){
            books = new HashSet<>();
        }
        books.add(book);
    }
    public void addBorrowingRecord(BorrowingRecord borrowingRecord){
        if(borrowingRecords == null){
            borrowingRecords = new HashSet<>();
        }
        borrowingRecords.add(borrowingRecord);
        borrowingRecord.setPatron(this);
    }
}
