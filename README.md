# A3 - Web Crawler

Verwendete externe Libraries:

Gson is a Java library that can be used to convert Java Objects into their JSON representation.
https://github.com/google/gson

Jsoup is a Java library for working with real-world HTML.
https://jsoup.org/

Apache POI - Java API To Access Microsoft Format Files 
https://mvnrepository.com/artifact/org.apache.poi/poi

Verwendete externe Klassen: 

CSVWriter



**Must Have Features (Genügend - 4)**

•	Es kann nahezu jeder Artikel auf Willhaben gesucht werden. (Externe Libraries wurden hier verwendet - Jsoup, Gson)

•   Die Ergebnisse werden in ein *.csv exportiert welches danach über Excel mit nur wenigen Klicks in Spalten aufbereitet werden kann (Eine Dokumentation dazu wurde erstellt und liegt im Repository).

•	Die Ergebnisse werden in ein *.xls mit Aufteilung in mehrere Spalten und mit diversen Formatierungen automatisch einwandfrei exportiert. Das nachträgliche Aufbereiten des Files entfällt hier. (Externe Library wurde hier verwendet - Apache POI)

•	Das *.csv & *.xls File wird nach jeden erneuten Start des Programmes ersetzt. 

•	Absichern des Programmes

•	File I/O check

•	Netzwerk check

•	Multithreading check - Unser Ansatz war hier einen zweiten Thread zu erstellen welcher uns Daten in jeweils ein ArrayList für unser *.xls und einmal für unser *.csv einliest.

•	Kommentieren mit JavaDoc check


**Should Have Features (Befriedigend - 3)**

•	Filter in der Tableview (GUI) können gesetzt werden (Funktioniert noch nicht)

•	Weitere Parameter wie maximale Anzahl der zu suchenden Artikel kann eingestellt werden check

•	Weitere Parameter wie die Postleitzahl des zu suchenden Artikels kann eingestellt werden check



**Nice to Have Features (Gut - 2)**


•	Wir können auch nach Laptops suchen und damit verbunden nach mehreren Marken und verschiedenen Serien, zB. suche Laptop von Marke Dell, HP, Lenovo, Microsoft und der Serie xyz oder nach allen. check

**Overkill (Sehr Gut - 1)**

•	GUI eigene Grafiken, Buttons teils check





