import 'package:flutter/material.dart';
import 'package:ftuto4/view_model/click_view_model.dart';
import 'package:ftuto4/views/second_screen.dart';
import 'package:provider/provider.dart';

import 'views/first_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<ClickViewModel>(
      create: (context) => ClickViewModel(),
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(primarySwatch: Colors.blue),
        initialRoute: "/first",
        routes: {
          "/first": (context) => const FirstScreen(),
          "/second": (context) => const SecondScreen(),
        },
      ),
    );
  }

}