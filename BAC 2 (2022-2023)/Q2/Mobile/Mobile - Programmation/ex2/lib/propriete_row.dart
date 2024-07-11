import 'package:ex2/propriete.dart';
import 'package:flutter/material.dart';

class ProprieteRow extends StatelessWidget {
  final Propriete propriete;

  const ProprieteRow({
    Key? key,
    required this.propriete
  }) : super(key: key);

  Widget build(BuildContext context) {
    return Column(
      children: [
        ListTile(
          title: Text(propriete.isToSell ? 'A vendre' : 'A louer'),
          subtitle: Text(propriete.squareMeters.toString()),
          trailing: Icon(propriete.isAppartement ? Icons.house_siding : Icons.house),
        ),
        Text(
          propriete.price.toString(),
          style: const TextStyle(
            fontWeight: FontWeight.w100,
          )
        ),
        Text(
          propriete.numberOfRooms.toString(),
          style: const TextStyle(
            fontWeight: FontWeight.w100,
          )
        )
      ],
    );
  }
}