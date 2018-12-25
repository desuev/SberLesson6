package desuev.ru.sberlesson6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class SecondFragment extends Fragment {

    private Button justBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        justBtn = view.findViewById(R.id.justBtn);
        justBtn.setOnClickListener(l -> {
            FragmentManager manager = getChildFragmentManager();
            manager.beginTransaction().add(R.id.secondFragment, ThirdFragment.newInstance(), "childFragment").commitNow();
        });
    }

    /**
     *    Данный метод вызывается в MainActivity, когда сервис генерирует данные и отправляет их BroadcastReceiver'у.
     *    @param text значение TextEdit'а в FirstFragment'е
     */
    public void setText(String text) {
        FragmentManager manager = getChildFragmentManager();
        ThirdFragment fragment = (ThirdFragment) manager.findFragmentByTag("childFragment");
        if(fragment != null) {
            fragment.setTextFromFirstFragment(text);
        }
    }

    public static SecondFragment newInstance(){
        return new SecondFragment();
    }

}
