package lab_5task2.SafiUllah_SE031.service;

import lab_5task2.SafiUllah_SE031.model.Document;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DocumentService {

    private final List<Document> documents = new ArrayList<>();
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("csv", "pdf", "doc", "docx");

    public String uploadDocument(MultipartFile file) {
        if (file.isEmpty()) {
            return "Error: File is empty.";
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return "Error: Invalid file name.";
        }

        String fileExtension = getFileExtension(originalFilename);
        if (!ALLOWED_EXTENSIONS.contains(fileExtension)) {
            return "Error: Only CSV, PDF, and Word files are allowed.";
        }

        Document doc = new Document(
                UUID.randomUUID().toString(),
                originalFilename,
                LocalDateTime.now().toString(),
                file.getSize()
        );

        documents.add(doc);
        return "File uploaded successfully: " + originalFilename;
    }

    public List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    public String deleteDocument(String id) {
        boolean removed = documents.removeIf(doc -> doc.getId().equals(id));
        return removed ? "Document deleted successfully." : "Document not found.";
    }

    private String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        return (lastDotIndex == -1) ? "" : fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
