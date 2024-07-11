import 'package:ex7/view_models/sos_view_model.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class PhoneDialog extends StatefulWidget {
  const PhoneDialog({Key? key}) : super(key: key);

  @override
  State<PhoneDialog> createState() => _PhoneDialogState();
}

class _PhoneDialogState extends State<PhoneDialog> {
  final phoneController = TextEditingController();

  final key = GlobalKey<FormState>();

  @override
  void dispose() {
    phoneController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return AlertDialog(
      title: const Text("Manage SOS Recipients"),
      content: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: Consumer<SOSViewModel> (
              builder: (context, model, child) => ListView.builder(
                itemCount: model.phones.length,
                itemBuilder: (context, index) {
                  var phone = model.phones[index];
                  return ListTile(
                    title: Text(phone),
                    trailing: IconButton(
                      icon: const Icon(Icons.delete),
                      onPressed: () => model.removePhone(phone),
                    ),
                  );
                },
              ),
            ),
          ),
          const SizedBox(height: 8),
          const Text("Add a new phone number below:"),
          const SizedBox(height: 8),
          Consumer<SOSViewModel>(
            builder: (context, model, child) => Form(
              key: key,
              child: Column(
                children: [
                  TextFormField(
                    controller: phoneController,
                    decoration: const InputDecoration(
                      border: OutlineInputBorder(),
                      hintText: "Enter a new phone number",
                    ),
                    validator: (value) => (value == null || value == "")
                        ? "Phone number can't be empty"
                        : null,
                  ),
                ],
              ),
            ),
          ),
          IconButton(
              onPressed: () {
                if (key.currentState!.validate()) {
                  Provider.of<SOSViewModel>(context, listen: false)
                      .addPhone(phoneController.text);
                  phoneController.clear();
                }
              },
              icon: const Icon(Icons.add)
          )
        ],
      ),
      actions: [
        TextButton(
          child: const Text("Cancel"),
          onPressed: () {
            phoneController.clear();
            Navigator.pop(context);
          },
        ),
      ],
    );
  }
}
