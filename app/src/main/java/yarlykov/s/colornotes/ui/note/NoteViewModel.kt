package yarlykov.s.colornotes.ui.note

import android.arch.lifecycle.ViewModel
import android.view.ViewManager
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.model.Repository

class NoteViewModel(private val repository: Repository = Repository) : ViewModel() {

    // Заметка, ожидающая записи
    private var pendingNote: Note? = null

    fun saveChanges(note: Note) {
        pendingNote = note
    }

    /**
     * TODO: Очистка ресурсов при уничтожении активити NoteActivity
     */
    override fun onCleared() {
        pendingNote?.let {
            repository.saveNote(pendingNote!!)
        }
    }
}