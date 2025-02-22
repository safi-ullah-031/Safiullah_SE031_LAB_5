package lab_5task2.SafiUllah_SE031.service;

import lab_5task2.SafiUllah_SE031.model.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentService {

    private static final String UPLOAD_DIR = "C:/Users/CUI/uploads/";
    private final List<Document> documents = new ArrayList<>();

    public String uploadDocument(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "Failed: Empty file uploaded.";
            }

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            File destFile = new File(uploadDir, uniqueFileName);
            file.transferTo(destFile);

            Document doc = new Document(
                    UUID.randomUUID().toString(),
                    uniqueFileName,
                    LocalDateTime.now().toString(),
                    file.getSize()
            );

            documents.add(doc);

            return "File uploaded successfully: " + uniqueFileName;
        } catch (IOException e) {
            return "Upload failed: " + e.getMessage();
        }
    }

    public List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    public String deleteDocument(String id) {
        boolean removed = documents.removeIf(doc -> doc.getId().equals(id));
        return removed ? "Document deleted successfully." : "Document not found.";
    }
}