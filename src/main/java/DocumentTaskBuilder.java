public class DocumentTaskBuilder {
    private int currentId = 0;

    public DocumentTask createDocumentTask(DocumentType documentType) {
        DocumentTask task = new DocumentTask(documentType, currentId);
        currentId++;
        return task;
    }
}
