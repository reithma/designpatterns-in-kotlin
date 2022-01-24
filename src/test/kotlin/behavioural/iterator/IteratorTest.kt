package behavioural.iterator

import org.junit.Test
import kotlin.test.assertEquals

class IteratorTest {
    @Test
    fun `check if iterators foreach works as intended`() {
        val bookTitles = mutableListOf("Lord of the Rings", "Think and grow rich", "Design Patterns")
        val books = bookTitles.map { Book(title = it) }
        books.forEachIndexed { index, book ->
            assertEquals(bookTitles[index], book.title)

        }
    }

    @Test
    fun `test iterator hasNext and Next`() {
        val bookTitles = mutableListOf("Lord of the Rings", "Think and grow rich", "Design Patterns")
        val books = bookTitles.map { Book(title = it) }
        val bookIter = books.iterator()
        assertEquals(true, bookIter.hasNext())
        val bookOne = bookIter.next()
        assertEquals(bookTitles[0], bookOne.title)
        assertEquals(true, bookIter.hasNext())
        val bookTwo = bookIter.next()
        assertEquals(bookTitles[1], bookTwo.title)
        assertEquals(true, bookIter.hasNext())
        val bookThree = bookIter.next()
        assertEquals(bookTitles[2], bookThree.title)
        assertEquals(false, bookIter.hasNext())
    }
}
