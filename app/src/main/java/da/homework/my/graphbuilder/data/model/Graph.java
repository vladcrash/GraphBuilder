package da.homework.my.graphbuilder.data.model;

import android.arch.persistence.room.Ignore;

/**
 * I love Java)
 */

public class Graph {

    private String function;
    private int color;
    private int thickness;
    private boolean isShow;

    public Graph() {
        isShow = true;
    }

    @Ignore
    public Graph(String function, int color, int thickness, boolean isShow) {
        this.function = function;
        this.color = color;
        this.thickness = thickness;
        this.isShow = isShow;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
