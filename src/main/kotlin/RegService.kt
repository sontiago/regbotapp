import org.jsoup.Jsoup
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender
import java.util.*
import java.util.concurrent.Executors

class RegService {
    companion object {
        private val signupUrl = "https://emias.info"
        private const val UPDATE_TIME = 10 * 60 * 1000L
    }
    private var clients: MutableSet<Client> = mutableSetOf()

    fun removeClient(user: User): Boolean {
        return clients.removeIf { client -> client.user == user }
    }

    fun addClient(anonymous: Client): Boolean {
        return clients.add(anonymous)
    }


    var bot: AbsSender? = null
    private var chatId: String? = null

    fun startWatching(absSender: AbsSender, chatId: String) {

        this.bot = absSender
        this.chatId = chatId

        val worker1 = Worker("https://gfxpeers.net/register.php")
        val worker2 = Worker("https://cgpeers.to/register.php")

        val threadPool = Executors.newFixedThreadPool(2)
        threadPool.submit(worker1)
        threadPool.submit(worker2)
    }

    inner class Worker(val url: String) : Runnable {



        val SLEEP_TIME = 10 * 60 * 1000L
        override fun run() {

            while (true) {
                val doc = Jsoup.connect(url).get()
                val containsUnavailable = doc.toString().contains("Sorry, the site is currently invite only.")
                println("Registration on $url -> "+ !containsUnavailable+" [${Date()}]")

                if(!containsUnavailable) {
                    bot?.execute(SendMessage(chatId, "Алярма, Регистрация открыта ($url)"))
                }


                Thread.sleep(SLEEP_TIME)
            }
        }


    }


}