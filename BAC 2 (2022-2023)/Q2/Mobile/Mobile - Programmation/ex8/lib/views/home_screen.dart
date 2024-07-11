import 'dart:io';

import 'package:camera/camera.dart';
import 'package:ex8/views/picture_screen.dart';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';

import 'gallery_screen.dart';

class HomeScreen extends StatefulWidget {
  final CameraDescription camera;

  const HomeScreen({Key? key, required this.camera}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  bool _isInitialized = true;
  late CameraController _controller;
  late Future<void> _initializeControllerFuture;

  @override
  void initState() {
    super.initState();
    _controller = CameraController(
      widget.camera,
      ResolutionPreset.medium,
    );
    _initializeControllerFuture = _controller.initialize();
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        // AppBar
        appBar: AppBar(
          title: const Text('Take a picture'),
          actions: [
            IconButton(
              onPressed: () async {
                if (!kIsWeb) {
                  await Navigator.of(context).push(
                    MaterialPageRoute(
                      builder: (context) => const GalleryScreen(),
                    ),
                  );
                } else {
                  FilePickerResult? result =
                      await FilePicker.platform.pickFiles(
                    type: FileType.custom,
                    allowedExtensions: ['jpg', 'png'],
                  );
                  if (result != null) {
                    Navigator.of(context).push(
                      MaterialPageRoute(
                        builder: (context) => PictureScreen(
                          bytes: result.files.single.bytes,
                        ),
                      ),
                    );
                  }
                }
              },
              icon: const Icon(Icons.dashboard),
            ),
          ],
        ),
        // Body
        body: Center(
          child: Column(
            children: [
              if (!_isInitialized)
                const Center(child: CircularProgressIndicator())
              else
                FutureBuilder<void>(
                  future: _initializeControllerFuture,
                  builder: (context, snapshot) {
                    if (snapshot.connectionState == ConnectionState.done) {
                      return CameraPreview(_controller);
                    } else {
                      return const Center(child: CircularProgressIndicator());
                    }
                  },
                ),
              Row(
                children: [
                  ElevatedButton(
                    onPressed: () async {
                      await onPausePreviewButtonPressed();
                    },
                    child: () {
                      if (_controller.value.isPreviewPaused) {
                        return const Icon(Icons.play_arrow);
                      } else {
                        return const Icon(Icons.pause);
                      }
                    }(),
                  ),
                  ElevatedButton(
                    onPressed: () async {
                      await onPauseButtonPressed();
                    },
                    child: () {
                      if (_isInitialized) {
                        return const Icon(Icons.square, color: Colors.red);
                      } else {
                        return const Icon(Icons.play_circle,
                            color: Colors.green);
                      }
                    }(),
                  ),
                ],
              ),
            ],
          ),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () async {
            try {
              await _initializeControllerFuture;
              final image = await _controller.takePicture();

              if (!mounted) return;

              await Navigator.of(context).push(
                MaterialPageRoute(
                  builder: (context) => PictureScreen(
                    imagePath: image.path,
                  ),
                ),
              );
            } catch (e) {
              print(e);
            }
          },
          child: const Icon(Icons.camera_alt),
        ));
  }

  Future<void> onPausePreviewButtonPressed() async {
    final CameraController cameraController = _controller;

    if (cameraController.value.isPreviewPaused) {
      await cameraController.resumePreview();
      showInSnackBar("Preview resumed");
    } else {
      await cameraController.pausePreview();
      showInSnackBar("Preview paused");
    }

    if (mounted) {
      setState(() {});
    }
  }

  Future<void> onPauseButtonPressed() async {
    final CameraController cameraController = _controller;

    if (_isInitialized) {
      await cameraController.dispose();
    } else {
      _controller = CameraController(
        widget.camera,
        ResolutionPreset.medium,
      );
      _initializeControllerFuture = _controller.initialize();
    }

    _isInitialized = !_isInitialized;

    if (mounted) {
      setState(() {});
    }
  }

  void showInSnackBar(String message) {
    ScaffoldMessenger.of(context)
        .showSnackBar(SnackBar(content: Text(message)));
  }
}
