import 'package:ex7/views/message_dialog.dart';
import 'package:ex7/views/phone_dialog.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../view_models/sos_view_model.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Screen'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () async {
                var provider = Provider.of<SOSViewModel>(context, listen: false);
                await provider.sendSOS();
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.red,
                minimumSize: const Size(200, 200),
                shape: const CircleBorder(),
              ),
              child: const Text("SOS"),
            ),
            ElevatedButton(
              onPressed: () => showDialog(
                context: context,
                builder: (context) => const MessageDialog(),
              ),
              child: const Text("Change SOS Message"),
            ),
            const SizedBox(height: 16),
            ElevatedButton(
              onPressed: () => showDialog(
                context: context,
                builder: (context) => const PhoneDialog(),
              ),
              child: const Text("Manage SOS recipients"),
            ),
          ],
        ),
      ),
    );
  }
}
