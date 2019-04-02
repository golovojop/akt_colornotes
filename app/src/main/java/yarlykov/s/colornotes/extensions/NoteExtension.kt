package yarlykov.s.colornotes.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.entity.Note.*

fun Note.Color.getColorId(c: Note.Color): Int =
    when (c) {
        Color.YELLOW -> R.color.color_yello
        Color.RED -> R.color.color_red
        Color.PINK -> R.color.color_pink
        Color.GREEN -> R.color.color_green
        Color.BLUE -> R.color.color_blue
        else -> {
            R.color.color_white
        }
    }

fun Note.Color.getColor(context: Context, c: Note.Color): Int =
    ContextCompat.getColor(context, getColorId(c))


