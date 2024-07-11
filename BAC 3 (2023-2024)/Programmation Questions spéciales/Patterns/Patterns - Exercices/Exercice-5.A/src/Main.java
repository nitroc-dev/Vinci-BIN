public class Main {

    public static void main(String[] args) {
        Album album = new Album.Builder("The Dark Side of the Moon", "Pink Floyd")
                .label("Harvest")
                .producer("Pink Floyd")
                .pays("UK")
                .version("Original")
                .genre("Rock")
                .yearOfRelease(1973)
                .remastered(true)
                .yearOfOriginalRelease(1973)
                .standardDebit("CD")
                .subscribedDebit("Vinyl")
                .build();
        System.out.println(album);
    }
}
