import 'package:http/http.dart' as http;
import 'dart:convert';

class Country {
  final String code;
  final String imgCode;
  final String name;

  Country(this.code, this.imgCode, this.name);

  fetchData() async {
    String url =
        "http://api.worldbank.org/v2/country/$code/indicator/NY.GDP.MKTP.CD?format=json";
    var data = await http.get(url);
    var jsonData = json.decode(data.body);
    var dataCountry = jsonData[1];

    return dataCountry;
  }

  getImageUrl() {
    return "https://www.countryflags.io/$imgCode/flat/64.png";
  }
}
