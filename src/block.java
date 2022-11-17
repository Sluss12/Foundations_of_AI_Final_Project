public class block implements blockInterface{
    location blockLocation;
    public block(location Li) {
        this.blockLocation = Li;
    }

    @Override
    public boolean above(block B) { // is block THIS above block B

        return true;
    }
    @Override
    public boolean above(location Li) { // is block THIS above location Li

        return true;
    }

    @Override
    public boolean on(block B) { // is block THIS on block B

        return true;
    }

    @Override
    public boolean clear() { // is anything on THIS block

        return true;
    }

    @Override
    public boolean table() { // is THIS block on the table

        return true;
    }
}
