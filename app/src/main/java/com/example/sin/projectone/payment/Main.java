package com.example.sin.projectone.payment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sin.projectone.ApplicationHelper;
import com.example.sin.projectone.Constant;
import com.example.sin.projectone.Product;
import com.example.sin.projectone.ProductAdapter;
import com.example.sin.projectone.R;
import com.example.sin.projectone.SwipeDetector;

import java.util.ArrayList;

/**
 * Created by nanth on 12/8/2016.
 */

public class Main extends Fragment  {
    Button _btn_next, _btn_back;
    private ListView _productList;
    public ArrayList<Product> products = new ArrayList<Product>();
    private ProductAdapter adapter;
    private SwipeDetector swipeDetector = new SwipeDetector();
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_payment_main, container, false);
        Fragment newFragment = new ScanPayment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack if needed
        transaction.replace(R.id.frame_container_scanner, newFragment);
        //transaction.addToBackStack();
        // Commit the transaction
        transaction.commit();
        _btn_next = (Button) view.findViewById(R.id.pay_next);
        _btn_back = (Button) view.findViewById(R.id.pay_back);
        _btn_next.setOnClickListener(nextBtnClick());
        _btn_back.setOnClickListener(backBtnClick());
        // set list view
        _productList = (ListView)view.findViewById(R.id.product_list);
        int a = R.layout.list_item_endpayment;
        int b = R.layout.list_item;
        adapter = new ProductAdapter(ApplicationHelper.getAppContext(),products, R.layout.list_item_endpayment);
        _productList.setAdapter(adapter);
        _productList.setOnTouchListener(swipeDetector);
        _productList.setOnItemClickListener(onItemClickListener());
        return view;
    }
    private View.OnClickListener nextBtnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!products.isEmpty() && products.size()>0){
                    Bundle product_bundle = new Bundle();
                    product_bundle.putParcelableArrayList(Constant.KEY_BUNDLE_ARRAYLIST_PRODUCT, products);
                    Fragment endPayment = new EndPayment();
                    endPayment.setArguments(product_bundle);
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container_payment, endPayment, Constant.TAG_FRAGMENT_PAYMENT_END);
                    //transaction.addToBackStack(null);
                    transaction.commit();
                }
                else{
                    Toast.makeText(Main.this.getActivity().getApplicationContext(), "please add products first",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private View.OnClickListener backBtnClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new EndPayment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container_scanner, newFragment);
                //transaction.addToBackStack(null);
                transaction.commit();

            }
        };
    }

    private AdapterView.OnItemClickListener onItemClickListener(){
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(swipeDetector.swipeDetected()){
                    if(swipeDetector.getAction()== SwipeDetector.Action.LR){
                        adapter.remove(adapter.getItem(position));
                    }
                }
                else{
                    adapter.addQtyProduct(adapter.getItem(position).id,1);
                }
                adapter.notifyDataSetChanged();
            }
        };
    }

    public int addProductPayment(Product product){
        if(!adapter.addQtyProduct(product.id,1)){
            adapter.add(product);
        }
        adapter.notifyDataSetChanged();
        return Integer.parseInt(product.id);
    }
}
