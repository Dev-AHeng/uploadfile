package xiaoheng.huluwashilou_uploadfile.util;

import android.content.*;
import android.widget.*;
import java.text.*;
import java.util.*;
import xiaoheng.huluwashilou_uploadfile.application.*;

/**
 * 个人工具类
 *
 * 封装于：2019.4.6
 * 作者：小亨
 */

public class BaseUtil
{
	/**
	 * Toast提示
	 * 
	 * s 提示内容
	 * i 提示时长
	 */
	public static void toast(String s, Integer i)
	{
		Toast.makeText(MyApplication.getAppContext(), s, i).show();
	}

	/**
	 * 打印
	 * 
	 * s 打印内容
	 */
	public static void print(String s)
	{

		// 设置日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
		System.out.println("Start******" + sdf.format(new Date()) + "\n" + s + "\nEnd********************\n");
	}

	/**
	 * 分享文本
	 * 
	 * s 分享text
	 */
	// startActivity();
	public static Intent share(String s)
	{
		Intent shareIntent = new Intent();
		shareIntent.setAction(Intent.ACTION_SEND);
		shareIntent.putExtra(Intent.EXTRA_TEXT, s);
		shareIntent.setType("text/plain");
		Intent.createChooser(shareIntent, "分享到");
		return shareIntent;
	}
}
