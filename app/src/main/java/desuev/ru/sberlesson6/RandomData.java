package desuev.ru.sberlesson6;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

public class RandomData extends IntentService {

    public static final String DATA_FILTER = "RANDOMDATA";

    public RandomData() {
        super(RandomData.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int kek = 0;
        int sign = -1;
        while (true){
            Intent internalIntent = new Intent("desuev.ru.sberlesson6.FILTER");
            if(kek > 99){
                sign = -1;
            }else if(kek < 1){
                sign = 1;
            }
            kek += sign;
            internalIntent.putExtra(DATA_FILTER, String.valueOf(kek));
            sendBroadcast(internalIntent);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static Intent newIntent(Context context){
        return new Intent(context, RandomData.class);
    }
}

