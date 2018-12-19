package desuev.ru.sberlesson6;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import desuev.ru.sberlesson6.API.CallBack;

public class FirstFragment extends Fragment implements CallBack {

    private TextInputEditText textEditForRandomData;
    private BroadcastReceiverForLesson receiver;
    private IntentFilter filter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        receiver = new BroadcastReceiverForLesson(this);
        filter = new IntentFilter("desuev.ru.sberlesson6.FILTER");
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, filter, null, null);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textEditForRandomData = view.findViewById(R.id.editTextInFirstFragment);
    }

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    /**
     *    Callback функция, принимающая в аргументах строку, сгенерированную сервисом.
     *    Обновляет TextEdit в данном фрагменте (FirstFragment) и передаёт значение этой View в MainActivity
     *    @param data строка, генерируемая сервисом (получается через BroadcastReceiver)
     */
    @Override
    public void update(String data) {
        textEditForRandomData.setText(data);
        ((MainActivity)getActivity()).setDataToSecondFragment(textEditForRandomData.getText().toString());
    }
}
