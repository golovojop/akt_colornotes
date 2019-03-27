package yarlykov.s.colornotes.data.entity

/**
 * Основной бизнес-компонент приложения.
 * Будем создавать, редактироваться заметки.
 */
data class Note ( val title: String, val note: String, val color: Int )