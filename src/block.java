public class block {
    location blockLocation;
    public block(location Li) {
        this.blockLocation = Li;
    }

    public boolean above(block B) { // is block THIS above block B

        return true;
    }

    public boolean above(location Li) { // is block THIS above location Li

        return true;
    }

    public boolean on(block B) { // is block THIS on block B

        return true;
    }

    public boolean clear() { // is anything on THIS block

        return true;
    }

    public boolean table() { // is THIS block on the table

        return true;
    }
}
