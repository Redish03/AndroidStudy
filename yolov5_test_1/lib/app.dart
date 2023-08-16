import 'package:flutter/material.dart';
import 'package:flutter_hooks/flutter_hooks.dart';

import 'package:yolov5_test_1/ui/main_page.dart';

class MyApp extends HookWidget {
  const MyApp({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: true,
      title: 'MyApp',
      theme: ThemeData.from(
        colorScheme: const ColorScheme.dark(),
      ),
      navigatorKey: GlobalKey<NavigatorState>(),
      initialRoute: MainPage.routeName,
      routes: {
        MainPage.routeName: (context) => const MainPage(),
      },
    );
  }
}