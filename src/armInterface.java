public interface armInterface {
    void pickUp(block Li, location loc);
    void putDown(block Li, location loc);
    void stack(block Li, location loc);
    void unstack(block Li, location loc);
    void move(block Li, location start, location end);
}
