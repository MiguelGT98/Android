import 'package:WorldPovertyApp/models/Country.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

final formatCurrency = new NumberFormat.simpleCurrency();

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
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
        title: Text(widget.country.name),
        actions: <Widget>[],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Expanded(
              child: Column(
                children: <Widget>[
                  Image.network(
                    widget.country.getImageUrl(),
                    fit: BoxFit.fill,
                    width: double.infinity,
                  ),
                  new Container(
                      width: double.infinity,
                      padding: EdgeInsets.fromLTRB(0, 10, 0, 10),
                      child: Text(
                        widget.country.incomeGroup,
                        textAlign: TextAlign.center,
                        style: TextStyle(
                            fontSize: 24,
                            fontWeight: FontWeight.w600,
                            color: widget.country.getTextColor()),
                      )),
                  new Expanded(
                      child: FutureBuilder(
                          future: widget.country.fetchData(),
                          builder:
                              (BuildContext context, AsyncSnapshot snapshot) {
                            if (snapshot.data == null) {
                              return Container(
                                child: Center(
                                  child: Text("Loading"),
                                ),
                              );
                            }

                            return ListView.builder(
                              itemBuilder: (BuildContext context, int index) {
                                var date = snapshot.data[index]["date"];
                                var value = snapshot.data[index]["value"] !=
                                        null
                                    ? '${formatCurrency.format(snapshot.data[index]["value"])} USD'
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
            )
          ],
        ),
      ),
    );
  }
}

/* Expanded(
      child: Column(
        children: <Widget>[
          Image.network(
            widget.country.getImageUrl(),
            fit: BoxFit.fill,
          ),
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

                    return ListView.builder(
                      itemBuilder: (BuildContext context, int index) {
                        var date = snapshot.data[index]["date"];
                        var value = snapshot.data[index]["value"] != null
                            ? "${snapshot.data[index]["value"]}"
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
    ); */
