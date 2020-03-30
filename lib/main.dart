import 'package:flutter/material.dart';
import 'dart:convert';
import 'dart:async';
import 'package:http/http.dart' as http;
import 'package:crypto/crypto.dart' as crypto;

const PUBLIC_KEY = "";
const PRIVATE_KEY = "";

void main() => runApp(new MarvelApp());

String generateMd5(String input) {
  return crypto.md5.convert(utf8.encode(input)).toString();
}

class MarvelApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      theme:
          ThemeData(brightness: Brightness.dark, primaryColor: Colors.blueGrey),
      debugShowCheckedModeBanner: false,
      home: Scaffold(
          appBar: AppBar(
            title: Text("Marvel Comics!"),
          ),
          body: InfinityDudes()),
    );
  }
}

class InfinityDudes extends StatefulWidget {
  @override
  ListInfinityDudesState createState() => new ListInfinityDudesState();
}

class ListInfinityDudesState extends State<InfinityDudes> {
  int _page = 0;
  int _offset = 10;

  Future<List<InfinityComic>> getDudes() async {
    var now = new DateTime.now();
    var md5D = generateMd5(now.toString() + PRIVATE_KEY + PUBLIC_KEY);
    var url =
        "https://gateway.marvel.com:443/v1/public/characters?limit=${_offset}&offset=${_offset * _page}=10&ts=" +
            now.toString() +
            "&apikey=$PUBLIC_KEY&hash=" +
            md5D;
    print(url);

    var data = await http.get(url);
    var jsonData = json.decode(data.body);
    List<InfinityComic> dudes = [];
    var dataMarvel = jsonData["data"];
    var marvelArray = dataMarvel["results"];
    for (var dude in marvelArray) {
      var thumb = dude["thumbnail"];
      var image = "${thumb["path"]}.jpg";
      InfinityComic infinityComic = InfinityComic(dude["name"], image);
      print("DUDE: " + infinityComic.title);
      dudes.add(infinityComic);
    }

    return dudes;
  }

  void _nextPage() {
    setState(() {
      _page = _page + 1;
    });
    pageController.text = "${_page}";
  }

  void _prevPage() {
    setState(() {
      if (_page > 0) _page = _page - 1;
    });
    pageController.text = "${_page}";
  }

  void _setPage(int value) {
    setState(() {
      _page = value;
    });
    pageController.text = "${value}";
  }

  var pageController = new TextEditingController();

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      body: Container(
          child: Column(
        children: <Widget>[
          new Expanded(
            child: FutureBuilder(
              future: getDudes(),
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
                    return ListTile(
                      title: Text(snapshot.data[index].title),
                      leading: CircleAvatar(
                        backgroundImage:
                            NetworkImage(snapshot.data[index].cover),
                      ),
                      onTap: () {
                        Navigator.push(context,
                            new MaterialPageRoute(builder: (context) {
                          return InfinityDetail(snapshot.data[index]);
                        }));
                      },
                    );
                  },
                  itemCount: snapshot.data.length,
                );
              },
            ),
          ),
          Container(
            padding: EdgeInsets.all(20.0),
            child: Row(
              children: <Widget>[
                Expanded(
                  child: FlatButton(
                    child: Text('Prev'),
                    color: Colors.blue,
                    onPressed: () {
                      _prevPage();
                    },
                  ),
                ),
                Expanded(
                  child: Container(
                    padding: EdgeInsets.fromLTRB(20, 0, 20, 0),
                    child: TextField(
                      keyboardType: TextInputType.number,
                      textAlign: TextAlign.center,
                      controller: pageController,
                      onSubmitted: (value) {
                        _setPage(int.parse(value));
                      },
                    ),
                  ),
                ),
                Expanded(
                  child: FlatButton(
                    child: Text('Next'),
                    color: Colors.blue,
                    onPressed: () {
                      _nextPage();
                    },
                  ),
                ),
              ],
            ),
          )
        ],
      )),
    );
  }
}

class InfinityComic {
  final String title;
  final String cover;
  InfinityComic(this.title, this.cover);
}

class InfinityDetail extends StatelessWidget {
  final InfinityComic infinityComic;
  InfinityDetail(this.infinityComic);
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
        appBar: AppBar(
          title: Text(infinityComic.title),
        ),
        body: Image.network(
          infinityComic.cover,
        ));
  }
}
