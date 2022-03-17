# Android-Advanced-SemesterPrj

### Konzept und Implementierung
Die ***Nature-Observer-App*** aus dem Repo Android-Basics in ***Version 2.0*** wurde um eine Wetter-API (https://openweathermap.org/api) erweitert und die Architektur umgestaltet.
Das Architekturmodel basiert nun auf dem ***MVVM-Konzept***.
Das Model (Outdoor-Erlebnisse) werden nicht mehr in den SharedPreferences abgelegt sondern über ein DAO-Interface und SQL-Befehlen in der Google Room Datenbank gespeichert.
Mittels Observer-Pattern und Live-Data werden die Daten aus Google Room über das Repository ausgelesen.
