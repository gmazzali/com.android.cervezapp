package com.android.cervezapp.view.field;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.cervezapp.business.util.Constants;

/**
 * @author Billy
 */
public class FechaPickerFragment extends DialogFragment implements OnDateSetListener {

	private EditText dateFieldText;

	public FechaPickerFragment(EditText dateFieldText) {
		this.dateFieldText = dateFieldText;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		Calendar calendario = Calendar.getInstance();
		try {
			calendario.setTime(new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).parse(this.dateFieldText.getText().toString()));
		} catch (Exception e) {
		}
		int anio = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(this.getActivity(), this, anio, mes, dia);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(year, monthOfYear, dayOfMonth);
		this.dateFieldText.setText(new SimpleDateFormat(Constants.DATE_FORMAT, Locale.getDefault()).format(calendario.getTime()));
	}
}