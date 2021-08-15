package com.library.eLibrary.service

import com.library.eLibrary.entity.Author
import com.library.eLibrary.repository.AuthorRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
//@WebMvcTest(controllers = [AuthorController::class])
@ExtendWith(MockKExtension::class)
class AuthorServiceImplTest () {

    @Autowired
    lateinit var authorServiceImpl:AuthorServiceImpl

    @MockK
    lateinit var authorRepository: AuthorRepository

//    @InjectMockKs
//

//    @BeforeEach
//    fun setUp() = MockKAnnotations.init(this)


    //UNIT TESTS NOT WORKING

    @Test
    fun testGetAllAuthor() {
//        val authorRepo:AuthorRepository = mockk()
        val authorRepos = mockk<AuthorRepository>()
        val temp:MutableList<Author> = Collections.emptyList()
        every { authorRepository.findAll() } returns Collections.emptyList()
//        val classmock = mockkClass(AuthorServiceImpl::class)
//        val classmockk = mockk<AuthorServiceImpl>()
        val expected:MutableList<Author> = authorServiceImpl.getAllAuthor()
        println(temp)
        println(expected)
        Assertions.assertEquals(temp, expected)
    }

}