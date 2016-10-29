package com.airbnb.jiafang_jiang.nytimessearch.filter;

import android.app.DatePickerDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.airbnb.jiafang_jiang.nytimessearch.R;
import com.airbnb.jiafang_jiang.nytimessearch.search.SearchActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import icepick.State;

public class EditFilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static EditFilterDialogFragment newInstance() {
        return new EditFilterDialogFragment();
    }

    @BindView(R.id.et_date) EditText etDateEditer;
    @BindView(R.id.spinner) Spinner spinner;
    @BindView(R.id.checkbox_art) CheckBox cbArt;
    @BindView(R.id.checkbox_fashion) CheckBox cbFashion;
    @BindView(R.id.checkbox_sports) CheckBox cbSports;

    @State String sortOrder;
    @State String beginDate;
    @State boolean cbArtChecked;
    @State boolean cbFashionChecked;
    @State boolean cbSportsChecked;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_filter, container);
        ButterKnife.bind(this, view);

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        etDateEditer.setOnClickListener(v -> {
            new DatePickerDialog(getActivity(), this, year, month, day).show();
        });
        etDateEditer.setCursorVisible(false);

        cbArt.setOnClickListener(v -> cbArtChecked = true);
        cbSports.setOnClickListener(v -> cbSportsChecked = true);
        cbFashion.setOnClickListener(v -> cbFashionChecked = true);

        return view;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        beginDate = new StringBuilder().append(year).append(month < 10 ? "0"+ month : month).append(dayOfMonth < 10? "0" + dayOfMonth : dayOfMonth).toString();
        etDateEditer.setText(new StringBuilder().append(month).append("/").append(dayOfMonth)
                .append("/").append(year));
    }

    @Override
    public void onResume() {
        // Store access variables for window and blank point
        Window window = getDialog().getWindow();
        Point size = new Point();
        // Store dimensions of the screen in `size`
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        // Set the width of the dialog proportional to 75% of the screen width
        window.setLayout((int) (size.x * 0.80), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        // Call super onResume after sizing
        super.onResume();
    }

    @OnClick(R.id.btn_save)
    public void onClickSaveButton() {
        String filter = new StringBuilder()
                .append("news_desk:(")
                .append(cbArtChecked ? "\"" + cbArt.getText() + "\" " : "")
                .append(cbSportsChecked ? "\"" + cbSports.getText() + "\" " : "")
                .append(cbFashionChecked ? "\"" + cbFashion.getText() + "\" " : "")
                .append(")")
                .toString();
        Log.d("[filter]", filter + " " + spinner.getSelectedItem().toString() + " " + beginDate);
        ((SearchActivity) getActivity()).updateQueryAndSend(filter, spinner.getSelectedItem().toString(), beginDate);
        dismiss();
    }
}
