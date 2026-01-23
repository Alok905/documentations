import java.util.ArrayList;
import java.util.List;

interface DocumentElement {
    void render();
}

class TextElement implements DocumentElement {
    private String text;

    public TextElement(String text) {
        this.text = text;
    }

    @Override
    public void render() {
        System.out.println(text);
    }
}

class ImageElement implements DocumentElement {
    private String path;

    public ImageElement(String path) {
        this.path = path;
    }

    @Override
    public void render() {
        System.out.println(path);
    }
}

class Document {
    private List<DocumentElement> elements;

    public Document() {
        this.elements = new ArrayList<>();
    }

    public void addElement(DocumentElement element) {
        elements.add(element);
    }

    List<DocumentElement> getElements() {
        return elements;
    }
}

interface Persistence {
    void save(String data);
}

class FilePersistence implements Persistence {
    @Override
    public void save(String data) {
        System.out.println("Saving to file: " + data);
    }
}

class DatabasePersistence implements Persistence {
    @Override
    public void save(String data) {
        System.out.println("Saving to database: " + data);
    }
}

class DocumentRenderer {
    private Document document;

    public DocumentRenderer(Document document) {
        this.document = document;
    }

    public void render() {
        for (DocumentElement element : document.getElements()) {
            element.render();
        }
    }
}

class DocumentEditor {
    private Document document;
    private Persistence persistence;

    public DocumentEditor(Document document, Persistence persistence) {
        this.document = document;
        this.persistence = persistence;
    }

    public void addElement(DocumentElement element) {
        document.addElement(element);
    }

    public void saveDocument() {
        StringBuilder data = new StringBuilder();
        for (DocumentElement element : document.getElements()) {
            // For simplicity, we just call render and capture output as string
            // In real scenario, we would have a method to get the content as string
            data.append(element.toString()).append("\n");
        }
        persistence.save(data.toString());
    }
}

public class Docs {

}
