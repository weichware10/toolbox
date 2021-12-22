![test status](.github/badges/tests.svg) ![code coverage](.github/badges/jacoco.svg)
# Weichware-10: Toolbox

Beinhaltet Toolbox

## Installation
### 1. fertig kompiliert:
- Installer-Datei von [Releases](https://github.com/weichware10/toolbox/releases) herunterladen
    - .msi: Windows
    - .deb: Linux
    - .dmg: MacOS
- diese Dateien werden auch bei jedem Durchlauf des [test-Workflows](https://github.com/weichware10/toolbox/actions/workflows/tests.yaml) erstellt und sind dort herunterzuladen (nach Pushes auf main und in Pull Requests)

### 2. selbst kompilieren:
- Repository clonen
- `mvn clean install` laufen lassen
- die benötigten JMODs für das richtige Betriebssystem werden automatisch heruntergeladen (manuell durch `mvn validate` möglich)
- die erzeugte Installer-Datei befindet sich im target-Verzeichnis

Achtung: die Versionsnummern unterscheiden sich zwischen den beiden Optionen. Falls zwischen fertig kompiliert und selbst kompiliert gewechselt wird, könnte eine vorherige Deinstallation unter Umständen nötig sein.

## Starten ohne Installation in Entwicklungsumgebung (VSCode)
- Herunterladen der JavaFX-SDK auf [gluonhq.com](https://gluonhq.com/products/javafx/)
- Erstellen einer `launch.json` im Verzeichnis `.vscode`:
    ```json
    // .vscode/launch.json
    {
        "version": "0.2.0",
        "configurations": [
            {
                "type": "java",
                "name": "Launch Toolbox",
                "request": "launch",
                "vmArgs": "--module-path=C:/javafx-sdk-17.0.1/lib --add-modules=javafx.controls --add-modules=javafx.fxml",
                "mainClass": "github.weichware10.toolbox.Main",
                "projectName": "toolbox"
            }
        ]
    }
    ```
    - `--module-path=C:/javafx-sdk-17.0.1/lib` sollte je nach lokaler Installation angepasst werden, sodass es das `lib`-Verzeichnis der JavaFX-SDK referenziert

## Maven Lifecycle
|                |                                                                                |
| -------------- | ------------------------------------------------------------------------------ |
| `mvn clean`    | "Aufräumen" der Entwicklungsumgebung (Löschen des `target`-Verzeichnis)        |
| `mvn validate` | Ausführen von Checkstyle, Herunterladen von plattformspezifischen JavaFX-JMODs |
| `mvn test`     | Ausführen der Tests                                                            |
| `mvn install`  | Erstellen einer plattformspezifischen Installer-Datei                          |
| `mvn package`  | Alle Schritte bis auf erstellen der Installer-Datei durchführen                |
| `mvn site`     | verschiedene Plugins erstellen HTML-Dateien mit Informationen über das Projekt |
