package hw3.hash;

public interface Oomage {
    default void draw(double x, double y) {
        draw(x, y, 1);
    }

    void draw(double x, double y, double scalingFactor);
} 
