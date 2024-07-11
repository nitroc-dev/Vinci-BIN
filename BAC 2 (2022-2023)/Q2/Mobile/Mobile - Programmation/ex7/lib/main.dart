import 'package:ex7/view_models/sos_view_model.dart';
import 'package:ex7/views/home_screen.dart';
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
    return ChangeNotifierProvider<SOSViewModel>(
      create: (context) => SOSViewModel(),
      child: MaterialApp(
        title: 'SOS App',
        theme: ThemeData(primarySwatch: Colors.blue),
        initialRoute: "/home",
        routes: {
          "/home": (context) => const HomeScreen(),
        },
      ),
    );
  }
}