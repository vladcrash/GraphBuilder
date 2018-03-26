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
    private static final int MIN_VALUE = 10;

    private GraphRepository repository;
    private Graph graph;
    private ObservableField<String> thickness = new ObservableField<>("15");
    private ObservableInt color = new ObservableInt();
    private Context context;

    public AddEditGraphViewModel(Application app) {
        super(app);
        context = app.getApplicationContext();
        repository = GraphRepository.getInstance(app);
        graph = new Graph();
        graph.setColor(app.getResources().getColor(R.color.black));
        color.set(graph.getColor());
        graph.setThickness(thickness.get());
    }

    public void setNameFunction(String name) {
        graph.setFunction(name);
    }

    public void setThickness(int value) {
        thickness.set(String.valueOf(value + MIN_VALUE));
        graph.setThickness(String.valueOf(value + MIN_VALUE));
    }

    public ObservableField<String> getThickness() {
        return thickness;
    }

    public void saveGraph() {
        repository.addGraph(graph);
    }

    public Graph getGraph() {
        return graph;
    }

    public ObservableInt getColor() {
        return color;
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
