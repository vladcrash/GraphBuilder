package da.homework.my.graphbuilder.graphs;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;


public class GraphListViewModel extends AndroidViewModel {

    private GraphRepository repository;
    private LiveData<List<Graph>> graphs;

    public GraphListViewModel(Application application) {
        super(application);
        repository = new GraphRepository(application);
        graphs = repository.getGraphs();
    }

    public LiveData<List<Graph>> getGraphs() {
        return graphs;
    }
}
