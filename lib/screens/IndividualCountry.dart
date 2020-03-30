import 'package:WorldPovertyApp/models/Country.dart';
import 'package:flutter/material.dart';

class IndividualCountryScreen extends StatefulWidget {
  final Country country;
  IndividualCountryScreen({Key key, this.country}) : super(key: key);

  @override
  _IndividualCountryScreenState createState() =>
      _IndividualCountryScreenState();
}

class _IndividualCountryScreenState extends State<IndividualCountryScreen> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          Image.network(widget.country.getImageUrl()),
          new Expanded(
              child: FutureBuilder(
                  future: widget.country.fetchData(),
                  builder: (BuildContext context, AsyncSnapshot snapshot) {
                    if (snapshot.data == null) {
                      return Container(
                        child: Center(
                          child: Text("Loading"),
                        ),
                      );
                    }
                    print(snapshot.data);
                    return ListView.builder(
                      itemBuilder: (BuildContext context, int index) {
                        print(index);
                        var date = snapshot.data[index]["date"];
                        var value = snapshot.data[index]["value"] != null
                            ? snapshot.data[index]["value"]
                            : "Not available";

                        return ListTile(
                          title: Text(value),
                          subtitle: Text(date),
                        );
                      },
                      itemCount: snapshot.data.length,
                    );
                  }))
        ],
      ),
    );
  }
}
