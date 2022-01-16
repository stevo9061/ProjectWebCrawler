# A3 - Web Crawler

# General Information
This project was built in collaboration, with the team members being:


- Stefan Bittgen
- Manuel Brüger

# Development process

For our project we started to think about what exactly we want to achieve and what is necessary for it. For this, milestones were recorded with our editor which gave us some direction. Then we assigned tasks for each group member to implement basic functions like extracting the necessary (desired) data from https://www.willhaben.at and further processing it and extracting it into an Excel spreadsheet. One of the biggest hurdles was to extract the data from the website. The team's main focus was on stability once we had a working graphical application. After we were able to complete almost all of our objectives, we began documenting our functions with Javadoc and prepared for the completion of the project.


# External libraries used

Gson is a Java library that can be used to convert Java Objects into their JSON representation.
https://github.com/google/gson

Jsoup is a Java library for working with real-world HTML.
https://jsoup.org/

Apache POI - Java API To Access Microsoft Format Files 
https://mvnrepository.com/artifact/org.apache.poi/poi

External classes used: 

CSVWriter

# Project Structure

Inside the Project, there's a folder structure, which contains various files as described below:

`src/.../fhtw/` ... contains all self-created project classes and much more.

`src/.../fhtw/ExampleFromAJsonWillhaben` contains a small part of the .json file to get an idea of how something like this looks like.

`src/.../fhtw/HowToSetMyColumnsWithAcsv` This tutorial describes how to split data from a *.csv file to multiple columns very quickly.


`src/.../fhtw/TestExperiments` In this directory there is only a text file in which it was stated with which test parameters our program was tested for the most part. In principle, however, our program can be used to search for any element on. Willhaben.

`src/.../resources/icons` In this directory is our icon for the app.

`src/.../resources/styles` There is a small css file in this directory, it was only used for some experimentation.

`src/.../resources/` Here are our two FXML files and the logo for our program.

 The project includes Javadoc-Documentation, which is available in our project directory.

 # How to use the program

In general, our program is designed in such a way that you can only search for various articles if you want to. The user can determine which articles these are with a line of text, the number of elements is fixed at 25, 50, or 100 and the postal code can also be used with a line of text for further limitation. After the entries, another window opens and these elements are already displayed in a table view. The data export to a *csv and *xls has already been carried out. In the "TestExperiments" folder, there are a few sample inputs that the program should definitely work with, but hopefully other inputs should also be possible without further ado.


**Must Have Features (Genügend - 4)**

•	Es kann nahezu jeder Artikel auf Willhaben gesucht werden. (Externe Libraries wurden hier verwendet - Jsoup, Gson) **CHECK**

•   Die Ergebnisse werden in ein *.csv exportiert welches danach über Excel mit nur wenigen Klicks in Spalten aufbereitet werden kann (Eine Dokumentation dazu wurde erstellt und liegt im Repository). **CHECK**

•	Die Ergebnisse werden in ein *.xls mit Aufteilung in mehrere Spalten und mit diversen Formatierungen automatisch einwandfrei exportiert. Das nachträgliche Aufbereiten des Files entfällt hier. (Externe Library wurde hier verwendet - Apache POI) **CHECK**

•	Das *.csv & *.xls File wird nach jeden erneuten Start des Programmes ersetzt. **CHECK**

•	Absichern des Programmes **CHECK**

•	File I/O **CHECK**

•	Netzwerk **CHECK**

•	Multithreading - Unser Ansatz war hier einen zweiten Thread zu erstellen welcher uns Daten in jeweils ein ArrayList für unser *.xls und einmal für unser *.csv einliest. **CHECK**

•	Kommentieren mit JavaDoc **CHECK**


**Should Have Features (Befriedigend - 3)**

•	Filter in der Tableview (GUI) können gesetzt werden **NO CHECK**

•	Weitere Parameter wie maximale Anzahl der zu suchenden Artikel kann eingestellt werden **CHECK**

•	Weitere Parameter wie die Postleitzahl des zu suchenden Artikels kann eingestellt werden **CHECK**



**Nice to Have Features (Gut - 2)**


•	Wir können auch nach Laptops suchen und damit verbunden nach mehreren Marken und verschiedenen Serien, zB. suche Laptop von Marke Dell, HP, Lenovo, Microsoft und der Serie xyz oder nach allen. **CHECK**

**Overkill (Sehr Gut - 1)**

•	GUI eigene Grafiken, Buttons teils **CHECK**





