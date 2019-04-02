package yarlykov.s.colornotes.data.model

import yarlykov.s.colornotes.data.entity.Note
import java.util.*

object Repository {

    val notes = listOf(
        Note(
            UUID.randomUUID().toString(),
            "Note WHITE",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note BLUE",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note GREEN",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note PINK",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.PINK
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note RED",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note VIOLET",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.VIOLET
        ),
        Note(
            UUID.randomUUID().toString(),
            "Note YELLOW",
            "Kotlin очень краткий, но при этом выразительный язык",
            Note.Color.YELLOW
        )
    )
}