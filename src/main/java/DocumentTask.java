import java.util.concurrent.Future;

public class DocumentTask implements Runnable {
    private DocumentType documentType;
    private Integer id;
    private Boolean isInterrupted;
    private Future future;

    DocumentTask(DocumentType documentType, Integer id) {
        this.documentType = documentType;
        this.id = id;
        this.isInterrupted = false;
    }

    public void run() {
        try {
            Thread.sleep(documentType.getPrintingDuration());
        } catch (InterruptedException e) {
            isInterrupted = true;
        }
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public Integer getId() {
        return id;
    }

    public Future getFuture() {
        return future;
    }

    public Boolean getInterrupted() {
        return isInterrupted;
    }

    public String toString() {
        String result = getId() + ". " + documentType.getName() + " - " + documentType.getPrintingDuration()
                + "ms - " + documentType.getFormat().name();
        if (getInterrupted())
            result += " - недопечатан";
        return result;
    }
}
