package yarlykov.s.colornotes.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.entity.Note.*

/**
 * TODO: Получить имя ресурса
 */
fun Note.Color.getColorRes() =
    when (this) {
        Color.YELLOW -> R.color.yellow
        Color.RED -> R.color.red
        Color.PINK -> R.color.pink
        Color.GREEN -> R.color.green
        Color.BLUE -> R.color.blue
        Color.VIOLET -> R.color.violet
        else -> {
            R.color.white
        }
    }

/**
 * TODO: Сконвертировать имя ресурса (R.color.yellow) в его уникальный (id: Int)
 */
fun Note.Color.getColorInt(context: Context): Int =
    ContextCompat.getColor(
        context, when (this) {
            Color.YELLOW -> R.color.yellow
            Color.RED -> R.color.red
            Color.PINK -> R.color.pink
            Color.GREEN -> R.color.green
            Color.BLUE -> R.color.blue
            Color.VIOLET -> R.color.violet
            else -> {
                R.color.white
            }
        }
    )
