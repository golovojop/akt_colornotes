package yarlykov.s.colornotes.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note

class MainAdapter : RecyclerView.Adapter<NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set (value) {
            field = value
            // Известить зарегистрированного Observer'а об изменении источника данных
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    /**
     * Заполнить ViewHolder данными из отдельной Note
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }
}

/**
 * Класс, который прикрепляется фреймворком к отдельному элементу списка,
 * чтобы иметь возможность быстро получать доступ к отдельным View этого
 * элемента.
 */
class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title = itemView.findViewById<TextView>(R.id.title)
    private val body = itemView.findViewById<TextView>(R.id.body)
    fun bind(note: Note) {
        title.text = note.title
        body.text = note.note
        itemView.setBackgroundColor(note.color)
    }
}