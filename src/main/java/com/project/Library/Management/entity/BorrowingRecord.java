package com.project.Library.Management.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;

@Entity
@Table(name = "borrowing_records")
@Data
@NoArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "patron_id")
    private Patron patron;
    @Column(name = "is_returned")
    private boolean isReturned;
    @Column(name = "return_date")
    private Date returnDate;
    @CurrentTimestamp
    private Date borrowingDate;

    public BorrowingRecord(Date returnDate) {
        this.returnDate = returnDate;
    }
}
