package org.jesperancinha.books.rest

import org.jesperancinha.books.dao.BookRepositorySearchDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller(
    val bookRepositorySearchDao: BookRepositorySearchDao
) {

    @GetMapping("{volume}")
    suspend fun getSpecificVolume(
        @PathVariable("volume") volume:String) = bookRepositorySearchDao.findBookByVolune(volume)
}