package da.homework.my.graphbuilder.graph;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;

public class GraphViewModel extends AndroidViewModel {

    public static final String TAG = GraphViewModel.class.getSimpleName();

    private GraphRepository repository;
    private LiveData<List<Graph>> graphs;
    private ObservableField<List<Graph>> functionList = new ObservableField<>();

    public GraphViewModel(@NonNull Application application) {
        super(application);
        repository = GraphRepository.getInstance(application);
        init();
    }

    private void init() {
        graphs = repository.getGraphs();
    }

    public LineData getLineData() {
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        for (Graph graph : graphs.getValue()) {
            Log.wtf(TAG, "getLineData: " + graph.getList());
            LineDataSet dataSet = new LineDataSet(graph.getList(), graph.getFunction());
            dataSet.setColor(graph.getColor());
            dataSet.setLineWidth(Integer.valueOf(graph.getThickness()));
            dataSets.add(dataSet);
        }
        return new LineData(dataSets);
    }

    public LiveData<List<Graph>> getGraphs() {
        return graphs;
    }


    public ObservableField<List<Graph>> getFunctionList() {
        return functionList;
    }


    public void setFunctionList(List<Graph> functions) {
        functionList.set(functions);
    }
}
