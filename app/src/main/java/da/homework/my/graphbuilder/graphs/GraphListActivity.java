package da.homework.my.graphbuilder.graphs;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.addeditgraph.AddEditGraphActivity;
import da.homework.my.graphbuilder.databinding.ActivityGraphListBinding;


public class GraphListActivity extends AppCompatActivity {
    public static final String EDIT_GRAPH_ID = "EDIT_GRAPH_ID";

    private GraphListViewModel viewModel;
    private GraphListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        ActivityGraphListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_graph_list);
        viewModel = ViewModelProviders.of(this).get(GraphListViewModel.class);
        adapter = new GraphListAdapter(viewModel);
        binding.setViewModel(viewModel);
        binding.setAddNewGraphCallBack(addNewGraphCallBack);
        binding.graphsList.setAdapter(adapter);
        adapter.setEditGraphCallback(editGraphCallBack);

        subscribeToModel();
    }

    private void subscribeToModel() {
        viewModel.getGraphs().observe(this, adapter::setGraphs);
    }

    private OnClickListener addNewGraphCallBack = view -> {
        Intent intent = new Intent(GraphListActivity.this, AddEditGraphActivity.class);
        startActivity(intent);
    };

    private GraphListAdapter.EditGraphCallBack editGraphCallBack = position -> {
        Intent intent = new Intent(GraphListActivity.this, AddEditGraphActivity.class);
        intent.putExtra(EDIT_GRAPH_ID, position);
        startActivity(intent);
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
