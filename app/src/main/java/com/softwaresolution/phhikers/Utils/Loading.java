package com.softwaresolution.phhikers.Utils;
import android.app.ProgressDialog;
import android.content.Context;

public class Loading {
    public ProgressDialog loadDialog;
    public String messageTitle ="Loading";
    public String message ="Please wait";
    public Loading(Context context) {
        loadDialog =  new ProgressDialog(context);
        loadDialog.setTitle(messageTitle);
        loadDialog.setMessage(message);
        loadDialog.setCancelable(false);
    }

    public ProgressDialog getLoadDialog() {
        return loadDialog;
    }

    public void setLoadDialog(ProgressDialog loadDialog) {
        this.loadDialog = loadDialog;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
