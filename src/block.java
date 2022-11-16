public class block implements blockInterface{

    @Override
    public block above() {
        return above;
    }

    @Override
    public block on() {
        return on;
    }

    @Override
    public boolean clear() {
        return clear;
    }

    @Override
    public boolean table() {
        return table;
    }
}
