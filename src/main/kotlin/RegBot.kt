
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.objects.Update

class RegBot : TelegramLongPollingCommandBot {

    lateinit var service: RegService

    constructor(): super() {

        println("Initializing Anonymizer Bot...")

        println("Initializing anonymouses list...")
        service = RegService()

        // регистрация всех кастомных команд
        println("Registering commands...");
        println("Registering '/start'...");
        register(StartCommand(service))

    }


    override fun processNonCommandUpdate(update: Update?) {

    }

    override fun getBotUsername(): String {
        return "REg bot"
    }
    override fun getBotToken(): String {
        return "1263819847:AAF2tBre9ZC0vMWteAtiD_Sdn0UpPiB__aM"
    }

}
