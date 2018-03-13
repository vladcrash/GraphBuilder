package da.homework.my.graphbuilder.graphs;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import java.util.List;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.data.model.Graph;
import da.homework.my.graphbuilder.databinding.ItemGraphListBinding;


public class GraphListAdapter extends RecyclerView.Adapter<GraphListAdapter.GraphViewHolder> {

    private List<Graph> graphs;
    private GraphListViewModel viewModel;

    public GraphListAdapter() {
    }

    @Override
    public GraphViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGraphListBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_graph_list, parent, false);
        return new GraphViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(GraphViewHolder holder, int position) {
        holder.bind(graphs.get(position), viewModel, position);
    }

    @Override
    public int getItemCount() {
        return graphs != null ? graphs.size() : 0;
    }

    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
        notifyDataSetChanged();
    }

    public void setViewModel(GraphListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public class GraphViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener{

        private ItemGraphListBinding binding;

        public GraphViewHolder(ItemGraphListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        private void bind(Graph graph, GraphListViewModel viewModel, int position) {
            binding.setGraph(graph);
            binding.setViewModel(viewModel);
            binding.setPosition(position);
            binding.executePendingBindings();
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    }
}
