package com.library.eLibrary.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "book")
class Book (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var book_id: Int? = null,

    @Column(name="name")
    var name: String?,

    @Column(name="isbn_no")
    var isbn_no: String?,

    @Column
    @JsonFormat(pattern = "YYYY-MM-DD")
    var create_at : Date?,

    @Column
    var no_of_pages: Int?,

    @Column
    var book_pdf: String?,

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "book", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    var categories : Set<BookCategories>?,

//    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    var author : Author?

)