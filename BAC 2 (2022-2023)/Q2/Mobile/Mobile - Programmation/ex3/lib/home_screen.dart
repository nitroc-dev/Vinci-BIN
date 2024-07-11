import 'package:ex3/note_row.dart';
import 'package:flutter/material.dart';

import 'note_form.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final list = [...defaultNotes];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Screen'),
      ),
      body: Column(
        children: [
          Expanded(
            child: Container(
              padding: const EdgeInsets.all(16.0),
              child: Center(
                child: SizedBox(
                  width: 512.0,
                  child: ListView.builder(
                    itemCount: list.length,
                    itemBuilder: (context, index) =>
                        NoteRow(
                            note: list[index],
                            deleteNote: (value) => setState(() => list.remove(value),
                        ),
                      ),
                  ),
                ),
              ),
            ),
          ),
          NoteForm(
            addNote: (value) => setState(() {
              defaultNotes.add(value);
            }),
          ),
        ],
      ),
    );
  }
}
