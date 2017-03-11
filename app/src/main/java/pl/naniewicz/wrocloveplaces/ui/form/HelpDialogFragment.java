package pl.naniewicz.wrocloveplaces.ui.form;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import pl.naniewicz.wrocloveplaces.R;

public class HelpDialogFragment extends DialogFragment {

    public static final String TAG = HelpDialogFragment.class.getSimpleName();

    public static HelpDialogFragment newInstance() {
        return new HelpDialogFragment();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.help)
                .setMessage(R.string.msg_login_info)
                .setPositiveButton(R.string.ok, null)
                .create();
    }
}