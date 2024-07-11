import 'package:flutter/material.dart';
import 'package:ftuto7/views/home_screen.dart';
import 'package:ftuto7/views/location_dialog.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(primarySwatch: Colors.blue),
      initialRoute: "/home",
      routes: {
        "/home": (context) => const HomeScreen(),
        "/location": (context) => const LocationDialog(),
      },
    );
  }
}