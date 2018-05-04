package toandoan.com.asyntasksample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by doan.van.toan on 5/4/18.
 */

public class MyAsyntask extends AsyncTask<Void, Integer, Void> {
    Activity contextParent;

    public MyAsyntask(Activity contextParent) {
        this.contextParent = contextParent;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(contextParent, "Start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);
            //Khi gọi hàm này thì onProgressUpdate sẽ thực thi
            publishProgress(i);
        }
        Log.d("A", "doInBackground: ");
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        ProgressBar progressBar = (ProgressBar) contextParent.findViewById(R.id.prb_demo);
        //Vì publishProgress chỉ truyền 1 đối số
        //Nên mảng values chỉ có 1 phần tử
        int number = values[0];
        //Tăng giá trị của ProgressBar lên
        progressBar.setProgress(number);

        //Đồng thời hiện giá trị là % lên TextView
        TextView textView = (TextView) contextParent.findViewById(R.id.tv_status);
        textView.setText(number + "%");
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(contextParent, "Done, Finished!", Toast.LENGTH_SHORT).show();
    }
}
