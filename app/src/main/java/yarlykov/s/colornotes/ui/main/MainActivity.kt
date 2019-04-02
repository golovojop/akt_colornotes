package yarlykov.s.colornotes.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.ui.note.NoteActivity

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Чтобы поставить свой ToolBar нужно в стиле выбрать тему без Toolba'a
        setSupportActionBar(toolbar_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = MainAdapter(::openNoteScreen)

        rv_noteList.layoutManager = GridLayoutManager(this, 2)

        /**
         * Котлин позволяет обойтись без findViewById<>() и напрямую
         * обратиться к View
         */
        rv_noteList.adapter = adapter

        /**
         * Подписаться на изменение источника списка Note
         */
        viewModel.viewState().observe(this, Observer<MainViewState> { t ->
            t?.let { adapter.notes = it.notes }
        })
    }

    private fun openNoteScreen(note: Note?) {
        NoteActivity.startNoteActivity(this, note)
    }
}
