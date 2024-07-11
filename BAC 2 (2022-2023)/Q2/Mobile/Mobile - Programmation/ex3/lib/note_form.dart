import 'package:flutter/material.dart';

import 'note.dart';

class NoteForm extends StatefulWidget {
  final void Function(Note) addNote;

  const NoteForm({Key? key, required this.addNote}) : super(key: key);

  @override
  State<NoteForm> createState() => _NoteFormState();
}

class _NoteFormState extends State<NoteForm> {
  final controllerTitle = TextEditingController();
  final controllerText = TextEditingController();
  final key = GlobalKey<FormState>();

  @override
  void dispose() {
    controllerTitle.dispose();
    controllerText.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: key,
      child: Row(
        children: [
          Expanded(
            child: TextFormField(
              controller: controllerTitle,
              decoration: const InputDecoration(labelText: "Enter title"),
              validator: (value) =>
              (value == null || value.isEmpty) ? "Title can't be empty" : null,
            ),
          ),
          Expanded(
            child: TextFormField(
              controller: controllerText,
              decoration: const InputDecoration(labelText: "Enter text"),
              validator: (value) =>
              (value == null || value.isEmpty) ? "Text can't be empty" : null,
            )
          ),
          const SizedBox(width: 32.0),
          ElevatedButton(
            child: const Text("Add note"),
            onPressed: () {
              if (key.currentState!.validate()) {
                widget.addNote(Note(
                  title: controllerTitle.text,
                  text: controllerText.text,
                ));
                controllerTitle.text = "";
                controllerText.text = "";
              }
            },
          )
        ],
      ),
    );
  }
}
