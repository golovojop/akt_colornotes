package yarlykov.s.colornotes.data.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.provider.FireStoreProvider
import yarlykov.s.colornotes.data.provider.RemoteDataProvider
import java.util.*

object Repository {

    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    fun getNotes () = remoteProvider.subscribeToAllNotes()
    fun saveNote (note: Note ) = remoteProvider.saveNote(note)
    fun getNoteById (id: String ) = remoteProvider.getNoteById(id)

//    private val notesLiveData = remoteProvider.subscribeToAllNotes()
//
//    // TODO: Mutable List
//    private val notes = arrayListOf(
//        Note(
//            UUID.randomUUID().toString(),
//            "Note WHITE",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.WHITE
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note BLUE",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.BLUE
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note GREEN",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.GREEN
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note PINK",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.PINK
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note RED",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.RED
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note VIOLET",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.VIOLET
//        ),
//        Note(
//            UUID.randomUUID().toString(),
//            "Note YELLOW",
//            "Kotlin очень краткий, но при этом выразительный язык",
//            Note.Color.YELLOW
//        )
//    )
//
//    init {
//        notesLiveData.value = notes
//    }
//
//    fun getNotes(): LiveData<List<Note>> {
//        return notesLiveData
//    }
//
//    fun saveNote(note: Note) {
//        saveOrReplace(note)
//    }

    // TODO: Работа с итератораи в Котлин:
    // TODO: https://stackoverflow.com/questions/34608551/in-kotlin-how-do-you-modify-the-contents-of-a-list-while-iterating
//    private fun saveOrReplace(note: Note) {
//
//        notes.listIterator().apply {
//            while(hasNext()){
//                if(next().id == note.id) {
//                    set(note)
//                    return@apply
//                }
//            }
//            notes.add(note)
//        }
//        notesLiveData.value = notes
//    }
}