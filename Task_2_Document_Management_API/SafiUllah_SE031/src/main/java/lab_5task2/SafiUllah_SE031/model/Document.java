package lab_5task2.SafiUllah_SE031.model;

public class Document {
    private String id;
    private String name;
    private String uploadDate;
    private long size;

    public Document(String id, String name, String uploadDate, long size) {
        this.id = id;
        this.name = name;
        this.uploadDate = uploadDate;
        this.size = size;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUploadDate() { return uploadDate; }
    public long getSize() { return size; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setUploadDate(String uploadDate) { this.uploadDate = uploadDate; }
    public void setSize(long size) { this.size = size; }
}
