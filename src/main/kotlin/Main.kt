
import org.jsoup.Jsoup
import org.telegram.telegrambots.ApiContextInitializer
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException
import java.util.concurrent.Executors


class Main {
    companion object {




        @JvmStatic
        fun main(args: Array<String>) {
            try {

                println("Initializing API context...")
                ApiContextInitializer.init()

                val botsApi = TelegramBotsApi()

                println("Registering Anonymizer...")
                botsApi.registerBot(RegBot())

                println("Anonymizer bot is ready for work!")

            } catch (e: TelegramApiRequestException) {
                println("Error while initializing bot! $e")
            }
        }
    }
}