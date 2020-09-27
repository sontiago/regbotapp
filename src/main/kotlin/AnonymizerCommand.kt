import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import org.telegram.telegrambots.meta.exceptions.TelegramApiException


abstract class AnonymizerCommand(commandIdentifier: String, description: String) : BotCommand(commandIdentifier, description) {

    fun execute(sender: AbsSender, message: SendMessage, user: User) {
        try {
            sender.execute(message)
        } catch (e: TelegramApiException) {
            e.printStackTrace()
        }

    }
}
