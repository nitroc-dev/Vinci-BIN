public class Album {

    private final String title, artist;

    private final String label, producer, pays, version, genre;
    private final int yearOfRelease;

    private final boolean remastered;
    private final int yearOfOriginalRelease;
    private final String standardDebit, subscribedDebit;

    public Album(Builder builder) {
        this.title = builder.title;
        this.artist = builder.artist;
        this.label = builder.label;
        this.producer = builder.producer;
        this.pays = builder.pays;
        this.version = builder.version;
        this.genre = builder.genre;
        this.yearOfRelease = builder.yearOfRelease;
        this.remastered = builder.remastered;
        this.yearOfOriginalRelease = builder.yearOfOriginalRelease;
        this.standardDebit = builder.standardDebit;
        this.subscribedDebit = builder.subscribedDebit;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getLabel() {
        return label;
    }

    public String getProducer() {
        return producer;
    }

    public String getPays() {
        return pays;
    }

    public String getVersion() {
        return version;
    }

    public String getGenre() {
        return genre;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public boolean isRemastered() {
        return remastered;
    }

    public int getYearOfOriginalRelease() {
        return yearOfOriginalRelease;
    }

    public String getStandardDebit() {
        return standardDebit;
    }

    public String getSubscribedDebit() {
        return subscribedDebit;
    }

    @Override
    public String toString() {
        return "Album{" + "title=" + title + ", artist=" + artist + ", label=" + label + ", producer=" + producer + ", pays=" + pays + ", version=" + version + ", genre=" + genre + ", yearOfRelease=" + yearOfRelease + ", remastered=" + remastered + ", yearOfOriginalRelease=" + yearOfOriginalRelease + ", standardDebit=" + standardDebit + ", subscribedDebit=" + subscribedDebit + '}';
    }

    public static class Builder {
        private final String title, artist;

        private String label, producer, pays, version, genre;
        private int yearOfRelease;

        private boolean remastered;
        private int yearOfOriginalRelease;
        private String standardDebit, subscribedDebit;

        public Builder(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        public Builder label(String label) {
            this.label = label;
            return this;
        }

        public Builder producer(String producer) {
            this.producer = producer;
            return this;
        }

        public Builder pays(String pays) {
            this.pays = pays;
            return this;
        }

        public Builder version(String version) {
            this.version = version;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder yearOfRelease(int yearOfRelease) {
            this.yearOfRelease = yearOfRelease;
            return this;
        }

        public Builder remastered(boolean remastered) {
            this.remastered = remastered;
            return this;
        }

        public Builder yearOfOriginalRelease(int yearOfOriginalRelease) {
            if (!remastered) {
                throw new IllegalStateException("Album not remastered");
            }
            this.yearOfOriginalRelease = yearOfOriginalRelease;
            return this;
        }

        public Builder standardDebit(String standardDebit) {
            if (!remastered) {
                throw new IllegalStateException("Album not remastered");
            }
            this.standardDebit = standardDebit;
            return this;
        }

        public Builder subscribedDebit(String subscribedDebit) {
            if (!remastered) {
                throw new IllegalStateException("Album not remastered");
            }
            this.subscribedDebit = subscribedDebit;
            return this;
        }

        public Album build() {
            return new Album(this);
        }
    }
}
