package yarlykov.s.colornotes.data.model

/**
 * TODO: Класс представляет результат запроса в облачную БД.
 */
sealed class NoteResult {
    data class Success < out T >( val data : T) : NoteResult()
    data class Error ( val error: Throwable) : NoteResult()
}