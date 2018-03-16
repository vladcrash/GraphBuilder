package da.homework.my.graphbuilder.addeditgraph;

import android.app.DialogFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;

import da.homework.my.graphbuilder.R;
import da.homework.my.graphbuilder.databinding.ActivityAddEditGraphBinding;


public class AddEditGraphActivity extends AppCompatActivity {

    private ActivityAddEditGraphBinding binding;
    private AddEditGraphViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_edit_graph);
        viewModel = ViewModelProviders.of(this).get(AddEditGraphViewModel.class);
        binding.setViewModel(viewModel);
        binding.setSaveGraphCallback(saveGraphCallback);
        binding.setColorPickerDialogCallback(colorPickerDialogCallback);
    }

    private OnClickListener saveGraphCallback = view -> {
        viewModel.saveGraph();
        finish();
    };

    private OnClickListener colorPickerDialogCallback = view -> {
        DialogFragment colorPicker = viewModel.getColorPickerDialog();
        colorPicker.show(getFragmentManager(), AddEditGraphViewModel.DIALOG_COLOR_PICKER);
    };

}
