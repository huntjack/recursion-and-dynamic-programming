package stack_of_boxes;

public class Box {
    private int length;
    private int width;
    private int height;

    public Box(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    public boolean isSmallerThan(Box box) {
        if(box == null) {
            return false;
        }
        return length < box.length &&
                width < box.width &&
                height < box.height;
    }
    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
