package yarlykov.s.colornotes.ui.splash

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import yarlykov.s.colornotes.data.model.Repository
import yarlykov.s.colornotes.exception.NoAuthException

class SplashViewModel(private val repository: Repository = Repository) : ViewModel() {

    val viewStateLiveData = MutableLiveData<SplashViewState>()

    fun getViewState(): LiveData<SplashViewState> = viewStateLiveData

    fun requestUser() {
        repository.getCurrentUser().observeForever {
            viewStateLiveData.value = if (it != null) {
                SplashViewState(isAuth = true)
            } else {
                SplashViewState(error = NoAuthException())
            }
        }
    }
}