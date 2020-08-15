public enum DocumentType {
    DRAWING("Чертеж", 10000, PaperFormat.A1),
    DECLARATION("Заявление", 1000, PaperFormat.A4),
    NEWSPAPER("Газета", 5000, PaperFormat.A5),
    POSTER("Плакат", 8000, PaperFormat.A2);

    private String name;
    private Integer printingDuration;
    private PaperFormat format;

    DocumentType(String name, Integer printingDurationMilliSecs, PaperFormat format) {
        this.name = name;
        this.printingDuration = printingDurationMilliSecs;
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public Integer getPrintingDuration() {
        return printingDuration;
    }

    public PaperFormat getFormat() {
        return format;
    }
}
