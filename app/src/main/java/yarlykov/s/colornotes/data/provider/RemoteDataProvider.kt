package yarlykov.s.colornotes.data.provider

import android.arch.lifecycle.LiveData
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.model.NoteResult
import yarlykov.s.colornotes.data.model.User

/**
 * TODO: Прокладка между ViewModel и источником данных.
 * TODO: Обработка запросов к источнику данных выполняются асинхронно
 * TODO: поэтому клиент будет получать в ответ LiveData и подписываться на
 * TODO: получение конечного результата.
 */
interface RemoteDataProvider {
    fun subscribeToAllNotes (): LiveData<NoteResult>
    fun getNoteById (id: String ): LiveData<NoteResult>
    fun saveNote (note: Note) : LiveData<NoteResult>
    fun getCurrentUser (): LiveData<User?>
}