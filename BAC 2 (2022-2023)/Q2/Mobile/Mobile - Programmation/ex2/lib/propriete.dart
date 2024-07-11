class Propriete {
  final bool isToSell;
  final bool isAppartement;
  final int squareMeters;
  final int numberOfRooms;
  final double price;

  Propriete({
    required this.isToSell,
    required this.isAppartement,
    required this.squareMeters,
    required this.numberOfRooms,
    required this.price,
  });
}

List<Propriete> _createProprietes() {
  final listAVendre = List.generate(1000, (index) =>
    Propriete(
      isToSell: true,
      isAppartement: index % 2 == 0 ? true : false,
      squareMeters: 100,
      numberOfRooms: 3,
      price: 100000,
    )
  );

  final listALouer = List.generate(1000, (index) =>
    Propriete(
      isToSell: false,
      isAppartement: index % 2 == 0 ? true : false,
      squareMeters: 100,
      numberOfRooms: 3,
      price: 1000,
    )
  );

  return [...listAVendre, ...listALouer];
}

final proprietes = _createProprietes();