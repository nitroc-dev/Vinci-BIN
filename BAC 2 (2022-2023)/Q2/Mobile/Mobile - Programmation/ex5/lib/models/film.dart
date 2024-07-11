import 'dart:convert';
import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

class Film {
  static const baseUrl = "https://sebstreb.github.io/flutter-fiche-5/ghibli-films";

  final String id;
  final String title;
  final String image;
  final String movie_banner;
  final String description;
  final String release_date;
  final String director;
  final String running_time;
  final String rt_score;

  const Film(
    this.id,
    this.title,
    this.image,
    this.movie_banner,
    this.description,
    this.release_date,
    this.director,
    this.running_time,
    this.rt_score,
  );

  Film.fromJson(Map<String, dynamic> json) : this(
    json['id'],
    json['title'],
    json['image'],
    json['movie_banner'],
    json['description'],
    json['release_date'],
    json['director'],
    json['running_time'],
    json['rt_score'],
  );

  @override
  String toString() => 'Film: $title, $image, $movie_banner, $description, $release_date, $director, $running_time, $rt_score';

  static Future<List<Film>> fetchFilms() async {
    var response = await http.get(Uri.parse("$baseUrl/"));

    if (response.statusCode != 200) {
      throw Exception("Error ${response.statusCode} fetching movies");
    }

    return compute((input) {
      final jsonList = jsonDecode(input);
      return jsonList.map<Film>((jsonObj) => Film.fromJson(jsonObj)).toList();
    }, response.body);
  }
}