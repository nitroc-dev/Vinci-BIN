import 'dart:convert';

import 'package:ex6/view_model/photos_view_model.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:loading_animations/loading_animations.dart';
import 'package:provider/provider.dart';

class ListScreen extends StatefulWidget {
  const ListScreen({Key? key}) : super(key: key);

  @override
  State<ListScreen> createState() => _ListScreenState();
}

class _ListScreenState extends State<ListScreen> {

  @override
  Widget build(BuildContext context) {
    return Consumer<PhotosViewModel>(
      builder: (context, model, child) => Scaffold(
        appBar: AppBar(
          title: const Text('FutureBuilder Example'),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.pushNamed(context, '/create');
          },
          child: const Icon(Icons.add),
        ),
        body: FutureBuilder<List<dynamic>>(
          future: Future.delayed(const Duration(seconds: 4), () => model.photosFuture),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return ListView.builder(
                itemCount: snapshot.data?.length,
                itemBuilder: (context, index) {
                  final post = snapshot.data?[index];
                  return ListTile(
                    title: Text(post['title']),
                    leading: Image.network(post['thumbnailUrl']),
                  );
                },
              );
            } else if (snapshot.hasError) {
              return Center(child: Text('${snapshot.error}'));
            } else {
              return Center(
                  child: LoadingFlipping.circle(),
              );
            }
          },
        ),
      ),
    );
  }
}
