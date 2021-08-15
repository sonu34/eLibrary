package com.library.eLibrary.repository

import com.library.eLibrary.entity.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Int> {

    @Query(value = "FROM Book where author_id = :uId")
    fun findByAuthorId(@Param("uId") id:Int) : MutableIterable<Book>

    @Query(value = "SELECT * FROM book where author_id = :uId", countQuery = "SELECT count(*) FROM book", nativeQuery=true)
    fun findPageByAuthorId(@Param("uId") id:Int, pageable: Pageable) : Page<Book>

    @Query(value = "SELECT * FROM book where name like %:keyword%", countQuery = "SELECT count(*) FROM book", nativeQuery=true)
    fun findByNameContaining(@Param("keyword") keyword:String, pageable: Pageable) : Page<Book>

}