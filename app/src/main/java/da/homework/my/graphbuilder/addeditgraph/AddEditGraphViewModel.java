package da.homework.my.graphbuilder.addeditgraph;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;

import com.android.colorpicker.ColorPickerDialog;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;


public class AddEditGraphViewModel extends AndroidViewModel {
    public static final String DIALOG_COLOR_PICKER = "DIALOG_COLOR_PICKER";
    private static final int MIN_VALUE = 1;

    private GraphRepository repository;
    private Graph graph;
    private ObservableField<String> thickness = new ObservableField<>("15");
    private ObservableField<String> function = new ObservableField<>();
    private ObservableInt color = new ObservableInt();
    private ObservableInt progress = new ObservableInt(5);
    private ObservableField<String> from = new ObservableField<>("-5.0");
    private ObservableField<String> to = new ObservableField<>("5.0");
    private boolean isEdit;
    private int position;
    private Context context;

    public AddEditGraphViewModel(Application app) {
        super(app);
        context = app.getApplicationContext();
        repository = GraphRepository.getInstance(app);
        graph = new Graph();
        graph.setFunction("x");
        graph.setColor(app.getResources().getColor(R.color.black));
        color.set(graph.getColor());
        graph.setThickness(thickness.get());
        graph.setStartX(Double.valueOf(from.get()));
        graph.setEndX(Double.valueOf(to.get()));
    }

    public void setNameFunction(String name) {
        if (name == null || name.isEmpty()) {
            graph.setFunction("x");
        } else {
            graph.setFunction(name);
        }
    }

    public void setFrom(String from) {
        if (from != null && !from.isEmpty() && !from.equals("-") && !from.equals(".")) {
            graph.setStartX(Double.valueOf(from));
        }
    }

    public void setTo(String to) {
        if (to != null && !to.isEmpty() && !to.equals("-") && !to.equals(".")) {
            graph.setEndX(Double.valueOf(to));
        }
    }

    public void setThickness(int value) {
        thickness.set(String.valueOf(value + MIN_VALUE));
        graph.setThickness(String.valueOf(value + MIN_VALUE));
    }

    public ObservableField<String> getThickness() {
        return thickness;
    }

    public void saveGraph() {
        if (isEdit) {
            repository.editGraph(position, graph);
        } else {
            repository.addGraph(graph);
        }
    }

    public Graph getGraph() {
        return graph;
    }

    public ObservableInt getColor() {
        return color;
    }

    public ObservableInt getProgress() {
        return progress;
    }

    public ObservableField<String> getFunction() {
        return function;
    }

    public ObservableField<String> getFrom() {
        return from;
    }

    public ObservableField<String> getTo() {
        return to;
    }

    public void editGraph(int position) {
        this.position = position;
        isEdit = true;
        graph = repository.getGraph(position);
        color.set(graph.getColor());
        thickness.set(graph.getThickness());
        function.set(graph.getFunction());
        progress.set(Integer.valueOf(graph.getThickness()) - MIN_VALUE);
        from.set(String.valueOf(graph.getStartX()));
        to.set(String.valueOf(graph.getEndX()));
    }

    public ColorPickerDialog getColorPickerDialog() {
        int title = R.string.color_picker_title;
        int[] colors = context.getResources().getIntArray(R.array.dialog_colors);
        int numColumns = 5;
        int size = colors.length;

        ColorPickerDialog pickerDialog = ColorPickerDialog.newInstance(title, colors, 0, numColumns, size);
        pickerDialog.setOnColorSelectedListener(c -> {
            graph.setColor(c);
            color.set(c);
        });
        return pickerDialog;
    }
}
