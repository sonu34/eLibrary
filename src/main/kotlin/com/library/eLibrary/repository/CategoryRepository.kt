package com.library.eLibrary.repository

import com.library.eLibrary.entity.BookCategories
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<BookCategories, Int>