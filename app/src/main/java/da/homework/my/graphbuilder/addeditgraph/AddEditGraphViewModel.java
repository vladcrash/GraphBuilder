package da.homework.my.graphbuilder.addeditgraph;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
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
    private ObservableInt thickness = new ObservableInt(15);
    private Context context;

    public AddEditGraphViewModel(Application application) {
        super(application);
        context = application.getApplicationContext();
        repository = GraphRepository.getInstance(application);
        graph = new Graph();
    }

    public void setNameFunction(String name) {
        graph.setFunction(name);
    }

    public void setThickness(int value) {
        thickness.set(value + MIN_VALUE);
        graph.setThickness(value + MIN_VALUE);
    }

    public ObservableInt getThickness() {
        return thickness;
    }

    public void saveGraph() {
        repository.addGraph(graph);
    }

    public ColorPickerDialog getColorPickerDialog() {
        int title = R.string.color_picker_title;
        int[] colors = context.getResources().getIntArray(R.array.dialog_colors);
        int numColumns = 5;
        int size = colors.length;

        ColorPickerDialog pickerDialog = ColorPickerDialog.newInstance(title, colors, 0, numColumns, size);
        pickerDialog.setOnColorSelectedListener(graph::setColor);
        return pickerDialog;
    }
}
