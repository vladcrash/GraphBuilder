package da.homework.my.graphbuilder.graph;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import java.util.List;

import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;

public class GraphViewModel extends AndroidViewModel {

    private GraphRepository repository;
    private LiveData<List<Graph>> graphs;
    private ObservableField<Graph> graph = new ObservableField<>();

    public GraphViewModel(@NonNull Application application) {
        super(application);
        repository = GraphRepository.getInstance(application);
        graphs = repository.getGraphs();
    }

    public LiveData<List<Graph>> getGraphs() {
        return graphs;
    }

    public ObservableField<Graph> getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph.set(graph);
    }
}
