package name.chakimar.singletasktest;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	/**
	 * ユーザーが自分のアプリからどこかにいったかを判断するヒントを教えてくれる。
	 * 自分の持つ別アクティビティを起動した時も呼ばれてしまうので注意。
	 * 電話の着信のように、ユーザーが意図していないアプリの切替には反応しない。
	 * HOMEキー押下時も呼ばれる。
	 */
	@Override
	protected void onUserLeaveHint() {
		if (!isMe()) {
			showToast("Good Bye !");
			finish();
		} else {
			showToast("わたしです　(＾o＾)");
		}
	}
	
	/**
	 * トップにあるアプリが自分であるか確認
	 * @return
	 */
	private boolean isMe() {
		String topPackageName = getTopPackageName();
		if (!this.getPackageName().equals(topPackageName)) {
			return false;
		}
		return true;
	}

	/**
	 * トップにあるアプリのパッケージ名を取得。
	 * パーミッション"android.permission.GET_TASKS"が必要
	 * @return
	 */
	private String getTopPackageName() {
		ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
		ActivityManager.RunningTaskInfo topTask = list.get(0);
		ComponentName topActivity = topTask.topActivity;
		String topPackageName = topActivity.getPackageName();
		return topPackageName;
	}
	
	protected void showToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
}
