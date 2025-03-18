# RPG Battle Game  

Dieses Spiel ist ein textbasiertes Rollenspiel, in dem der Spieler einen Charakter erstellt und ihn durch verschiedene Kämpfe verbessert. Der Charakter kann gegen andere Kämpfer antreten, Erfahrungspunkte sammeln und im Level aufsteigen.  

Das Spiel kombiniert Entscheidungen des Spielers mit automatisierten, zufallsbasierten Kampfsimulationen.  

---

## Spielbeschreibung  

Der Spieler kann eine eigene Spielfigur erstellen, in einer SQLite-Datenbank speichern und weiterentwickeln. Zur Auswahl stehen drei verschiedene Klassen: Magier, Krieger oder Späher. Jede Klasse hat ihre eigenen Stärken und Schwächen, die sich auf die Kämpfe auswirken. Die Kämpfe selbst laufen automatisch ab.  

---

## Spielmechanik  

Das Spiel besteht aus verschiedenen Phasen, in denen der Spieler aktiv Entscheidungen trifft oder den Kampfverlauf beobachtet.  

### Manuelle Entscheidungen  

- Erstellung eines neuen Charakters: Der Spieler wählt einen Namen und eine Klasse.  
- Auswahl der Kampfart: Der Spieler entscheidet, ob er einem Duell, einem Training oder einem Turnier beitreten möchte.  
- Speichern und Laden eines Charakters: Der Spieler kann seinen Fortschritt speichern oder eine frühere Spielfigur laden.  

### Automatische Abläufe  

- Kämpfe laufen ohne Eingreifen des Spielers ab. Angriffe, Verteidigungen und Spezialfähigkeiten werden vom Spiel ausgeführt.  
- Erfahrungspunkte werden nach einem Kampf automatisch berechnet und verteilt.  
- Charakterwerte verbessern sich beim Levelaufstieg automatisch.  

---

## Charakterklassen  

Jeder Charakter gehört zu einer bestimmten Klasse. Jede Klasse hat eigene Attribute, die bestimmen, wie sich der Charakter im Kampf verhält.  

Klasse	Eigenschaften
- 🧙‍♂️ Magier: Hohe Intelligenz, nutzt Mana für mächtige Angriffe
- 🛡 Krieger: Starker Nahkämpfer mit hoher Rüstung. Bei geringer Gesundheit wechselt er in den Berserker-Modus und macht mehr Schaden
- 🏹 Späher: Agil, schnell und gut im Ausweichen. Bei kritischen Treffern macht er besonders viel Schaden
Jeder Charakter startet mit einem Basiswert für Gesundheit (HP), XP und klassenspezifischen Attributen.

Der Spieler kann seinen Charakter nicht direkt steuern, aber durch die Wahl der Klasse beeinflussen, welche Kampftechniken verwendet werden.  

---

## Kampfarten  

Der Spieler kann aus drei verschiedenen Kampfmodi wählen.  

1. **Duelle**  
   - 1-gegen-1-Kämpfe gegen einen zufälligen Gegner
   - Der Kampf wird automatisch ausgetragen.  
   - Nur der Gewinner erhält Erfahrungspunkte.  

2. **Training**  
   - Der Charakter tritt gegen mehrere Gegner in Übungskämpfen an.  
   - Jeder Kämpft zweimal gegen in einem Duell gegen jeden
   - Alle Kämpfe werden automatisch durchgeführt.  
   - Erfahrungspunkte werden je nach Leistung verteilt.  
   - Gute Möglichkeit, XP zu sammeln

3. **Turnier**  
   - Mehrere Charaktere treten in einem Turnier gegeneinander an. 
   - Verlierer scheiden aus dem Turnier aus
   - Die Turnierstruktur und die Kämpfe laufen automatisch ab.  
   - Der Gewinner erhält die höchste Erfahrungspunkte-Belohnung.  

Jeder Kampf läuft in Runden ab. Charaktere greifen sich abwechselnd an, bis einer besiegt wird.
Nach jedem Kampf wird der aktuelle Zustand des Charakters angezeigt, einschließlich der verbleibenden Gesundheit, der gewonnenen Erfahrung und des neuen Levels.  

---

## Levelaufstieg und Erfahrung  

Ein Charakter sammelt Erfahrungspunkte durch Kämpfe. Sobald eine bestimmte Anzahl erreicht ist, steigt er automatisch im Level auf.  

Durch einen Levelaufstieg verbessern sich die Attribute des Charakters:  

- Die maximale Gesundheit steigt.  
- Magier erhalten mehr Mana und Intelligenz, Krieger mehr Stärke und Rüstung, Späher mehr Ausdauer und Beweglichkeit.  

Die Menge an Erfahrungspunkten hängt von der Stärke des besiegten Gegners ab. Kämpfe gegen stärkere Gegner bringen mehr Erfahrung.  

---

## Speicherung und Laden von Charakteren  

Das Spiel ermöglicht es, Charaktere in einer Datenbank zu speichern und später wieder zu laden.  

Der Spieler hat folgende Optionen:  

- Einen neuen Charakter speichern.  
- Einen vorhandenen Charakter aktualisieren.  
- Einen gespeicherten Charakter laden und weiterspielen.  

Gespeicherte Charaktere bleiben in der Datenbank erhalten und können jederzeit weiterverwendet werden.  

---

## Installation und Ausführung  

### Voraussetzungen  

- Java 17 oder höher  
- SQLite-Treiber für Java (sqlite-jdbc-3.49.1.0) 

### Start des Spiels  

1. Den Code herunterladen und kompilieren:  
   ```bash
   javac Main.java
   ```  
2. Das Spiel starten:  
   ```bash
   java Main
   ```  

Das Spiel startet in einer textbasierten Umgebung, in der der Spieler Menüpunkte auswählt und den Fortschritt seines Charakters verfolgt.  

---

## Beispielhafter Spielablauf  

1. Das Spiel wird gestartet, und der Spieler wählt, ob er einen neuen Charakter erstellen oder einen gespeicherten laden möchte.  
2. Bei der Charaktererstellung gibt der Spieler einen Namen ein und entscheidet sich für eine Klasse.  
3. Nach der Erstellung gelangt der Spieler in das Hauptmenü und kann eine Kampfart wählen.  
4. Der Kampf startet automatisch, und der Spieler sieht den Kampfverlauf auf dem Bildschirm.  
5. Nach dem Kampf werden Erfahrungspunkte berechnet, und der Charakter kann aufsteigen.  
6. Der Spieler kann das Spiel speichern und später weiterspielen.  

---

## Weitere Entwicklungsmöglichkeiten  

Das Spiel bietet eine solide Grundlage für weitere Erweiterungen. Mögliche Verbesserungen könnten sein:  

- Mehr Charakterklassen mit einzigartigen Fähigkeiten  
- Gegenstände und Ausrüstung, die die Werte des Charakters beeinflussen  
- Ein fortgeschritteneres Kampfsystem mit taktischen Elementen  
- Eine grafische Benutzeroberfläche für bessere Darstellung  

Das Spiel ist für Erweiterungen offen und kann leicht angepasst werden.  

---

## Fazit  

Dieses Rollenspiel kombiniert einfache Menüführung mit automatisierten Kämpfen. Der Spieler trifft Entscheidungen über die Charakterentwicklung, während die Kämpfe selbst automatisch ablaufen.  

Das System ermöglicht eine kontinuierliche Verbesserung des Charakters, Langzeitmotivation durch Erfahrungspunkte und eine taktische Herangehensweise an die Kämpfe.  
