package yarlykov.s.colornotes.ui.main

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activity_main.*
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.exception.NoAuthException
import yarlykov.s.colornotes.ui.note.NoteActivity

class MainActivity : AppCompatActivity() {

    val RC_SIGN_IN = 458;

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: Чтобы поставить свой ToolBar нужно в стиле выбрать тему без Toolba'a
        setSupportActionBar(toolbar_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter(::openNoteScreen)

        // TODO: В этом месте адаптер инициализируется данными
        viewModel.viewState().observe(this, Observer<MainViewState> { viewState ->
            viewState?.apply {
                notes?.let {renderData(it)}
                error?.let {renderError(it)}
            }
        })

        // TODO: Котлин позволяет обойтись без findViewById<>() и напрямую обратиться к View
        rv_noteList.adapter = adapter
        rv_noteList.layoutManager = GridLayoutManager(this, 2)

        // TODO: При нажатии на кнопку запускаем активити созданиязаметки
        fab.setOnClickListener { openNoteScreen(null) }
    }

    fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN && resultCode != Activity.RESULT_OK){
            finish()
        }
    }

    private fun openNoteScreen(note: Note?) {
        NoteActivity.startNoteActivity(this, note)
    }
}
