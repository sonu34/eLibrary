package com.library.eLibrary.service

import com.library.eLibrary.entity.Author
import com.library.eLibrary.entity.Book
import com.library.eLibrary.entity.BookCategories
import com.library.eLibrary.repository.AuthorRepository
import com.library.eLibrary.repository.BookRepository
import com.library.eLibrary.repository.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
open class ServiceImpl {

    @Autowired
    lateinit var bookRepository : BookRepository

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var authorRepository: AuthorRepository

    open fun getBooks() : MutableIterable<Book> {
        return bookRepository.findAll()
    }

    open fun addBookInLibrary(book: Book) : String {

        val bookData : Book = Book(null, book.name, book.isbn_no, book.create_at, book.no_of_pages, book.book_pdf, Collections.emptySet(), null)

        val booksAtDb : MutableIterable<Book> = bookRepository.findAll()

        for(it in booksAtDb) {
            if(book.isbn_no.equals(it.isbn_no)) {
                return "book already in library"
            }
        }

        var auth:Optional<Author> = authorRepository.findAuthorByName(book.author?.name!!)


        if(auth.isPresent) {
            bookData.author = auth.get()
        } else {
            val authorD :Author = Author(null, "", null)
            authorD.name = book.author?.name
            val authorRes = authorRepository.save(authorD)
            bookData.author = authorRes
        }

        val bookRet : Book = bookRepository.save(bookData)

        for (bookCate:BookCategories in book.categories!!) {
            bookCate.book = bookRet
            categoryRepository.save(bookCate)
        }

        return "success"
    }

    open fun deleteBookFromLibrary(b_id:Int) : Book {
        var book:Optional<Book> = bookRepository.findById(b_id)
        if(book.isPresent) {
            bookRepository.deleteById(b_id)
        }
        return book.get()
    }

    @Cacheable(value = arrayOf("pagedata"), key = "#page")
    open fun getPageOfTextualSearchBooks(page:Int, keyword:String) : Page<Book> {
        val pageable: Pageable = PageRequest.of(page, 1)
        val ans : Page<Book> = bookRepository.findByNameContaining(keyword, pageable)
        return ans
    }

}