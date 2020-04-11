# Generator variabile aleatoare

![](https://img.shields.io/badge/Let's%20Plot-1.3.1-blueviolet)
![](https://img.shields.io/badge/Java-11-red)
![](https://img.shields.io/badge/Gradle-6.3-blue)

# Cum se construieste si compileaza proiectul

## 1. Varianta perfecta
Descarcati IDE-ul [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), importati proiectul si apasati pe butonul de run. IDE-ul va descarca automat toate dependintele necesare si va va alerta daca nu aveti versiunea de JAVA cel putin 11.

## 2. Fara IDE

### Pe GNU/Linux:

1. Descarcati **JAVA**
   - *Ubuntu:* `sudo apt install openjdk-11-jdk`
   - *Arch:* `sudo pacman -S openjdk-11-jdk`

2. Descarcati **Gradle**
   - *Ubuntu:* `sudo apt install gradle`
   - *Arch:* `sudo pacman -S gradle`

3. Descarcati **Compilatorul de cod Kotlin**
   - *Ubuntu:* `sudo snap install kotlin`
   - *Arch:* `sudo snap install kotlin` sau daca nu aveti snapd instalat `sudo pacman -S kotlin`

4. Deschideti folderul cu codul sursa si rulati comanda: `./gradlew build` sau pentru a specifica versiunea de java dorita (daca 11 sau mai recenta nu este cea implicita) rulati `./gradlew build -Dorg.gradle.java.home={calea_catre_java}`. Spre exemplu eu as compila cu: `./gradlew build -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk/`

5. Pentru a rula proiectul executati `./gradlew run`, respectiv `./gradlew run -Dorg.gradle.java.home=/usr/lib/jvm/java-11-openjdk/`

### Pe Windows:

Deoarece Windows nu dispune de un manager oficial de pachete nu pot recomanda decat descarcarea manuala a programelor de mai sus. Urmatoarele link-uri duc catre paginile oficiale de descarcare:

1. [**Java**](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
2. [**Gradle**](https://gradle.org/install/) -- urmati instructiunile de la sectiunea *Installing manually*
3. [**Kotlin**](https://kotlinlang.org/docs/tutorials/command-line.html) -- urmati instructiunile din *Manual install* sau din sectiunea *Chocolatey*

Pentru a compila si rula proiectul folositi pasii 4 si 5 de mai sus dar in loc de `./gradlew` tastati `gradlew.bat`

## Versiune precompilate
Pentru a rula proiectul fara a-l compila puteti descarca varianta precompilata de [aici](https://github.com/alexandru-balan/generator_variabile_aleatoare/releases/tag/2020.alpha.2). Executand fisierul .jar ar trebui sa vedeti graficele celor doua variabile aleatoare specificate in cerinta proiectului.

ATENTIE!: Pentru a executa fisierul veti avea in continuare nevoie de JAVA 11 instalat pe sistemul dumneavoastra. Daca JAVA 11 nu este versiunea implicita puteti deschide un terminal si navigati catre locul unde se afla fisierul .jar apoi executati: `{calea_catre_java11}/bin/java -jar Generator.jar`. Spre exemplu:  `/usr/lib/jvm/java-11-openjdk/bin/java -jar Generator.jar`

Pont: Pentru aflarea versiunii predefinite de java rulati intr-un terminal `java -version`

# Structura proiectului

Codul sursa se afla in `generator_variabile_aleatoare/src/main/kotlin/simulare2`.

Pachetul *simulare2* este pachetul principal al proiectul si contine clasele ce definesc comportamentul variabilelor aleatoare cu:
- distributie uniforma (in plus)
- distributie gama
- distributie exponentiala

Pachetul *interfaces* contine on interfata *Generator* si o clasa abstracta *Plotable* pe care le implementeaza toate variabilele de mai sus, deoarece pot genera o serie de valori aleatoare pe care le poate lua variabila, iar din acestea poate fi realizat un grafic ce va fi mai apoi afisat utilizatorului la rulare.

Pachetul *constants* contine un fisier ce defineste toate constantele folosite in proiect.

Pachetul *playground* contine fisierul *Main.kt* care contine functia main. Modificati acest fisier pentru a schimba output-ul programului.

## Clasa [ExponentialVariable](https://github.com/alexandru-balan/generator_variabile_aleatoare/blob/master/src/main/kotlin/simulare2/ExponentialVariable.kt)

Defineste comportamentul unei variabile cu distributie exponentiala. Primeste un singur parametru lambda si genereaza valori folosind a treia teorema de respingere. Pentru generare este mai intai generata o variabila cu distributie exponentiala de parametru 1 iar apoi din valorile acesteia se obtin valorile variabilei noastre prin impartire la lambda.

## Clasa [GammaVariable](https://github.com/alexandru-balan/generator_variabile_aleatoare/blob/master/src/main/kotlin/simulare2/GammaVariable.kt)

Comportamentul este asemanator clasei de mai sus cu urmatoarele exceptii:
- Generarea se face folosind teorema 2 de respingere pentru variabile gama
- Clasa nu se instantiaza prin constructor ci prin metoda newInstance() care poate intoarce o instanta de tip GammaVariable sau nu. Aceast lucru a fost implementat deoarece algoritmii de generare sunt diferiti in functie de parametrul ν. Acest proiect a fost creat pentru ν > 1, deci incercarea de a instantia GammaVariable cu ν subunitar va esua.

## Metode importante ale claselor de mai sus

- *generate(size : Int)*: Creaza o lista de valori aleatoare distribuite in functie de distributia varibilei alese. In mod predefinit se vor genera 100_000 de valori. Parametrul size defineste cate valori se genereaza.
- *plot()*: Creaza si afiseaza graficul distributiei variabilei

Daca *plot* se apeleaza inaintea lui *generate* atunci graficul afisat va fi gol.
