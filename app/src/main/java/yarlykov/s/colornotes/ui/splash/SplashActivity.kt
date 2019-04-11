package yarlykov.s.colornotes.ui.splash

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.firebase.ui.auth.AuthUI
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.exception.NoAuthException
import yarlykov.s.colornotes.ui.main.MainActivity

class SplashActivity : AppCompatActivity(){

    companion object {
        private const val RC_SIGN_IN = 1458;
        private const val START_DELAY = 1000L
    }

    val viewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    val layoutRes: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutRes?.let {
            setContentView(it)
        }
        viewModel.getViewState().observe(this, Observer<SplashViewState> { viewState ->
            viewState?.apply {
                isAuth?.let { renderData(it) }
                error?.let { renderError(it) }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ viewModel.requestUser() }, START_DELAY)
    }

    fun renderData(data: Boolean?) {
//        if(data == true){
//            startMainActivity()
//        }

        data?.takeIf { it }?.let {
            startMainActivity()
        }
    }

    fun renderError(error: Throwable?) {
        when (error) {
            is NoAuthException -> startLoginActivity()
            else -> error?.let {
                Log.d("NOTES_ALL", it.message)
            }
        }
    }

    fun startLoginActivity() {
        val providers = listOf(
//            AuthUI.IdpConfig.EmailBuilder().build()
//            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.android_robot)
                .setTheme(R.style.LoginStyle)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }

    private fun startMainActivity() {
        MainActivity.start(this)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK){
            finish()
        }
    }
}