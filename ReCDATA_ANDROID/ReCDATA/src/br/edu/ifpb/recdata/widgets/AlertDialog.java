package br.edu.ifpb.recdata.widgets;

import android.app.Activity;

public abstract class AlertDialog {

        private Activity activity;

        public AlertDialog(Activity activity) {
                this.activity = activity;
        }

        public abstract void showAlertDialog();
        
        public Activity getActivity() {
                return activity;
        }

        public void setActivity(Activity activity) {
                this.activity = activity;
        }
}
