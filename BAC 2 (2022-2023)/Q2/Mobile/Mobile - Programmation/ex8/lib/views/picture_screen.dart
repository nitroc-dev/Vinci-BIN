import 'dart:io';
import 'dart:typed_data';

import 'package:file_saver/file_saver.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

class PictureScreen extends StatelessWidget {
  final String? imagePath;
  final Uint8List? bytes;

  const PictureScreen({Key? key, this.imagePath, this.bytes}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final imageWidget = bytes != null ? Image.memory(bytes!) : kIsWeb ? Image.network(imagePath!) : Image.file(File(imagePath!));

    return Scaffold(
      appBar: AppBar(title: const Text('Display the Picture')),
      body: imageWidget,
      floatingActionButton: FloatingActionButton(
        onPressed: () async {
          if (kIsWeb && imagePath != null) {
            await FileSaver.instance.saveFile(
              link: imagePath!,
              name: 'image.jpg',
              ext: 'jpg',
              // Link sur le web
            );
          } else if ((Platform.isAndroid ||
                  Platform.isIOS ||
                  Platform.isWindows ||
                  Platform.isMacOS ||
                  Platform.isLinux) &&
              imagePath != null) {
            final date = DateTime.now().toString();
            await FileSaver.instance.saveFile(
              filePath: imagePath,
              name: 'image_$date.jpg',
            );
            Navigator.of(context).pop();
          }
        },
        child: const Icon(Icons.save),
      ),
    );
  }
}
