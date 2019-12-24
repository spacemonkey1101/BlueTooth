package com.example.bluetooth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA ; //creating a bluetooth adapter for using bluetooth


    public void turnBluetoothOff(View view)
    {
        BA.disable();
        if (BA.isEnabled())
        {
            Toast.makeText(this, "Bluetooth could not be disabled", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
        }
    }

    public void discover(View view)
    {
        Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);//android OS automatically sees the discoverable device
        startActivity(i);
    }

    public void pairedDevices(View view)
    {
        //A Set is like a list but has some differences
        Set<BluetoothDevice> pairedDevice = BA.getBondedDevices();

        ListView listView =findViewById(R.id.listView);
        ArrayList pairedDeviesArrayList = new ArrayList(); //converting a set into array list
        for (BluetoothDevice bluetoothDevice: pairedDevice){
            //get name of each of the dvice and add it to the array list
            pairedDeviesArrayList.add(bluetoothDevice.getName());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, pairedDeviesArrayList);
        listView.setAdapter(arrayAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(BA.isEnabled()) { //if bluetooth is turned on
            Toast.makeText(this, " bluetooth is on!!!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // request to enable bluetooth
            startActivity(i);

            if(BA.isEnabled())
            {
                Toast.makeText(this, " bluetooth is now on!!!", Toast.LENGTH_SHORT).show();

            }
        }
    }
}
