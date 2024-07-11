import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../models/article.dart';
import '../view_model/articles_view_model.dart';

class ListScreen extends StatelessWidget {
  const ListScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Consumer<ArticlesViewModel>(
      builder: (context, articlesViewModel, child) => Scaffold(
        appBar: AppBar(
          title: const Text("Articles"),
          actions: [
            IconButton(
              icon: articlesViewModel.showRead
                  ? const Icon(Icons.check_box)
                  : const Icon(Icons.check_box_outline_blank),
              onPressed: () {
                articlesViewModel.toggleDisplayRead();
              },
            ),
          ],
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: () {
            Navigator.pushNamed(context, '/create');
          },
          child: const Icon(Icons.add),
        ),
        body: Padding(
          padding: const EdgeInsets.all(32.0),
          child: articlesViewModel.articlesToShow.isEmpty
              ? const Center(
                  child: Text("There are no articles yet. Create one!"),
                )
              : ListView.builder(
                  itemCount: articlesViewModel.articlesToShow.length,
                  itemBuilder: (context, index) {
                    var article = articlesViewModel.articlesToShow[index];
                    return Column(
                      children: [
                        ListTile(
                          leading: IconButton(
                            icon: article.read
                                ? const Icon(Icons.check_box)
                                : const Icon(
                                    Icons.check_box_outline_blank),
                            onPressed: () {
                              articlesViewModel.setArticleRead(
                                  article.id, !article.read);
                            },
                          ),
                          title: Text(article.title),
                          subtitle: Text(article.author),
                          onTap: () {
                            Navigator.pushNamed(context, '/article',
                                arguments: article);
                          },
                          trailing: IconButton(
                            icon: const Icon(Icons.delete),
                            onPressed: () {
                              articlesViewModel.deleteArticle(article.id);
                            },
                          ),
                        ),
                        const Divider(),
                      ],
                    );
                  },
                ),
        ),
      )
    );
  }
}
