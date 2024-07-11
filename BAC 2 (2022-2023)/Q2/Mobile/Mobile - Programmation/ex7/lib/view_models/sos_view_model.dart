import 'package:flutter/material.dart';

import 'package:flutter_sms/flutter_sms.dart';
import 'package:location/location.dart';

class SOSViewModel extends ChangeNotifier{
  var message = "Help me!";
  List<String> phones = [];

  String get getMessage => message;

  void updateMessage(String message){
    this.message = message;
    notifyListeners();
  }

  void addPhone(String phone){
    phones.add(phone);
    notifyListeners();
  }

  void removePhone(String phone){
    phones.remove(phone);
    notifyListeners();
  }

  Future<void> sendSOS() async {
    var location = await _getLocation();
    await sendSMS(
      message: message.replaceFirst("@loc", "${location?.latitude}, ${location?.longitude}"),
      recipients: [...phones],
    );
  }

  Future<LocationData?> _getLocation() async {
    Location location = Location();

    var serviceEnabled = await location.serviceEnabled();
    if (!serviceEnabled) {
      serviceEnabled = await location.requestService();
      if (!serviceEnabled) {
        return null;
      }
    }

    var permissionGranted = await location.hasPermission();
    if (permissionGranted == PermissionStatus.denied) {
      permissionGranted = await location.requestPermission();
      if (permissionGranted != PermissionStatus.granted) {
        return null;
      }
    }

    return await location.getLocation();
  }
}