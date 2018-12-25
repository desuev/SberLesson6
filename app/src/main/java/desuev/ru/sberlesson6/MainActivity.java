package desuev.ru.sberlesson6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import desuev.ru.sberlesson6.API.CallBack;
import desuev.ru.sberlesson6.FirstFragment.TextUpdateInteface;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TextUpdateInteface{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        initListeners();
//        initFragments();
    }

    private void initService() {
        startService(RandomData.newIntent(MainActivity.this));
    }

    private void initFragments() {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .add(R.id.firstFragment, FirstFragment.newInstance())
                .add(R.id.secondFragment, SecondFragment.newInstance()).commitNow();
    }

    private void initListeners(){
        FragmentManager manager = getSupportFragmentManager();
        FirstFragment fragment = (FirstFragment) manager.findFragmentById(R.id.firstFragment);
        fragment.addListener(this);
    }

    /**
     * Функция передачи данных из FirstFragment в SecondFragment.
     * FirstFragment получает данные из BroadcastReceiver'а через CallBack-функцию,
     * в которой происходит вызов данного метода.
     *
     * @param text данные, передаваеммые FirstFragment'ом
     */
    @Override
    public void onTextUpdate(String text) {
        FragmentManager manager = getSupportFragmentManager();
        SecondFragment fragment = (SecondFragment) manager.findFragmentById(R.id.secondFragment);
        fragment.setText(text);
    }
}

