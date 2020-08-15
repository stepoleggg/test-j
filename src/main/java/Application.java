import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        PrintManager printManager = new PrintManager();
        DocumentTaskBuilder documentTaskBuilder = new DocumentTaskBuilder();
        Scanner scn = new Scanner(System.in);

        System.out.println("Доступные команды: ");
        System.out.println("drawing - добавить в очередь чертеж");
        System.out.println("declaration - добавить в очередь заявление");
        System.out.println("newspaper - добавить в очередь газету");
        System.out.println("poster - добавить в очередь плакат");
        System.out.println("stop - остановить диспетчер");
        System.out.println("printedlist-by-order - вывести напечатанные документы в порядке печати");
        System.out.println("printedlist-by-type - вывести напечатанные документы в порядке типов документов");
        System.out.println("printedlist-by-duration - вывести напечатанные документы в порядке продолжительности печати");
        System.out.println("printedlist-by-paper - вывести напечатанные документы в порядке размера бумаги");
        System.out.println("average-duration - вывести среднюю продолжительность печати");
        System.out.println("cancel-x - отменить печать документа с id=x");

        while(true) {
            String reading = scn.next();
            if (reading.equals("drawing")) {
                DocumentTask task = documentTaskBuilder.createDocumentTask(DocumentType.DRAWING);
                printManager.print(task);
                System.out.println("Добавлен документ в очередь:");
                System.out.println(task.toString());
            } else if (reading.equals("declaration")) {
                DocumentTask task = documentTaskBuilder.createDocumentTask(DocumentType.DECLARATION);
                printManager.print(task);
                System.out.println("Добавлен документ в очередь:");
                System.out.println(task.toString());
            } else if (reading.equals("newspaper")) {
                DocumentTask task = documentTaskBuilder.createDocumentTask(DocumentType.NEWSPAPER);
                printManager.print(task);
                System.out.println("Добавлен документ в очередь:");
                System.out.println(task.toString());
            } else if (reading.equals("poster")) {
                DocumentTask task = documentTaskBuilder.createDocumentTask(DocumentType.POSTER);
                printManager.print(task);
                System.out.println("Добавлен документ в очередь:");
                System.out.println(task.toString());
            } else if (reading.equals("stop")) {
                List<DocumentTask> uncompleted = printManager.stop();
                System.out.println("Ненапечатанные документы:");
                printDocumentList(uncompleted);
                return;
            } else if (reading.equals("printedlist-by-order")) {
                List<DocumentTask> completed = printManager.printedListByOrder();
                System.out.println("Напечатанные документы:");
                printDocumentList(completed);
            } else if (reading.equals("printedlist-by-type")) {
                List<DocumentTask> completed = printManager.printedListByType();
                System.out.println("Напечатанные документы:");
                printDocumentList(completed);
            } else if (reading.equals("printedlist-by-duration")) {
                List<DocumentTask> completed = printManager.printedListByDuration();
                System.out.println("Напечатанные документы:");
                printDocumentList(completed);
            } else if (reading.equals("printedlist-by-paper")) {
                List<DocumentTask> completed = printManager.printedListByPaper();
                System.out.println("Напечатанные документы:");
                printDocumentList(completed);
            } else if (reading.equals("average-duration")) {
                Double averageDuration = printManager.getAverageDuration();
                System.out.println("Средняя продолжительность печати: " + averageDuration + "ms");
            } else if (reading.startsWith("cancel-")) {
                try {
                    Integer id = Integer.parseInt(reading.substring(7));
                    if (printManager.cancelTask(id)) {
                        System.out.println("Печать документа отменена");
                    } else {
                        System.out.println("Документ с id=" + id + " в очереди на печать не найден");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Неверное значение id документа");
                }
            } else {
                System.out.println("Неизвестная команда");
            }

        }
    }

    private static void printDocumentList(List<DocumentTask> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("-");
            return;
        }
        for (DocumentTask task : tasks) {
            System.out.println(task.toString());
        }
    }
}
