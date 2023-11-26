public class Page {
    protected String surahName;
    protected int pageNo;
    protected int startingRuku;
    protected int endingRuku;
    protected int startingAyat;
    protected int endingAyat;

    public Page(String surahName, int pageNo, int startingRuku, int endingRuku, int startingAyat, int endingAyat) {
        this.surahName = surahName;
        this.pageNo = pageNo;
        this.startingRuku = startingRuku;
        this.endingRuku = endingRuku;
        this.startingAyat = startingAyat;
        this.endingAyat = endingAyat;
    }
}
