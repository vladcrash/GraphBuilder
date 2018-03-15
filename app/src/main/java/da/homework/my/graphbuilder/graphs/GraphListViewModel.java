package da.homework.my.graphbuilder.graphs;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.view.View;
import android.widget.CheckBox;

import java.util.List;

import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;


public class GraphListViewModel extends AndroidViewModel {

    private GraphRepository repository;
    private LiveData<List<Graph>> graphs;

    public GraphListViewModel(Application application) {
        super(application);
        repository = GraphRepository.getInstance(application);
        graphs = repository.getGraphs();
    }

    public LiveData<List<Graph>> getGraphs() {
        return graphs;
    }

    public void updateGraph(View view, int position) {
        repository.updateGraph(((CheckBox) view).isChecked(), position);
    }
}
