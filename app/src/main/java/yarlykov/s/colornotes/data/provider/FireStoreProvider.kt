package yarlykov.s.colornotes.data.provider

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.model.NoteResult
import yarlykov.s.colornotes.data.model.User
import yarlykov.s.colornotes.exception.NoAuthException

/**
 * TODO: Название корневой коллекции заметок в FireStore.
 * TODO: Коллекция будет создана автоматически при первом запросе к ней
 */
private const val NOTES_COLLECTION = "notes"
private const val USERS_COLLECTION = "users"

class FireStoreProvider : RemoteDataProvider {

    // TODO: Для логирования
    private val TAG = " ${FireStoreProvider::class.java.simpleName}:"
    // TODO: Получить ссылку на базу
    private val db = FirebaseFirestore.getInstance()
    // TODO: Получить ссылку на корневую коллекцию
    private val notesCollection = db.collection(NOTES_COLLECTION)
    // TODO: Пользователь Firebase
    private val currentUser
        get () = FirebaseAuth.getInstance().currentUser

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>().apply {

            // TODO: Для обработки исключения из метода getUserNotesCollection()
            // TODO: используем try/catch
            try {
                getUserNotesCollection().addSnapshotListener { querySnapshot, error ->
                    value = error?.let { throw it }
                        ?: querySnapshot?.let { userNotes ->
                            val notes = userNotes.documents.map { it.toObject(Note::class.java) }
                            NoteResult.Success(notes)
                        }
                }
            } catch (e: Throwable) {
                value = NoteResult.Error(e)
            }

//        return MutableLiveData<NoteResult>().apply {
//
//            // TODO: Поставить колбек на КОЛЛЕКЦИЮ в БД !!!
//            // TODO: Очень удобно. Получим снэпшот всей коллекции, если там
//            // TODO: кто-то что-то модифицировал, а также получим вызов
//            // TODO: колбека, если при обращении к базе произошла ошибка
//            notesCollection.addSnapshotListener { querySnapshot, error ->
//                value = error?.let { error ->
//                    NoteResult.Error(error)
//                } ?: querySnapshot?.let {
//                    val notes = mutableListOf<Note>()
//                    for (doc: QueryDocumentSnapshot in it) {
//                        notes.add(doc.toObject(Note::class.java))
//                    }
//                    NoteResult.Success(notes)
//                }
//            }

            // TODO: Поставить колбек на КОЛЛЕКЦИЮ в БД !!!
            // TODO: Очень удобно. Получим снэпшот всей коллекции, если там
            // TODO: кто-то что-то модифицировал, а также получим вызов
            // TODO: колбека, если при обращении к базе произошла ошибка
//            notesCollection.addSnapshotListener{ querySnapshot, error ->
//                if(error != null) {
//                    value = NoteResult.Error(error)
//                } else if(querySnapshot != null) {
//                    val notes = mutableListOf<Note>()
//
//                    for(doc: QueryDocumentSnapshot in querySnapshot) {
//                        notes.add(doc.toObject(Note::class.java))
//                    }
//                    value = NoteResult.Success(notes)
//                }
//            }
        }
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>().apply {

            try {
                // TODO: Запрос в базу и установка колбеков на результат операции
                getUserNotesCollection().document(id)
                    .get()
                    .addOnSuccessListener { docSnapshot ->
                        value = NoteResult.Success(docSnapshot.toObject(Note::class.java))
                    }
                    .addOnFailureListener { error ->
                        throw error
                    }
            } catch (e: Throwable) {
                value = NoteResult.Error(e)
            }

//            // TODO: Запрос в базу и установка колбеков на результат операции
//            notesCollection.document(id)
//                .get()
//                .addOnSuccessListener { docSnapshot ->
//                    value = NoteResult.Success(docSnapshot.toObject(Note::class.java))
//                }
//                .addOnFailureListener { error ->
//                    value = NoteResult.Error(error)
//                }
        }
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        return MutableLiveData<NoteResult>().apply {

            try {
                // TODO: Запись в базу и установка колбеков на результат операции
                getUserNotesCollection().document(note.id)
                    .set(note)
                    .addOnSuccessListener {
                        Log.d(TAG, "Note $note is saved")
                        value = NoteResult.Success(note)
                    }
                    .addOnFailureListener { error ->
                        Log.d(TAG, "Error saving note $note , message: ${error.message}")
                        throw error
                    }
            } catch (e: Throwable) {
                value = NoteResult.Error(e)
            }
        }

//        return MutableLiveData<NoteResult>().apply {
//
//            // TODO: Запись в базу и установка колбеков на результат операции
//            notesCollection.document(note.id)
//                .set(note)
//                .addOnSuccessListener {
//                    Log.d(TAG, "Note $note is saved")
//                    value = NoteResult.Success(note)
//                }
//                .addOnFailureListener { error ->
//                    Log.d(TAG, "Error saving note $note , message: ${error.message}")
//                    value = NoteResult.Error(error)
//                }
//        }
    }

    /**
     * TODO: Получить текущего юзера облачного сервиса
     */
    override fun getCurrentUser(): LiveData<User?> {
        return MutableLiveData<User?>().apply {
            value = currentUser?.let {
                User(
                    it.displayName ?: "",
                    it.email ?: ""
                )
            }
        }
    }

    /**
     * TODO: В корне облачной базы будет коллекция "users", её документами будут
     * TODO: отдельные пользователи, каждый из которых будет иметь свою вложенную
     * TODO: коллекцию "notes"
     */
    private fun getUserNotesCollection() = currentUser?.let {
        db.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
    } ?: throw NoAuthException()

}