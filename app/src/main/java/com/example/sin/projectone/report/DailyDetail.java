package com.example.sin.projectone.report;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sin.projectone.ProductDBHelper;
import com.example.sin.projectone.R;
import com.example.sin.projectone.receipt.TransDetailListCursor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by naki_ on 11/25/2016.
 */

public class DailyDetail extends Fragment {
    private Button dailyReport;
    private Button topSell;
    Calendar myCalendar;
    EditText dateEdit;
    TextInputLayout layoutText;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_report_daily_detail, container, false);
        String createAt = getArguments().getString("createAt");
        ListView listProduct = (ListView)view.findViewById(R.id.report_daily_list_product);
        ListView listEmployee = (ListView)view.findViewById(R.id.report_daily_list_employee);
        Cursor todoCursor = ProductDBHelper.getInstance(this.getActivity()).getDailyEmployee(createAt);
        Cursor todoCursor2 = ProductDBHelper.getInstance(this.getActivity()).getDailyProduct(createAt);
        TransDetailListCursor todoAdapter = new TransDetailListCursor(this.getActivity(), todoCursor);
        listProduct.setAdapter(todoAdapter);
        listProduct.setDivider(null);
        return view;
    }

}
