package com.library.eLibrary.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity
@Table(name = "categories")
class BookCategories (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cat_id : Int?,

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    var book: Book?,

    @Column
    var category : String?

)