package da.homework.my.graphbuilder.graph;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import da.homework.my.graphbuilder.data.GraphRepository;
import da.homework.my.graphbuilder.data.model.Graph;

public class GraphViewModel extends AndroidViewModel {

    public static final String TAG = GraphViewModel.class.getSimpleName();

    private GraphRepository repository;
    private LiveData<String> graphs;
    private ObservableField<String> functionList = new ObservableField<>();

    public GraphViewModel(@NonNull Application application) {
        super(application);
        repository = GraphRepository.getInstance(application);
        init();
    }

    private void init() {
        graphs = Transformations.map(repository.getGraphs(), list -> {
            StringBuilder sb = new StringBuilder();
            for (Graph graph : list) {
                if (graph.isShow()) {
                    sb.append(graph.getFunction()).append("\n");
                }
            }
            return sb.toString();
        });
    }

    public LiveData<String> getGraphs() {
        return graphs;
    }


    public ObservableField<String> getFunctionList() {
        return functionList;
    }


    public void setFunctionList(String functions) {
        functionList.set(functions);
    }
}
