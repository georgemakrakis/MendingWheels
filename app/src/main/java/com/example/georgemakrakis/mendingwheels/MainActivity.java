package com.example.georgemakrakis.mendingwheels;

import android.net.Uri;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Init the Accessories Spinner
        Spinner spinner = (Spinner) findViewById(R.id.accessories_spinner);

        List<String> accessories = new ArrayList<>();

        accessories.add("Select an accessory");
        accessories.add("Chain");
        accessories.add("Freehub Body");
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accessories);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Init the video player
        final VideoView videoview = (VideoView) findViewById(R.id.videoView);

        // Make an action when the user selects an item
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                if(selectedItem.equals("Select an accessory"))
                {
                    // Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.chain_rear_derailleur);
                    Button playstop = (Button) findViewById(R.id.playstop);
                    playstop.setText("Play");
                    videoview.setVisibility(View.GONE);
                    videoview.setVisibility(View.VISIBLE);
                    videoview.setVideoURI(null);

                    List<String> stores = new ArrayList<>();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, stores);

                    //Load them into the ListView
                    ListView listView = (ListView) findViewById(R.id.stores_list);
                    listView.setAdapter(adapter);

                }
                else if(selectedItem.equals("Chain"))
                {
                    Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.chain_rear_derailleur);
                    videoview.setVideoURI(uri);

                    // List with stores that sell Chains
                    List<String> stores = new ArrayList<>();
                    stores.add("Bogiatzoglou");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, stores);

                    //Load them into the ListView
                    ListView listView = (ListView) findViewById(R.id.stores_list);
                    listView.setAdapter(adapter);
                }
                else if(selectedItem.equals("Freehub Body"))
                {
                    Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.remove_freehub_body);
                    videoview.setVideoURI(uri);

                    // List with stores that sell Chains
                    List<String> stores = new ArrayList<>();
                    stores.add("Bogiatzoglou");
                    stores.add("Katsouris Bikes");
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.listview, stores);

                    //Load them into the ListView
                    ListView listView = (ListView) findViewById(R.id.stores_list);
                    listView.setAdapter(adapter);
                }
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });




    }
    // Play/Stop button
    public void playVideo(View view)
    {
        VideoView videoview= (VideoView) findViewById(R.id.videoView);
        Button playstop = (Button) findViewById(R.id.playstop);
//        boolean isPlaying = false;
//
//        if (isPlaying) {
//            videoview.stopPlayback();
//            playstop.setText("Play");
//            isPlaying = !isPlaying;
//        }
//        else
//        {
//            isPlaying = true;
//            videoview.start();
//            playstop.setText("Pause");
//
//        }
        if(videoview.isPlaying())
        {
            videoview.pause();
            playstop.setText("Play");
        }
        else
        {
            videoview.start();
            playstop.setText("Pause");
        }



    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
        }
        else if (id == R.id.nav_stores)
        {

        }
//        else if (id == R.id.nav_slideshow)
//        {
//
//        }
//        else if (id == R.id.nav_manage)
//        {
//
//        }
        else if (id == R.id.nav_share)
        {

        }
        else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
