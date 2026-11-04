# Filminator

Java Swing catalogue for movies, series and documentaries. The app uses Java 8 and an IntelliJ GUI Designer form.

## Run

From the project folder, run the supplied JAR with Java 8 or later.

```bash
java -jar out/Filminator.jar
```

To work on the source, open this folder as an IntelliJ IDEA project, select JDK 8, enable Swing UI Designer and run `Main`. The project's libraries are in `lib/`.

## Use

- Choose a content type, fill the form and click **New** to add an entry.
- Select a title in **Catalogue** to see its details.
- Click **Export** to save the entries to an XML file. Enter a filename ending in `.xml`.
- Click **Import** to open a saved XML catalogue. A successful import replaces the current entries.
- Click **Close** or the window's X to exit after confirmation. Export first to keep the current catalogue.

## JavaDoc

Open `out/javadoc/index.html` in a browser. To regenerate it with the JDK:

```bash
javadoc -d out/javadoc -classpath 'lib/*' \
  src/Main.java src/model/*.java src/view/*.java src/controller/*.java src/util/*.java
```
