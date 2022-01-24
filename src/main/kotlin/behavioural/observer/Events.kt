package behavioural.observer

import java.io.File

enum class EventTypes(val stringRep: String) {
    OPEN("open"), UPDATE("update"), CLOSE("close")
}

interface EventListener {
    fun update(eventType: EventTypes?, file: File?)
}

/**
 * the observer pattern allows a component to be a subscriber of a service.
 * it allows notifying as many objects as there are subscribers
 * maps one to many relationship
 */
class EventManager(vararg operations: EventTypes) {
    var listeners = hashMapOf<EventTypes, ArrayList<EventListener>>()

    init {
        for (operation in operations) {
            listeners[operation] = ArrayList<EventListener>()
        }
    }

    fun subscribe(eventType: EventTypes?, listener: EventListener) {
        val users = listeners[eventType]
        users?.add(listener)
    }

    fun unsubscribe(eventType: EventTypes?, listener: EventListener) {
        val users = listeners[eventType]
        users?.remove(listener)
    }

    fun notify(eventType: EventTypes?, file: File?) {
        val users = listeners[eventType]
        users?.forEach {
            it.update(eventType = eventType, file = file)
        }
    }
}

class Editor {
    var events = EventManager(EventTypes.OPEN, EventTypes.UPDATE, EventTypes.CLOSE)
    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify(EventTypes.OPEN, file)
    }

    fun updateFile() {
        file?.let {
            events.notify(EventTypes.UPDATE, it)
        }
    }

    fun closeFile() {
        file?.let {
            events.notify(EventTypes.CLOSE, it)
        }
    }
}

class EmailPushListener(private val emailAddress: String) : EventListener {
    val emailList = mutableListOf<String>()
    override fun update(eventType: EventTypes?, file: File?) {
        println("E-Mail to $emailAddress: Action '${eventType?.stringRep}' has been performed on File ${file?.name}")
        emailList
            .add(("E-Mail to $emailAddress: Action '${eventType?.stringRep}' has been performed on File ${file?.name}"))
        when(eventType) {
            EventTypes.OPEN -> println("Mail-FOO")
            EventTypes.UPDATE -> println("Mail-BAR")
            EventTypes.CLOSE -> println("MAIL-BAZ")
            else -> throw IllegalArgumentException("Unknown eventtype")
        }
    }
}

class LoggingListener(private val filename: String) : EventListener {
    val loggingStatements = mutableListOf<String>()
    override fun update(eventType: EventTypes?, file: File?) {
        println("Logging to $filename: Action '${eventType?.stringRep}' has been performed on File ${file?.name}")
        loggingStatements
            .add("Logging to $filename: Action '${eventType?.stringRep}' has been performed on File ${file?.name}")
        when(eventType) {
            EventTypes.OPEN -> println("Logger-FOO")
            EventTypes.UPDATE -> println("Logger-BAR")
            EventTypes.CLOSE -> println("Logger-BAZ")
            else -> throw IllegalArgumentException("Unknown eventtype")
        }
    }
}
