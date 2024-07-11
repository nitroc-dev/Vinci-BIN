import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../view_models/sos_view_model.dart';

class MessageDialog extends StatefulWidget {
  const MessageDialog({Key? key}) : super(key: key);

  @override
  State<MessageDialog> createState() => _MessageDialogState();
}

class _MessageDialogState extends State<MessageDialog> {
  final messageController = TextEditingController();

  final key = GlobalKey<FormState>();

  @override
  void dispose() {
    messageController.dispose();
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    messageController.text = Provider.of<SOSViewModel>(context).message;
    return AlertDialog(
      title: const Text("Update SOS Message"),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Text("Write your new SOS message below:"),
          const SizedBox(height: 8),
          Form(
              key: key,
              child: Column(
                children: [
                  TextFormField(
                    controller: messageController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: "Enter your new SOS message",
                    ),
                    validator: (value) => (value == null || value == "")
                        ? "Message can't be empty"
                        : null,
                  ),
                ],
              )
          )
        ],
      ),
      actions: [
        TextButton(
          onPressed: () => Navigator.pop(context),
          child: const Text("Cancel"),
        ),
        TextButton(
          onPressed: () {
            if (key.currentState!.validate()) {
              final sosViewModel = Provider.of<SOSViewModel>(context, listen: false);
              sosViewModel.updateMessage(messageController.text);
              Navigator.pop(context);
            }
          },
          child: const Text("Save"),
        ),
      ],
    );
  }
}
