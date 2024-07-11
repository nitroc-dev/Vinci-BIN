import 'package:flutter/material.dart';

import '../models/article.dart';

class ArticlesViewModel extends ChangeNotifier {
  var articles = <Article>[];
  var displayRead = false;

  get showRead => displayRead;

  List<Article> get articlesToShow {
    return articles.where((article) => displayRead || !article.read).toList();
  }

  Article getArticleById(int id) {
    return articles.firstWhere((article) => article.id == id);
  }

  void addArticle(Article article) {
    articles.add(article);
    notifyListeners();
  }

  void deleteArticle(int id) {
    articles.removeWhere((article) => article.id == id);
    notifyListeners();
  }

  void setArticleRead(int id, bool read) {
    var article = getArticleById(id);
    article.read = read;
    notifyListeners();
  }

  void toggleDisplayRead() {
    displayRead = !displayRead;
    notifyListeners();
  }
}
