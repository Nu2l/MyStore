package com.example.sin.projectone;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

public class MainActivity {
//        extends AppCompatActivity
//        implements NavigationView.OnNavigationItemSelectedListener {

//    private Button btn_Pay, btn_TakePic;
//    private ImageView imgMain;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        // set on click btn//
//        btn_Pay = (Button) findViewById(R.id.btn_pay);
//        btn_TakePic = (Button)findViewById(R.id.btn_take_cam);
//        btn_TakePic.setOnClickListener(takePic());
//        btn_Pay.setOnClickListener(startPayment());
//        //
//        imgMain = (ImageView)findViewById(R.id.imgMain);
//        init();
//    }
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
////        if (id == R.id.nav_camera) {
////            // Handle the camera action
////        } else if (id == R.id.nav_gallery) {
////
////        } else if (id == R.id.nav_slideshow) {
////
////        } else if (id == R.id.nav_manage) {
////
////        } else if (id == R.id.nav_share) {
////
////        } else if (id == R.id.nav_send) {
////
////        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void init(){
//        this.deleteDatabase(ProductDBHelper.DATABASE_NAME); // debug
//        WebService.getAllProduct(new JsonHttpResponseHandler(){
//              @Override
//              public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                  try {
//                      if(response.length()>0){
//                          System.out.println(response);
//                          //System.out.println(response.getJSONArray("Product"));
//                          ProductDBHelper.getInstance(MainActivity.this.getApplicationContext()).LoadProduct(response.getJSONArray("Products"));
//                      }
//                      else if(response.length()==0){
//                          System.out.println("Empty");
//                      }
//                      System.out.println("finish");
//
//                  } catch (Exception e) {
//                      e.printStackTrace();
//                  }
//              }
//          });
//
//    }
//
//    private View.OnClickListener startPayment() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(v.getContext(), ScanPayment.class);
//                startActivity(i);
//
//            }
//        };
//    }
//
//    private View.OnClickListener takePic() {
//        return new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dispatchTakePictureIntent();
//            }
//        };
//    }
//
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, Constant.REQUEST_IMAGE_CAPTURE);
//        }
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == Constant.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            String path = saveToInternalStorage(imageBitmap,"1010.png");
//            System.out.println("Result :"+path);
//            imgMain.setImageBitmap(loadImageFromStorage("test.png"));
//        }
//    }
//
//    private String saveToInternalStorage(Bitmap bitmapImage,String imgName){
//        ContextWrapper cw = new ContextWrapper(getApplicationContext());
//        // path to /data/data/yourapp/app_data/imageDir
//        File directory = cw.getDir(Constant.FOLDER_PHOTO, Context.MODE_PRIVATE);
//        // Create imageDir
//        File mypath=new File(directory,imgName);
//
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(mypath);
//            // Use the compress method on the BitMap object to write image to the OutputStream
//            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return directory.getAbsolutePath();
//    }
//
//    private Bitmap loadImageFromStorage(String imgName)
//    {
//        String path = getApplicationInfo().dataDir+"/app_"+Constant.FOLDER_PHOTO;
//        try {
//            File f=new File(path, imgName);
//            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            return b;
//        }
//        catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
}
