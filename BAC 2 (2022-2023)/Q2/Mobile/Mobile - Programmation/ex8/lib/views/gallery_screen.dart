import 'dart:io';
import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

class GalleryScreen extends StatefulWidget {
  final Key? key;

  const GalleryScreen({this.key}) : super(key: key);

  @override
  _GalleryScreenState createState() => _GalleryScreenState();
}

class _GalleryScreenState extends State<GalleryScreen> {
  @override
  void initState() {
    super.initState();
    _getImages();
  }

  List<String> _imagePaths = [];

  Future<void> _getImages() async {
    Directory? directory = await getExternalStorageDirectory();
    debugPrint(directory!.path);
    List<FileSystemEntity>? files = directory?.listSync(recursive: true);
    for (FileSystemEntity file in files!) {
      debugPrint(file.path);
      if (file.path.endsWith('.jpg') || file.path.endsWith('.png')) {
        _imagePaths.add(file.path);
      }
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Image List'),
      ),
      body: _imagePaths.isEmpty
          ? const Center(
              child: Text('No images found.'),
            )
          : ListView.builder(
              itemCount: _imagePaths.length,
              itemBuilder: (BuildContext context, int index) {
                return Image.file(File(_imagePaths[index]));
              },
            ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _getImages();
        },
        child: const Icon(Icons.refresh),
      ),
    );
  }
}
