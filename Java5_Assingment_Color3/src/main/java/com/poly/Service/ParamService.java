package com.poly.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

//Bài 1
@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    /**
    * Đọc chuỗi giá trị của tham số
    * @param name tên tham số
    * @param defaultValue giá trị mặc định
    * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    */
    public String getString(String name, String defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return value;
        } else {
            return defaultValue;
        }
    }

    /**
    * Đọc số nguyên giá trị của tham số
    * @param name tên tham số
    * @param defaultValue giá trị mặc định
    * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
	*/
    public int getInt(String name, int defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        return defaultValue;
    }

    /**
    * Đọc số thực giá trị của tham số
    * @param name tên tham số
    * @param defaultValue giá trị mặc định
    * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    */
    public double getDouble(String name, double defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // do nothing
            }
        }
        return defaultValue;
    }

    /**
    * Đọc giá trị boolean của tham số
    * @param name tên tham số
    * @param defaultValue giá trị mặc định
    * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    */
    public boolean getBoolean(String name, boolean defaultValue) {
        String value = request.getParameter(name);
        if (value != null) {
            return Boolean.parseBoolean(value);
        }
        return defaultValue;
    }

    /**
    * Đọc giá trị thời gian của tham số
    * @param name tên tham số
    * @param pattern là định dạng thời gian
    * @return giá trị tham số hoặc null nếu không tồn tại
    * @throws RuntimeException lỗi sai định dạng
    */
    public Date getDate(String name, String pattern) {
        String value = request.getParameter(name);
        if (value != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
                return (Date) dateFormat.parse(value);
            } catch (Exception e) {
                throw new RuntimeException("Invalid date format: " + pattern, e);
            }
        }
        return null;
    }

    /**
    * Lưu file upload vào thư mục
    * @param file chứa file upload từ client
    * @param path đường dẫn tính từ webroot
    * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
    * @throws RuntimeException lỗi lưu file
    */
    public File save(MultipartFile file, String path) {
        if (!file.isEmpty() && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            String fileName = file.getOriginalFilename();
            try (InputStream inputStream = file.getInputStream()) {
                File uploadedFile = new File(path, fileName);
                if (!uploadedFile.getParentFile().exists()) {
                    uploadedFile.getParentFile().mkdirs();
                }
                file.transferTo(uploadedFile);
                return uploadedFile;
            } catch (IOException | IllegalStateException e) {
                throw new RuntimeException("Failed to save file", e);
            }
        }
        return null;
    }
}
