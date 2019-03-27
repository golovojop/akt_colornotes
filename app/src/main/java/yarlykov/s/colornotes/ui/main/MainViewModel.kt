package yarlykov.s.colornotes.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import yarlykov.s.colornotes.data.model.Repository

class MainViewModel: ViewModel() {

    private val viewStateLiveData : MutableLiveData<MainViewState> = MutableLiveData<MainViewState>().apply {
        value = MainViewState(Repository.notes)
    }

    fun viewState() : LiveData<MainViewState> = viewStateLiveData

}