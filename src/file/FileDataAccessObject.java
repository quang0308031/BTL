package file;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import application.CustomFunction;

/**
 * 
 * @author Nguyễn Thanh Quang
 *
 * @param <T>
 * 
 * Lớp thực hiện lưu và tải dữ liệu.
 */
public class FileDataAccessObject<T extends Serializable> {
    private String fileName;
    
    /**
     * Tạo đối tượng FileDataAccessObject
     */
    public FileDataAccessObject() {}

    public FileDataAccessObject(String fileName) {
		super();
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
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
        } catch (Exception e) {
            CustomFunction.showErrorAlert("Bạn chưa tải file hoặc không thể lưu vào đường dẫn này!");
        }
    }

    /**
     * 
     * @return 
     * Lấy dữ liệu từ file
     */
    @SuppressWarnings("unchecked")
	public List<T> load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) in.readObject();
        } catch (Exception e) {
            CustomFunction.showErrorAlert("Không thể mở file từ đường dẫn này!");
        } 
        return new ArrayList<>();
    }
}