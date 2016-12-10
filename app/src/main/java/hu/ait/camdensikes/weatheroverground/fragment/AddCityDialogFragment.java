package hu.ait.camdensikes.weatheroverground.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import hu.ait.camdensikes.weatheroverground.R;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public class AddCityDialogFragment extends DialogFragment {

    public interface AddCityDialogListener {
        public void onDialogPositiveClick(String city);
    }

    // Use this instance of the interface to deliver action events
    AddCityDialogListener dListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof AddCityDialogListener) {
            dListener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(
                    "This Activity is not implementing the " +
                            "OnMessageFragmentAnswer interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.dialog_add_city, null);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton(getString(R.string.add), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        final EditText etCity = (EditText) view.findViewById(R.id.etCity);
                        dListener.onDialogPositiveClick(etCity.getText().toString());
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                });
        return builder.create();

    }
}
