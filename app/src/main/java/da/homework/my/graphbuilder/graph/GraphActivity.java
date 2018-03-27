package da.homework.my.graphbuilder.graph;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.databinding.ActivityGraphBinding;
import da.homework.my.graphbuilder.graphs.GraphListActivity;

public class GraphActivity extends AppCompatActivity {

    private static final String TAG = GraphActivity.class.getSimpleName();

    private ActivityGraphBinding binding;
    private GraphViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_graph);
        viewModel = ViewModelProviders.of(this).get(GraphViewModel.class);
        binding.setViewModel(viewModel);

        subscribeToModel();

        settingsChart();
        binding.chart.invalidate();
    }

    private void settingsChart() {
        binding.chart.setData(viewModel.getLineData());
        binding.chart.setDescription(null);
        binding.chart.getAxisLeft().setTextSize(12);
        binding.chart.getAxisRight().setTextSize(12);
        binding.chart.getXAxis().setTextSize(12);
        binding.chart.getLegend().setEnabled(false);
    }

    private void subscribeToModel() {
        viewModel.getGraphs().observe(this, list -> {
            binding.chart.setData(viewModel.getLineData());
            binding.chart.invalidate();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_graph, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.open) {
            Intent intent = new Intent(this, GraphListActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }
}
