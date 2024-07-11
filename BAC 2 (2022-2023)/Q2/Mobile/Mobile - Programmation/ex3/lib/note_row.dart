import 'package:flutter/material.dart';

import 'note.dart';

class NoteRow extends StatelessWidget {
  final Note note;
  final void Function(Note) deleteNote;

  NoteRow({
    Key? key,
    required this.note, required this.deleteNote,
  }) : super(key: key);

  List<Note> displayedNotes = defaultNotes;

  @override
  Widget build(BuildContext context) {
    return ListTile(
      title: Text(note.title),
      subtitle: Text(note.text),
      trailing: IconButton(
        onPressed: () => deleteNote(note),
        icon: const Icon(Icons.delete),
      ),
    );
  }
}

List<Note> _createNotes() {
  final notes = [
    for (var i = 0; i < 10; i++)
      Note(title: "Note ${i + 1}", text: "$i")
  ];

  return [...notes];
}

List<Note> defaultNotes = _createNotes();