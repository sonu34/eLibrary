package com.library.eLibrary.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table(name = "authors")
class Author (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var author_id : Int?,

    @Column
    var name:String?,

    @JsonIgnore
    @OneToMany(mappedBy = "author", cascade = arrayOf(CascadeType.PERSIST), fetch = FetchType.LAZY)
    var books : Set<Book>?

)