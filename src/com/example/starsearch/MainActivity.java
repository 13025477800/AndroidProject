package com.example.starsearch;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText edittext_name; // 输入姓名的EditText控件
	DatePicker datepicker_bir; // 选择日期的控件
	Button btn_search; // 查询按钮控件
	ImageView imageview_star; // 星座图片控件
	TextView textview_info; // 星座信息控件

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edittext_name = (EditText) this.findViewById(R.id.editTextName);
		datepicker_bir = (DatePicker) this.findViewById(R.id.datePickerBir);
		btn_search = (Button) this.findViewById(R.id.buttonSearch);
		imageview_star = (ImageView) this.findViewById(R.id.imageViewStar);
		textview_info = (TextView) this.findViewById(R.id.textViewInfo);

		btn_search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int month = datepicker_bir.getMonth(); // 获取当前选择的月份
				int day = datepicker_bir.getDayOfMonth(); // 获取当前选择的日
				// 调用searchStar，根据月日获取相应的星座索引（0~11：水瓶座~摩羯座）
				int index = searchStar(month, day);

				int[] infoarray = { R.string.aquarius, R.string.pisces, R.string.aries, R.string.taurus,
						R.string.gemini, R.string.cancer, R.string.leo, R.string.virgo, R.string.libra,
						R.string.scorpio, R.string.sagittarius, R.string.capricornus };
				int[] imagarray = { R.drawable.aquarius, R.drawable.pisces, R.drawable.aries, R.drawable.taurus,
						R.drawable.gemini, R.drawable.cancer, R.drawable.leo, R.drawable.virgo, R.drawable.libra,
						R.drawable.scorpio, R.drawable.sagittarius, R.drawable.capricornus };

				// 根据索引获取星座信息字符串
				String star = MainActivity.this.getString(infoarray[index]);
				// 设定星座信息控件
				textview_info.setText(edittext_name.getText().toString() + ",你的星座信息如下:\r\n" + star);
				// 根据索引设定星座图片
				imageview_star.setImageResource(imagarray[index]);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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

	// 根据月日获取所在星座的索引，0~11分别代表12星座，-1代表参数异常
	// 参数：month月份取值范围为0~11，代表1~12月
	// 参数：day日期取值范围为1~31
	public int searchStar(int month, int day) {
		int[] DayArr = { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 }; // 两个星座分割日
		int index = month;

		// 所查询日期在分割日之前，索引减1，否则不变
		if (day < DayArr[month]) {
			index = index - 1;
			if (index < 0) {
				index = 11;
			}
		}
		return index;
	}

}
