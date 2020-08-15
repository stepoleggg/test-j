import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrintManager {
    private ExecutorService executorService;
    private List<DocumentTask> tasks;

    public PrintManager() {
        this.executorService = Executors.newSingleThreadExecutor();
        this.tasks = new LinkedList<DocumentTask>();
    }

    public void print(DocumentTask documentTask) {
        Future future = executorService.submit(documentTask);
        documentTask.setFuture(future);
        tasks.add(documentTask);
    }

    public List<DocumentTask> stop() {
        List<DocumentTask> uncompleted = new LinkedList<DocumentTask>();
        for (DocumentTask task : tasks) {
            if (!task.getFuture().isDone())
                uncompleted.add(task);
        }
        executorService.shutdownNow();
        return uncompleted;
    }

    public boolean cancelTask(Integer id) {
        for (DocumentTask task : tasks) {
            if (task.getId().equals(id)) {
                if (task.getFuture().isDone())
                    return false;
                task.getFuture().cancel(true);
                return true;
            }
        }
        return false;
    }

    public Double getAverageDuration() {
        List<DocumentTask> completed = printedList(null);
        int fullDuration = 0;
        for (DocumentTask task : completed) {
            fullDuration += task.getDocumentType().getPrintingDuration();
        }
        return fullDuration / (double) completed.size();
    }

    public List<DocumentTask> printedListByOrder() {
        return printedList(null);
    }

    public List<DocumentTask> printedListByType() {
        return printedList(new Comparator<DocumentTask>() {
            public int compare(DocumentTask o1, DocumentTask o2) {
                String typeName1 = o1.getDocumentType().getName();
                String typeName2 = o2.getDocumentType().getName();
                return typeName1.compareTo(typeName2);
            }
        });
    }

    public List<DocumentTask> printedListByDuration() {
        return printedList(new Comparator<DocumentTask>() {
            public int compare(DocumentTask o1, DocumentTask o2) {
                Integer duration1 = o1.getDocumentType().getPrintingDuration();
                Integer duration2 = o2.getDocumentType().getPrintingDuration();
                return duration1.compareTo(duration2);
            }
        });
    }

    public List<DocumentTask> printedListByPaper() {
        return printedList(new Comparator<DocumentTask>() {
            public int compare(DocumentTask o1, DocumentTask o2) {
                String format1 = o1.getDocumentType().getFormat().name();
                String format2 = o2.getDocumentType().getFormat().name();
                return format1.compareTo(format2);
            }
        });
    }

    private List<DocumentTask> printedList(Comparator<DocumentTask> comparator) {
        List<DocumentTask> completed = new LinkedList<DocumentTask>();
        for (DocumentTask task : tasks) {
            if (task.getFuture().isDone())
                completed.add(task);
        }
        if (comparator == null)
            return completed;
        Collections.sort(completed, comparator);
        return completed;
    }
}
