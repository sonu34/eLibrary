package com.library.eLibrary.controller

import com.library.eLibrary.entity.Book
import com.library.eLibrary.service.ServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api")
@EnableCaching
open class BookController {

    @Autowired
    lateinit var serviceImpl: ServiceImpl

    @GetMapping("/books")
    open fun getAllBooks() : MutableIterable<Book> {
        return serviceImpl.getBooks()
    }

    @GetMapping("/books/{page}")
    open fun getPageOfTextualSearchBooks(@PathVariable("page") page:Int, @RequestParam keyword:String) : Page<Book> {
        return serviceImpl.getPageOfTextualSearchBooks(page, keyword)
    }

    @PostMapping("/add", consumes = arrayOf(MediaType.ALL_VALUE))
    open fun addBook(@Validated @RequestBody book: Book) : String {
        return serviceImpl.addBookInLibrary(book)
    }

    @DeleteMapping("/del/{b_id}")
    open fun removeBook(@PathVariable b_id:Int) : Book {
        return serviceImpl.deleteBookFromLibrary(b_id)
    }

}