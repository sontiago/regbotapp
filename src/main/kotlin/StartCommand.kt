import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User
import org.telegram.telegrambots.meta.bots.AbsSender


internal class StartCommand(private val regService: RegService) : AnonymizerCommand("start", "start using bot\n") {

    /**
     * реализованный метод класса BotCommand, в котором обрабатывается команда, введенная пользователем
     * @param absSender - отправляет ответ пользователю
     * @param user - пользователь, который выполнил команду
     * @param chat - чат бота и пользователя
     * @param strings - аргументы, переданные с командой
     */
    override fun execute(absSender: AbsSender, user: User, chat: Chat, strings: Array<String>) {

        val sb = StringBuilder()

        val message = SendMessage()
        message.chatId = chat.id!!.toString()

        if (regService.addClient(Client(user, chat))) {
            println("User ${user.userName} is trying to execute '$commandIdentifier' the first time. Added to users' list.")
            sb.append("Hi, ").append(user.getUserName()).append("! You've been added to bot users' list!\n")
                .append("Please wait for updates")
        } else {
            println("User ${user.id} has already executed '${commandIdentifier}'. Is he trying to do it one more time?")
            sb.append("You've already started bot! You can send messages if you set your name (/set_name).")
        }

        message.text = sb.toString()

        regService.startWatching(absSender, chat.id!!.toString())
        execute(absSender, message, user)
    }
}