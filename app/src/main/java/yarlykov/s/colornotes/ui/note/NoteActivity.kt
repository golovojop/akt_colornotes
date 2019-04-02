package yarlykov.s.colornotes.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_note.*
import android.view.Menu
import android.view.MenuItem
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.extensions.format
import yarlykov.s.colornotes.extensions.getColorId


class NoteActivity : AppCompatActivity() {

    companion object {

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

    private var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar_note)

        note = intent.getParcelableExtra(EXTRA_NOTE)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = note?.let {
            it.lastChanged.format(DATE_TIME_FORMAT)
        } ?: getString(R.string.new_note_title)
    }

    private fun initView() {
        note?.apply {
            et_title.setText(title)
            et_body.setText(text)
            toolbar_note.setBackgroundColor(color.getColorId(color))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}