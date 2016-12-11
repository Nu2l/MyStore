package com.example.sin.projectone.receipt;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.sin.projectone.ProductDBHelper;
import com.example.sin.projectone.R;

/**
 * Created by naki_ on 11/25/2016.
 */

public class list_fragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_receipt_list_fragment, container, false);
//        ListView lvItems = (ListView) view.findViewById(R.id.list);
        Cursor todoCursor = ProductDBHelper.getInstance(this.getActivity()).getTransaction();
        System.out.println(todoCursor.getColumnName(0));
//        System.out.println(transList);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.Planets, android.R.layout.simple_list_item_1);
        TransListCursor todoAdapter = new TransListCursor(this.getActivity(), todoCursor);
//        lvItems.setAdapter(todoAdapter);
//        getListView().setOnItemClickListener(this);
        return view;

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Cursor todoCursor = ProductDBHelper.getInstance(this.getActivity()).getTransaction();
//        System.out.println(transList);
//        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
//                R.array.Planets, android.R.layout.simple_list_item_1);
        TransListCursor todoAdapter = new TransListCursor(this.getActivity(), todoCursor);
        setListAdapter(todoAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
        Cursor cursor = (Cursor) parent.getItemAtPosition(position);
        String strTrans = cursor.getString(cursor.getColumnIndex("transactionID"));
        String strUser = cursor.getString(cursor.getColumnIndex("username"));
        String strTotal = String.valueOf(cursor.getDouble(cursor.getColumnIndex("total")));
        String strDisc = String.valueOf(cursor.getDouble(cursor.getColumnIndex("discount")));
        String strCreate = cursor.getString(cursor.getColumnIndex("createAt"));
        Log.d("listView", strTrans);
        Log.d("listView", strUser);
        Log.d("listView", strTotal);
        Log.d("listView", strDisc);
        Log.d("listView", strCreate);
        DetailList transDetail = new DetailList ();
        Bundle args = new Bundle();
        args.putString("transactionID", strTrans);
        args.putString("username", strUser);
        args.putString("total", strTotal);
        args.putString("discount", strDisc);
        args.putString("create", strCreate);
        transDetail.setArguments(args);
        getFragmentManager().beginTransaction().replace(R.id.fragment_receipt_container, transDetail).commit();
    }
}
