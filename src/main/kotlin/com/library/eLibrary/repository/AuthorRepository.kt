package com.library.eLibrary.repository

import com.library.eLibrary.entity.Author
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AuthorRepository : JpaRepository<Author, Int> {

    override fun findAll(pageable: Pageable): Page<Author>

    @Query(value = "from Author where name=:author_name")
    fun findAuthorByName(@Param("author_name") authorName:String) : Optional<Author>

}