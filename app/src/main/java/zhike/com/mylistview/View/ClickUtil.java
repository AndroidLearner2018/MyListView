package zhike.com.mylistview.View;

/**
 * Created by Administrator on 2016/12/1.
 */


public class ClickUtil {
    public static boolean processFlag = true;

    public static void setProcessFlag() {
        processFlag = false;
    }

    public static void getTime() {
        new TimeThread().start();
    }
    /**
     * 计时线程（防止在一定时间段内重复点击按钮）
     */
    private static class TimeThread extends Thread {
        public void run() {
            try {
                sleep(500); //1000
                processFlag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //1秒计时防双击
    public static void getMoreTime() {
        new MoreTimeThread().start();
    }
    private static class MoreTimeThread extends Thread {
        public void run() {
            try {
                sleep(1000); //1000
                processFlag = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}