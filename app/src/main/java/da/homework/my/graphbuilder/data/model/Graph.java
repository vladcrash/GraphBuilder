package da.homework.my.graphbuilder.data.model;

import android.arch.persistence.room.Ignore;

import com.github.mikephil.charting.data.Entry;

import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.Function;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private String function;
    private int color;
    private int thickness;
    private double startX = -1.0;
    private double endX = 5.0;
    private boolean isShow;
    private Function fun;

    public Graph() {
        isShow = true;
    }

    @Ignore
    public Graph(String function, int color, int thickness, boolean isShow) {
        this.function = function;
        fun = new Function(function);
        this.color = color;
        this.thickness = thickness;
        this.isShow = isShow;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
        fun = new Function(function);
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

    public double getStartX() {
        return startX;
    }

    public void setStartX(double startX) {
        this.startX = startX;
    }

    public double getEndX() {
        return endX;
    }

    public void setEndX(double endX) {
        this.endX = endX;
    }

    public List<List<Entry>> getList() {
        List<List<Entry>> line = new ArrayList<>();
        List<Entry> entries = new ArrayList<Entry>();
        for (double x = startX; x < endX; x = x + 0.1) {
            double y = new Expression("f(x)",
                    fun, new Argument("x = " + x)).calculate();
            if (Double.isNaN(y)) {
                if(!entries.isEmpty()) {
                    line.add(entries);
                    entries = new ArrayList<>();
                }
            } else
                entries.add(new Entry((float) x, (float) y));
        }
        line.add(entries);
        return line;
    }
}
