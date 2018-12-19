package desuev.ru.sberlesson6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import desuev.ru.sberlesson6.API.CallBack;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        initFragments();
    }

    private void initService(){
        startService(RandomData.newIntent(MainActivity.this));
    }

    private void initFragments(){
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.firstFragment, FirstFragment.newInstance())
                .add(R.id.secondFragment, SecondFragment.newInstance()).commitNow();
    }

/**
*    Функция передачи данных из FirstFragment в SecondFragment.
*    FirstFragment получает данные из BroadcastReceiver'а через CallBack-функцию,
*    в которой происходит вызов данного метода.
*
*    @param data данные, передаваеммые FirstFragment'ом
*/
    public void setDataToSecondFragment(String data){
        FragmentManager manager = getSupportFragmentManager();
        SecondFragment fragment = (SecondFragment) manager.findFragmentById(R.id.secondFragment);
        fragment.setText(data);
    }

}

