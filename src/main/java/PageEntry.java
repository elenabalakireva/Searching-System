import java.util.Objects;

public class PageEntry implements Comparable<PageEntry> {
    private String pdfName;
    private int page;
    private int count;

    public PageEntry() {
    }

    public PageEntry(String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.page = page;
        this.count = count;
    }

    @Override
    public String toString() {
        return "PageEntry{" +
                "pdfName='" + pdfName + '\'' +
                ", page=" + page +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(PageEntry o) {
        if (count < o.count) {
            return -1;
        } else return 1;

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object obj) {
        PageEntry o = (PageEntry) obj;
        return pdfName.equals(o.pdfName) && page == o.page;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pdfName, page);
    }
}
