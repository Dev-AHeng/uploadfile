package xiaoheng.huluwashilou_uploadfile;

import android.app.*;
// import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.io.*;
import okhttp3.*;

public class MainActivity extends Activity 
{
	private Runnable r1;
	private OkHttpClient client;
	private MultipartBody.Builder builder;
	private MultipartBody build;
	private Request bi;
	private Call call;
	private Thread t1;

	private File file, file2;
	private String TAG = "message信息";
	private String suffix;

	private EditText ext1;
	private TextView txv1;
	private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		ext1 = (EditText)findViewById(R.id.mainEditText1);
		txv1 = (TextView)findViewById(R.id.mainTextView1);
		btn1 = (Button)findViewById(R.id.mainButton1);

    	r1 = new Runnable() 
        {
            public void run() 
            {
				client = new OkHttpClient();

				/*
				 //这里方便演示。读取drawable里的图片。
				 Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);

				 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

				 bm.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
				 */
				try
				{
					suffix = ext1.getText().toString().substring(ext1.getText().toString().lastIndexOf("."));
				}
				catch (Exception e)
				{
				}


				builder = new MultipartBody.Builder()
					.setType(MultipartBody.FORM)
					// .addFormDataPart("img_1", "0.jpg", RequestBody.create(MediaType.parse("image/jpeg"), file))
					.addFormDataPart("img_2", "xiaoheng" + suffix, RequestBody.create(MediaType.parse("image/jpeg"), file));
				//有多个图片就用for循环添加即可
				build = builder.build();

				bi = new Request.Builder()
					.url("http://212.64.122.76/uploadfile/uploadfile.php") 
					.post(build)
					.build();

				call = client.newCall(bi);
				call.enqueue(new Callback()
					{
						@Override
						public void onFailure(Call call, final IOException e)
						{
							Log.i(TAG, "状态: 失败");
							runOnUiThread(new Runnable()
								{
									@Override
									public void run()
									{
										// TODO: Implement this method
										txv1.setText("失败：" + e);
										ext1.setVisibility(View.VISIBLE);
										btn1.setVisibility(View.VISIBLE);
									}
								});

						}

						@Override
						public void onResponse(Call call, Response response) throws IOException
						{
							final String responseStr = response.body().string();

							Log.i(TAG, "————————————返回信息: " + responseStr + "返回信息————————————");

							runOnUiThread(new Runnable()
								{
									@Override
									public void run()
									{
										ext1.setVisibility(View.VISIBLE);
										btn1.setVisibility(View.VISIBLE);
										Toast.makeText(MainActivity.this, responseStr, Toast.LENGTH_SHORT).show();
										txv1.setText(responseStr);
									}
								});

						}
					});
			}
		};

	}

	public void upload(View v)
	{
		String ss = ext1.getText().toString();
		file = new File(ss);
		if (ext1.getText().toString().length() <= 0)
		{
			txv1.setText("请输入本地文件路径");
		}
		else if (!file.exists())
		{
			txv1.setText("文件不存在");
		}
		else
		{
			txv1.setText("正在上传……");
			ext1.setVisibility(View.INVISIBLE);
			v.setVisibility(View.INVISIBLE);
			t1 = new Thread(r1);
			t1.start();
		}

	}

}
