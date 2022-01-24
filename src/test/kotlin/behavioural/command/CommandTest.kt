package behavioural.command

import org.assertj.core.api.Assertions
import org.junit.Test

class CommandTest {
    @Test
    fun `basic command test`() {
        val book = Item(itemId = 100L, price = 14.5)
        val bookOrder = Order(orderId = 0L)
        val commandProcessor = OrderCommandProcessor()

        commandProcessor.addToQueue(OrderAddCommand(order = bookOrder, itemId = book.itemId, amount = 2))
        commandProcessor.addToQueue(OrderAddCommand(order = bookOrder, itemId = book.itemId, amount = 3))
        commandProcessor.addToQueue(OrderPayCommand(order = bookOrder))

        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(0)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(null)

        commandProcessor.processCommands()
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(1)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(5)
        Assertions.assertThat(bookOrder.isPayed).isTrue

        commandProcessor.revertCommand(2)
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(1)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(2)
        Assertions.assertThat(bookOrder.isPayed).isFalse

        commandProcessor.revertCommand(1)
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(0)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(null)

    }

    @Test
    fun `mixed command test`() {
        val book = Item(itemId = 100L, price = 14.5)
        val tv = Item(itemId = 200L, price = 1399.95)
        val bookOrder = Order(orderId = 0L)
        val tvOrder = Order(orderId = 1L)
        val commandProcessor = OrderCommandProcessor()

        commandProcessor.addToQueue(OrderAddCommand(order = bookOrder, itemId = book.itemId, amount = 2))
        commandProcessor.addToQueue(OrderAddCommand(order = tvOrder, itemId = tv.itemId, amount = 1))
        commandProcessor.addToQueue(OrderAddCommand(order = bookOrder, itemId = book.itemId, amount = 3))
        commandProcessor.addToQueue(OrderPayCommand(order = bookOrder))

        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(0)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(null)


        commandProcessor.processCommands()
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(1)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(5)
        Assertions.assertThat(bookOrder.items[tv.itemId]).isEqualTo(null)
        Assertions.assertThat(bookOrder.isPayed).isTrue

        Assertions.assertThat(tvOrder.items.keys.size).isEqualTo(1)
        Assertions.assertThat(tvOrder.items[book.itemId]).isEqualTo(null)
        Assertions.assertThat(tvOrder.items[tv.itemId]).isEqualTo(1)
        Assertions.assertThat(tvOrder.isPayed).isFalse


        commandProcessor.revertCommand(2)
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(1)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(2)
        Assertions.assertThat(bookOrder.isPayed).isFalse

        commandProcessor.revertCommand(5)
        Assertions.assertThat(bookOrder.items.keys.size).isEqualTo(0)
        Assertions.assertThat(bookOrder.items[book.itemId]).isEqualTo(null)
    }
}
