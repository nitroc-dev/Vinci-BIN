import 'package:ex6/view_model/photos_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class FormScreen extends StatefulWidget {
  const FormScreen({Key? key}) : super(key: key);

  @override
  State<FormScreen> createState() => _FormScreenState();
}

class _FormScreenState extends State<FormScreen> {
  final titleController = TextEditingController();
  final thumbnailController = TextEditingController();

  final key = GlobalKey<FormState>();

  @override
  void dispose() {
    titleController.dispose();
    thumbnailController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("New post"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      body: Padding(
        padding: const EdgeInsets.all(32.0),
        child: Form(
          key: key,
          child: Column(
            children: [
              TextFormField(
                controller: titleController,
                decoration: const InputDecoration(labelText: "Title"),
                validator: (value) => (value == null || value == "")
                    ? "Title can't be empty"
                    : null,
              ),
              TextFormField(
                controller: thumbnailController,
                decoration: const InputDecoration(labelText: "Thumbnail Url"),
                validator: (value) => (value == null || value == "")
                    ? "Thumbnail can't be empty"
                    : null,
              ),
              const SizedBox(height: 16),
              ElevatedButton(
                child: const Text("Create article"),
                onPressed: () {
                  if (key.currentState!.validate()) {
                    final photosViewModel = Provider.of<PhotosViewModel>(context, listen: false);
                    photosViewModel.createPhoto(title: titleController.text, thumbnailUrl: thumbnailController.text);
                    Navigator.pop(context);
                  }
                },
              )
            ],
          ),
        ),
      ),
    );
  }
}
