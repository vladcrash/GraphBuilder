package da.homework.my.graphbuilder.addeditgraph;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;


public class AddEditGraphViewModel extends AndroidViewModel {

    private GraphRepository repository;
    private Graph graph;

    public AddEditGraphViewModel(Application application) {
        super(application);
        repository = GraphRepository.getInstance(application);
        graph = new Graph();
    }

    public void setNameFunction(String name) {
        graph.setFunction(name);
    }

    public void saveGraph() {
        repository.addGraph(graph);
    }
}
