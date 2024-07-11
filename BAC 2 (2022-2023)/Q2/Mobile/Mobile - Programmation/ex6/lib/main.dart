import 'package:ex6/view/form_screen.dart';
import 'package:ex6/view/list_screen.dart';
import 'package:ex6/view_model/photos_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<PhotosViewModel>(
      create: (context) => PhotosViewModel(),
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        initialRoute: "/photos",
        routes: {
          "/photos": (context) => const ListScreen(),
          "/create": (context) => const FormScreen(),
        },
      ),
    );
  }
}
