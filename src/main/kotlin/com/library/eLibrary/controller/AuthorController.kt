package com.library.eLibrary.controller

import com.library.eLibrary.entity.Author
import com.library.eLibrary.entity.Book
import com.library.eLibrary.repository.AuthorRepository
import com.library.eLibrary.repository.BookRepository
import com.library.eLibrary.service.AuthorServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/author")
open class AuthorController {

    @Autowired
    lateinit var authorServiceImpl: AuthorServiceImpl

    @GetMapping("/all")
    open fun getAllAuthor() : MutableList<Author> {
        return authorServiceImpl.getAllAuthor()
    }

    @GetMapping("/data/{page}")
    open fun getPageOfAuthor(@PathVariable("page") page:Int) : Page<Author> {
        return authorServiceImpl.getPageOfAuthor(page)
    }

    @GetMapping("/auth/{name}")
    open fun getListOfBookByAuthor(@PathVariable name:String) : MutableIterable<Book> {
        return authorServiceImpl.getListOfBookByAuthor(name)
    }

    @GetMapping("/pauthor/{page}")
    open fun getPageOfBooksByAuthor(@PathVariable("page") page: Int, @RequestParam(required = false, defaultValue = "") name:String) : Page<Book> {
        return authorServiceImpl.getPageOfBooksByAuthor(page, name)
    }

}