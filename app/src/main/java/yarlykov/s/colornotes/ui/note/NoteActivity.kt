package yarlykov.s.colornotes.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note.*
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {

        private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE "

        fun getStartIntent(context: Context, note: Note?): Intent {
            val intent = Intent(context, NoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
            }
        }

        fun startNoteActivity(context: Context, note: Note?) {
            context.startActivity(Intent(context, NoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, note)
            })
        }
    }

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar_note)

        note = intent.getParcelableExtra(EXTRA_NOTE)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.title = if (note != null) {
            SimpleDateFormat(
                DATE_TIME_FORMAT,
                Locale.getDefault()
            ).format(note!!.lastChanged)
        } else {
            getString(R.string.new_note_title)
        }

        initView()
    }
}