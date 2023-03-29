package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataAccessObject<T extends Serializable> {
    private String fileName;

    public FileDataAccessObject(String fileName) {
        this.fileName = fileName;
    }

    public void save(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}