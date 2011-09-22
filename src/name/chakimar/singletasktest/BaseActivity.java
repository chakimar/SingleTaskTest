package name.chakimar.singletasktest;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.Toast;

public abstract class BaseActivity extends Activity {

	/**
	 * ���[�U�[�������̃A�v������ǂ����ɂ��������𔻒f����q���g�������Ă����B
	 * �����̎��ʃA�N�e�B�r�e�B���N�����������Ă΂�Ă��܂��̂Œ��ӁB
	 * �d�b�̒��M�̂悤�ɁA���[�U�[���Ӑ}���Ă��Ȃ��A�v���̐ؑւɂ͔������Ȃ��B
	 * HOME�L�[���������Ă΂��B
	 */
	@Override
	protected void onUserLeaveHint() {
		if (!isMe()) {
			showToast("Good Bye !");
			finish();
		} else {
			showToast("�킽���ł��@(�Oo�O)");
		}
	}
	
	/**
	 * �g�b�v�ɂ���A�v���������ł��邩�m�F
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
	 * �g�b�v�ɂ���A�v���̃p�b�P�[�W�����擾�B
	 * �p�[�~�b�V����"android.permission.GET_TASKS"���K�v
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
