package desuev.ru.sberlesson6;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import desuev.ru.sberlesson6.API.CallBack;

public class BroadcastReceiverForLesson extends BroadcastReceiver {

    private CallBack client;

    public BroadcastReceiverForLesson(CallBack callBack){
        client = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
            client.update((intent.getStringExtra(RandomData.DATA_FILTER)));
    }
}
