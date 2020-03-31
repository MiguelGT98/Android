import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

import 'dart:convert';

class Country {
  final String code;
  final String imgCode;
  final String name;
  final String incomeGroup;

  Country(this.code, this.imgCode, this.name, this.incomeGroup);

  fetchData() async {
    String url =
        "http://api.worldbank.org/v2/country/$code/indicator/NY.GDP.MKTP.CD?format=json";
    var data = await http.get(url);
    var jsonData = json.decode(data.body);
    var dataCountry = jsonData[1];

    return dataCountry;
  }

  getImageUrl() {
    return "https://cdn.countryflags.com/thumbs/${name.toLowerCase().replaceAll(RegExp(' +'), '-')}/flag-800.png";
  }

  getThumbImageUrl() {
    return "https://www.countryflags.io/$imgCode/flat/64.png";
  }

  getTextColor() {
    if (incomeGroup == "High income") {
      return Colors.green;
    }

    if (incomeGroup == "Upper middle income") {
      return Colors.lightGreen;
    }

    if (incomeGroup == "Middle income") {
      return Colors.yellow;
    }

    if (incomeGroup == "Lower middle income") {
      return Colors.orange;
    }

    if (incomeGroup == "Low income") {
      return Colors.red;
    }

    return Colors.black;
  }
}
