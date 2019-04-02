package yarlykov.s.colornotes.data.model

import yarlykov.s.colornotes.data.entity.Note
import java.util.*

object Repository {

    val notes = listOf(
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.PINK
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.VIOLET
        ),
        Note(
            UUID.randomUUID().toString(),
            "Моя первая заметка",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.YELLOW
        )
    )
}