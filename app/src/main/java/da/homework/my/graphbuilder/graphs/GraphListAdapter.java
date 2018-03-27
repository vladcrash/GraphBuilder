package da.homework.my.graphbuilder.graphs;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import java.util.List;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.data.model.Graph;
import da.homework.my.graphbuilder.databinding.ItemGraphListBinding;


public class GraphListAdapter extends RecyclerView.Adapter<GraphListAdapter.GraphViewHolder> {

    private List<Graph> graphs;
    private GraphListViewModel viewModel;
    private EditGraphCallBack editGraphCallback;

    public GraphListAdapter(GraphListViewModel viewModel) {
        this.viewModel = viewModel;
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
        holder.bind(graphs.get(position));
    }

    @Override
    public int getItemCount() {
        return graphs != null ? graphs.size() : 0;
    }

    public void setGraphs(List<Graph> graphs) {
        this.graphs = graphs;
        notifyDataSetChanged();
    }

    public void setEditGraphCallback(EditGraphCallBack editGraphCallback) {
        this.editGraphCallback = editGraphCallback;
    }

    public interface EditGraphCallBack {
        void editTask(int position);
    }

    public class GraphViewHolder extends RecyclerView.ViewHolder implements OnClickListener {

        private ItemGraphListBinding binding;

        public GraphViewHolder(ItemGraphListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);

        }

        private void bind(Graph graph) {
            binding.setGraph(graph);
            binding.setListener(listener);
            binding.executePendingBindings();
        }

        private OnClickListener listener = view -> {
            viewModel.updateGraph(view, getAdapterPosition());
        };

        @Override
        public void onClick(View v) {
            editGraphCallback.editTask(getAdapterPosition());
        }
    }
}
