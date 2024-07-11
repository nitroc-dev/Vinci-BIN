import 'package:flutter/material.dart';

class Laureat extends StatelessWidget {
  final String motivation;
  final String? firstname;
  final String? lastname;

  const Laureat({Key? key, required this.motivation, this.firstname, this.lastname}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    String name1 = firstname ?? "";
    String name2 = lastname ?? "";

    return Container(
      decoration: const BoxDecoration(
        border: Border(
          bottom: BorderSide(
            color: Colors.black,
            width: 1,
          ),
          left: BorderSide(
            color: Colors.red,
            width: 1,
          ),
        ),
      ),
      child: Column(
        children: [
          Text(
            motivation ?? "",
            style: const TextStyle(
              fontStyle: FontStyle.italic,
              fontSize: 24,
            ),
          ),
          Text(
            "$name1 $name2",
            style: const TextStyle(
              fontWeight: FontWeight.bold,
            ),
          ),
        ],
      ),
    );
  }
}