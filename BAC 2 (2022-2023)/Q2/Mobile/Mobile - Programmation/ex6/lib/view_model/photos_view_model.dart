import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:http/http.dart' as http;

class PhotosViewModel extends ChangeNotifier {
  Future<List<dynamic>> _photosFuture = Future.value([]);

  PhotosViewModel() {
    _photosFuture = fetchPhotos();
  }

  Future<List<dynamic>> get photosFuture => _photosFuture;

  Future<List<dynamic>> fetchPhotos() async {
    final response =
    await http.get(Uri.parse('https://unreal-api.azurewebsites.net/photos'));
    if (response.statusCode == 200) {
      return json.decode(response.body);
    } else {
      throw Exception('Failed to load data');
    }
  }

  void createPhoto({required String title, required String thumbnailUrl}) async {
    final url = Uri.parse('https://unreal-api.azurewebsites.net/photos');
    final response = await http.post(
      url,
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'title': title,
        'thumbnailUrl': thumbnailUrl,
      }),
    );
    if (response.statusCode == 201) {
      _photosFuture = fetchPhotos();
      notifyListeners();
    } else {
      throw Exception('Failed to create photo.');
    }
  }
}