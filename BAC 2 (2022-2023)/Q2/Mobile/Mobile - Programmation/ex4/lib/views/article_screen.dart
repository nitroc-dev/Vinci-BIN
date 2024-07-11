import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import '../../models/article.dart';
import '../view_model/articles_view_model.dart';

class ArticleScreen extends StatelessWidget {
  const ArticleScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final article = ModalRoute.of(context)!.settings.arguments as Article;
    return Scaffold(
      appBar: AppBar(
        title: const Text("Article"),
        leading: IconButton(
          icon: const Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: article.read
            ? const Icon(Icons.check_box)
            : const Icon(Icons.check_box_outline_blank),
        onPressed: () {
          final articlesViewModel = Provider.of<ArticlesViewModel>(context, listen: false);
          articlesViewModel.setArticleRead(article.id, !article.read);
        },
      ),
      body: Padding(
        padding: const EdgeInsets.all(32),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              article.title,
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
              ),
            ),
            Text(
              article.author,
              style: TextStyle(fontSize: 18, color: Colors.grey[700]),
            ),
            const SizedBox(height: 16),
            Expanded(
              child: SingleChildScrollView(
                child: SizedBox(
                  width: double.infinity,
                  child: Text(article.content, textAlign: TextAlign.justify),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
