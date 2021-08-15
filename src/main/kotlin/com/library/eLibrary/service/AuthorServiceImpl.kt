package com.library.eLibrary.service

import com.library.eLibrary.entity.Author
import com.library.eLibrary.entity.Book
import com.library.eLibrary.repository.AuthorRepository
import com.library.eLibrary.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
open class AuthorServiceImpl {

    @Autowired
    lateinit var authorRepository: AuthorRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    open fun getAllAuthor() : MutableList<Author> {
        return authorRepository.findAll()
    }

    open fun getPageOfAuthor(page : Int) : Page<Author> {

        val pageable:Pageable = PageRequest.of(page, 3)
        val pageData:Page<Author> = authorRepository.findAll(pageable)
        return pageData

    }

    open fun getListOfBookByAuthor(name:String) : MutableIterable<Book> {

        val author:Optional<Author> = authorRepository.findAuthorByName(name)
        var id:Int=0
        if(author.isPresent) {
            id=author.get().author_id!!
        }
        val ans:MutableIterable<Book> = bookRepository.findByAuthorId(id)

        return ans
    }

    open fun getPageOfBooksByAuthor(page: Int, name:String) : Page<Book> {

        val author:Optional<Author> = authorRepository.findAuthorByName(name)
        var id:Int=0
        if(author.isPresent) {
            id=author.get().author_id!!
        }

        val pageable:Pageable = PageRequest.of(page, 2)

        val ans:Page<Book> = bookRepository.findPageByAuthorId(id, pageable)

        return ans
    }

}