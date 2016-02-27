package pl.naniewicz.wrocloveplaces.ui.form;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;

import pl.naniewicz.wrocloveplaces.R;

/**
 * Created by Szymon Kozak on 2016-02-27.
 */
public class HelpDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new MaterialDialog.Builder(getActivity())
                .title(R.string.help)
                .content(R.string.msg_login_info)
                .positiveText(R.string.ok)
                .build();
    }
}