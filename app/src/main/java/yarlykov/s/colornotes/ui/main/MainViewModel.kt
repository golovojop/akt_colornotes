package yarlykov.s.colornotes.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.data.model.Repository

class MainViewModel: ViewModel() {

    private val viewStateLiveData : MutableLiveData<MainViewState> = MutableLiveData()

    /**
     * TODO: Поставить Observer на изменения в LiveData репозитория. Если в репозитории
     * TODO: что-то меняется, то в локальной LiveData это нужно отразить, тогда
     * TODO: и MainActivity узнает об изменениях. Получиласть цепочка из LiveData,
     * TODO: связанная обзерверами.
     */
    init {
        Repository.getNotes().observeForever {
            viewStateLiveData.value = viewStateLiveData.value?.copy(notes = it!!) ?: MainViewState(it!!)
        }
    }

    fun viewState() : LiveData<MainViewState> = viewStateLiveData



}