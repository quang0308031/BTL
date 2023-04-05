package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDataAccessObject<T extends Serializable> {
    private String fileName;
    
    /**
     * 
     * @param fileName
     * Lấy đường dẫn
     */
    public FileDataAccessObject(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 
     * @param data
     * Lưu dữ liệu từ file
     */
    public void save(List<T> data) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return 
     * Lấy dữ liệu từ file
     */
    public List<T> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}