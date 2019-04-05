package yarlykov.s.colornotes.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.model.NoteResult
import yarlykov.s.colornotes.data.model.Repository

class MainViewModel: ViewModel() {

    private val viewStateLiveData : MutableLiveData<MainViewState> = MutableLiveData()

    /**
     * TODO: Поставить Observer на изменения в LiveData репозитория. Если в репозитории
     * TODO: что-то меняется, то в локальной LiveData это нужно отразить, тогда
     * TODO: и MainActivity узнает об изменениях. Получилась цепочка из LiveData,
     * TODO: связанная обзерверами. Данный класс не знает где фактически хранятся данные.
     */
    init {
        Repository.getNotes().observeForever {noteResult ->
            noteResult?.also{
                when(it) {
                    is NoteResult.Success<*> -> viewStateLiveData.value = MainViewState(notes = it.data as List<Note>)
                    is NoteResult.Error -> it.error
                }
            }
        }
    }

    fun viewState() : LiveData<MainViewState> = viewStateLiveData
}