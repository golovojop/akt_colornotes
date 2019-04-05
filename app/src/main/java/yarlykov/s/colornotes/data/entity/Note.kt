package yarlykov.s.colornotes.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * TODO: Основной бизнес-компонент приложения.
 * TODO: Так как заметки будут храниться в облаке, то класс
 * TODO: должен иметь конструктор по умолчанию, чтобы парсинг
 * TODO: принимаемых из базы объектов мог сконвертировать их в Note.
 */
@Parcelize
data class Note ( val id: String = "",
                  val title: String ="",
                  val text: String ="",
                  val background: Color = Color.WHITE,
                  val lastChanged: Date = Date()): Parcelable {

    override fun equals (other: Any ?): Boolean {
        // Проверка по ссылке
        if ( this === other) return true
        // Проверка по классу. Фактически это вот так работает:
        // Получаем инстансы Class и делаем equal()
        // if (other?.javaClass != this.javaClass) return false
        if (other?.javaClass != javaClass) return false

        other as Note
        if (id != other.id) return false
        return true
    }

    override fun hashCode (): Int {
        return id.hashCode()
    }

    /**
     * Палитра цветов
     */
    enum class Color {
        WHITE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
        PINK
    }
}