package ctare.core;

/**
 * Created by ctare on 2020/05/17.
 */
public class Color {
    public final int r, g, b, a;
    public Color(float r, float g, float b) {
        this(r, g, b, 0);
    }

    public Color(float r, float g, float b, float a) {
        this((int)r, (int)g, (int)b, (int)a);
    }

    public Color(int r, int g, int b) {
        this(r, g, b, 0);
    }

    public Color(int r, int g, int b, int a) {
        this.r = r;
        this.b = b;
        this.g = g;
        this.a = a;
    }

    @Override
    public String toString() {
        return String.format("%d, %d, %d: %d", r, g, b, a);
    }
}
