import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.User

class Client(val user: User, val chat: Chat) {
    var displayedName: String? = null

    override fun hashCode(): Int {
        return user.hashCode()
    }

    override fun equals(obj: Any?): Boolean {
        return obj is Client && obj.user == user
    }
}