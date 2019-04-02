package yarlykov.s.colornotes.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import yarlykov.s.colornotes.R
import yarlykov.s.colornotes.data.entity.Note
import yarlykov.s.colornotes.extensions.getColorInt

class MainAdapter(val onItemClick: ((note: Note) -> Unit)? = null) : RecyclerView.Adapter<MainAdapter.NoteViewHolder>() {

    var notes: List<Note> = listOf()
        set (value) {
            field = value
            // Известить зарегистрированного Observer'а об изменении источника данных
            notifyDataSetChanged()
        }

    // Взывается из RecyclerView при подключении адаптера
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)  =
        NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        )

    override fun getItemCount() = notes.size

    /**
     * Заполнить ViewHolder данными из отдельной Note
     */
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int): Unit {
        holder.bind(notes[position])
    }

    /**
     * Класс, который прикрепляется фреймворком к отдельному элементу списка,
     * чтобы иметь возможность быстро получать доступ к отдельным View этого
     * элемента.
     */
    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle = itemView.findViewById<TextView>(R.id.title)
        private val tvBody = itemView.findViewById<TextView>(R.id.body)

        fun bind(note: Note)  = with(note) {
            tvTitle.text = title
            tvBody.text = text
            itemView.setBackgroundColor(note.background.getColorInt(itemView.context))
            itemView.setOnClickListener{ onItemClick?.invoke(note) }
        }
    }
}

