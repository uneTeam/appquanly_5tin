package com.example.quan_ly_don_hang;

import java.util.ArrayList;

import com.example.quan_ly_don_hang.adapter.SanPhamAdapter;
import com.example.quan_ly_don_hang.data.DBManager;
import com.example.quan_ly_don_hang.model.SanPham;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class TaoDonHangActivity extends Activity {
	ArrayList<SanPham> arraySP = new ArrayList<SanPham>();
	SanPhamAdapter adapter;
	Cursor cursor;
	DBManager dbmanager;
	TextView txttest;
	ListView lv;
	Integer tongtien = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tao_don_hang);
		txttest = (TextView)findViewById(R.id.txtTest);
		lv = (ListView)findViewById(R.id.lv_chonsp);
		dbmanager = new DBManager(this);
		display();
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView textview = (TextView)arg1.findViewById(R.id.lv_GiaSP);
				int text = Integer.parseInt(textview.getText().toString());
				tongtien = tongtien + text;				
				txttest.setText(String.valueOf(tongtien));
			}
		});
		
	}
	public void display(){
		cursor = dbmanager.getAllSanPham();
		while (cursor.moveToNext()) {
			arraySP.add(new SanPham(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getInt(4), cursor.getBlob(5)));
		}
		adapter = new SanPhamAdapter(this, R.layout.item_list_sanpham, arraySP);
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tao_don_hang, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
