package com.hassan.qurandemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView txt = findViewById(R.id.txtRead);


        Long id = getIntent().getExtras().getLong("id");
        Surah surah = ((App) getApplication()).getDaoSession().getSurahDao().load(id);

        List<Ayah> ayahs = surah.getAyahs();

        setTitle(surah.getSurahName());

        StringBuilder stringBuilder = new StringBuilder();
        for (Ayah a : ayahs) {
            stringBuilder.append(a.getAyahText());
            stringBuilder.append(" (" + a.getNumberInSurah() + ") ");
        }

        txt.setText(stringBuilder.toString());
    }
}
