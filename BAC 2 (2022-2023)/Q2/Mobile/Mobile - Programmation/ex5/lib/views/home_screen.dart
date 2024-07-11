import 'package:flutter/material.dart';

import '../models/film.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

enum FetchState { loading, error, done }

class _HomeScreenState extends State<HomeScreen> {
  var _status = FetchState.loading;
  var _message = "Loading...";
  final List<Film> _films = [];

  @override
  void initState() {
    super.initState();
    Future.delayed(const Duration(seconds: 3), () {
      _fetchGhibliFilms();
    });
  }

  void _fetchGhibliFilms() async {
    try {
      var response = await Film.fetchFilms();
      setState(() {
        _status = FetchState.done;
        _films.addAll(response);
      });
    } catch (error) {
      setState(() {
        _status = FetchState.error;
        _message = error.toString();
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Studio Ghibli Films"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: _status != FetchState.done
            ? Column(children: [Expanded(child: Center(child: Text(_message)))])
            : ListView.separated(
              itemCount: _films.length,
              itemBuilder: (context, index) => FilmRow(film: _films[index]),
              separatorBuilder: (context, index) => const Divider(),
        ),
      ),
    );
  }
}

class FilmRow extends StatelessWidget {
  final Film film;

  const FilmRow({Key? key, required this.film}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return LayoutBuilder(
      builder: (context, constraints) {
        if (constraints.maxWidth < 400) {
          return Column(
            children: [
              Image.network(
                film.image,
                width: 100,
                height: 150,
                fit: BoxFit.cover,
              ),
              const SizedBox(height: 8),
              Text(
                film.title,
                style: const TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              Text("${film.rt_score} / 100",),
              Text("${film.running_time} minutes"),
              Text("Directed by ${film.director}"),
              Text(film.description),
            ],
          );
        } else {
          return Row(
            children: [
              Image.network(
                film.image,
                width: 100,
                height: 150,
                fit: BoxFit.cover,
              ),
              const SizedBox(width: 8),
              Expanded(
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      film.title,
                      style: const TextStyle(
                        fontSize: 20,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text("${film.rt_score} / 100",),
                    Text("${film.running_time} minutes"),
                    Text("Directed by ${film.director}"),
                    Text(film.description),
                  ],
                ),
              ),
            ],
          );
        }
      },
    );
  }

}
