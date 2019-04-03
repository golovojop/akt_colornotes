package yarlykov.s.colornotes.ui.note

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_note.*
import android.view.MenuItem
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.extensions.format
import yarlykov.s.colornotes.extensions.getColorInt
import java.util.*


class NoteActivity : AppCompatActivity() {

    companion object {

        private const val SAVE_DELAY = 2000L
        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE "
        private const val DATE_TIME_FORMAT = "dd.MMM.yy HH:mm"

        fun getStartIntent(context: Context, note: Note?): Intent =
            Intent(context, NoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
            }

        fun startNoteActivity(context: Context, note: Note?) {
            context.startActivity(Intent(context, NoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
            })
        }
    }

    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar_note)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        note = intent.getParcelableExtra(EXTRA_NOTE)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = note?.let {
            it.lastChanged.format(DATE_TIME_FORMAT)
        } ?: getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        note?.apply {
            et_title.setText(title)
            et_body.setText(text)
            toolbar_note.setBackgroundColor(background.getColorInt(this@NoteActivity))
        }

        // TODO: Отслеживаем изменения текста
        et_title.addTextChangedListener(textWatcher)
        et_body.addTextChangedListener(textWatcher)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    private fun triggerSaveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return

        // TODO: Передать Runnable как блок кода
        // TODO: https://kotlinlang.org/docs/reference/java-interop.html#sam-conversions
        Handler().postDelayed({
            note = note?.copy(
                title = et_title.text.toString(),
                text = et_body.text.toString(),
                lastChanged = Date()
            ) ?: createNewNote()
            note?.let { viewModel.saveChanges(note!!) }
        }, SAVE_DELAY)
    }


    /**
     * TODO: Создать новую заметку из содержимого вьюшек активити
     */
    private fun createNewNote(): Note = Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString())

    /**
     * TODO: Если заметка изменена пользователем, то сработает этот Watcher, который триггерит
     * TODO: процедуру сохранения измененной заметки. Само сохранение - отложенное на величину SAVE_DELAY.
     * TODO: То есть мы клацаем, а через некоторое время сработает запись. Если ещё поклацать,
     * TODO: то она сова сработает. Прикольно ! Не нужно создавать кнопку Save.
     */
    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            triggerSaveNote()
        }
    }

}