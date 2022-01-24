package behavioural.iterator

class Book(val title: String)

class Books(val books: MutableList<Book> = mutableListOf()) : Iterable<Book> {
    override fun iterator(): Iterator<Book> = BookIterator(books)
}

/**
 * The iterator pattern is used when the implementation of traversing a structure needs to be hidden from the user
 */
class BookIterator(val books: MutableList<Book> = mutableListOf(), var index: Int = 0) : Iterator<Book> {
    override fun hasNext(): Boolean = books.size > index

    override fun next(): Book {
        val book = books[index]
        index++
        return book
    }
}

