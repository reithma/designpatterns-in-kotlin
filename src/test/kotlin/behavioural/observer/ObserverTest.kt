package behavioural.observer

import org.assertj.core.api.Assertions
import org.junit.Test

class ObserverTest {
    @Test
    fun `test observer objects`() {
        val editor = Editor()
        editor.events.subscribe(EventTypes.OPEN, LoggingListener("logging.log"))
        editor.events.subscribe(EventTypes.UPDATE, EmailPushListener("test@test.at"))
        editor.events.subscribe(EventTypes.CLOSE, LoggingListener("logging.log"))
        editor.events.subscribe(EventTypes.CLOSE, EmailPushListener("test@test.at"))

        editor.openFile("test.txt")
        editor.updateFile()
        editor.closeFile()
    }

    @Test
    fun `test listeners objects`() {
        val editor = Editor()
        val loggingListener = LoggingListener("/var/log/logging.log")
        val emailPushListener = EmailPushListener("test@test.at")
        editor.events.subscribe(EventTypes.OPEN, loggingListener)
        editor.events.subscribe(EventTypes.UPDATE, emailPushListener)
        editor.events.subscribe(EventTypes.CLOSE, emailPushListener)
        editor.events.subscribe(EventTypes.CLOSE, loggingListener)

        Assertions.assertThat(loggingListener.loggingStatements.size).isEqualTo(0)
        editor.openFile("./big/filepath/test.txt")
        Assertions.assertThat(loggingListener.loggingStatements.size).isEqualTo(1)
        editor.updateFile()
        Assertions.assertThat(loggingListener.loggingStatements.size).isEqualTo(1)
        Assertions.assertThat(emailPushListener.emailList.size).isEqualTo(1)
        editor.closeFile()
        Assertions.assertThat(loggingListener.loggingStatements.size).isEqualTo(2)
        Assertions.assertThat(emailPushListener.emailList.size).isEqualTo(2)
    }
}
