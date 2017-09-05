package zhike.com.mylistview.utils;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;


public class ActivityHelper {

    private static Toast t = null;

    public static void showTips(Context con, String text) {

        if (t != null) {
            t.setText(text);
        } else {
            t = Toast.makeText(con, text, Toast.LENGTH_SHORT);
        }
        t.show();

    }

    /*
     * 提示加载
     */
    public static ProgressDialog showProgressDialog(Context context, ProgressDialog progressDialog, Activity activity) {
        String message = "加载中...";
        progressDialog = ProgressDialog.show(activity, null, message, true, true);
        progressDialog.show();

        return progressDialog;
    }

    /*
     * 隐藏提示加载
     */
    public static void hideProgressDialog(ProgressDialog progressDialog) {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }
}
