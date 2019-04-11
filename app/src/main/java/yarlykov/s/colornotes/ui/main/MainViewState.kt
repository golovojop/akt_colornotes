package yarlykov.s.colornotes.ui.main

import yarlykov.s.colornotes.data.entity.Note

class MainViewState(val notes: List<Note>? = null, val error: Throwable? = null)