class Note {
  String title;
  String text;

  Note({
    required this.title,
    required this.text,
  });

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
      other is Note && runtimeType == other.runtimeType && title == other.title;

  @override
  int get hashCode => title.hashCode;
}