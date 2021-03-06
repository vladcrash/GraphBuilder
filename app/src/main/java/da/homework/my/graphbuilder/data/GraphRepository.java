package da.homework.my.graphbuilder.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.data.model.Graph;


public class GraphRepository {
    private MutableLiveData<List<Graph>> graphs;
    private static GraphRepository INSTANCE;
    private List<Graph> list = new ArrayList<>();

    private GraphRepository(Application application) {
        graphs = new MutableLiveData<>();
        pleaseWork(application);
    }

    public static GraphRepository getInstance(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new GraphRepository(application);
        }

        return INSTANCE;
    }

    private void pleaseWork(Application app) {
        list.add(new Graph("x * x", app.getResources().getColor(R.color.blue), "6", true));
        list.add(new Graph("1 / x", app.getResources().getColor(R.color.green), "6", true));
//        list.add(new Graph("f(x) = e ^ x", app.getResources().getColor(R.color.amber), 12, true));
//        list.add(new Graph("f(x) = sin(x)", app.getResources().getColor(R.color.deep_orange), 12, false));
//        list.add(new Graph("f(x) = cos(x)", app.getResources().getColor(R.color.lime), 12, true));
        graphs.setValue(list);
    }

    public MutableLiveData<List<Graph>> getGraphs() {
        return graphs;
    }

    public void addGraph(Graph graph) {
        list.add(graph);
        graphs.setValue(list);
    }

    public Graph getGraph(int position) {
        return list.get(position);
    }

    public void editGraph(int position, Graph graph) {
        list.set(position, graph);
        graphs.setValue(list);
    }

    public void updateGraph(boolean isChecked, int position) {
        list.get(position).setShow(isChecked);
        graphs.setValue(list);
    }
}
