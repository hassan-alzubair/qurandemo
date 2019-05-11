package com.hassan.qurandemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.greendao.query.QueryBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements SuarsRecyclerAdapter.OnSurahClickListener {

    private List<Surah> list = new ArrayList<>();
    private SuarsRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setSupportActionBar((Toolbar) findViewById(R.id.appbar));
        getSupportActionBar().setTitle("سور القرآن");

        RecyclerView recyclerView = findViewById(R.id.recycler);

        SearchView searchView = findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Surah> surahs = ((App) getApplication()).getDaoSession().getSurahDao().queryBuilder().where(SurahDao.Properties.SurahName.like("%" + s + "%")).list();
                fetchFromDatabase(surahs);
                return false;
            }
        });

        adapter = new SuarsRecyclerAdapter(list);
        adapter.setOnSurahClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        boolean firstStart = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("first_start", true);
        if (firstStart) {
            initDatabase();
            PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("first_start", false).apply();
        } else {
            fetchFromDatabase(((App) getApplication()).getDaoSession().getSurahDao().loadAll());
        }
    }

    private void fetchFromDatabase(List<Surah> surahs) {
        list.clear();
        list.addAll(surahs);
        adapter.notifyDataSetChanged();
    }

    private void initDatabase() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("جاري تحضير سور القرآن الكريم");
        progressDialog.setTitle("الرجاء الانتظار");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(114);
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                DaoSession daoSession = ((App) getApplication()).getDaoSession();
                JSONObject object = JsonAssetsUtils.getJsonObjectFromAssets(HomeActivity.this, "quran.json");
                try {
                    JSONArray surahsArray = object.getJSONObject("data").getJSONArray("surahs");
                    for (int i = 0; i < surahsArray.length(); i++) {
                        JSONObject surahObject = surahsArray.getJSONObject(i);
                        Surah surah = new Surah(null, surahObject.getString("name"), surahObject.getString("revelationType"), surahObject.getInt("number"));
                        daoSession.getSurahDao().insert(surah);
                        JSONArray ayatsArray = surahObject.getJSONArray("ayahs");
                        for (int j = 0; j < ayatsArray.length(); j++) {
                            JSONObject ayahObject = ayatsArray.getJSONObject(j);
                            Ayah ayah = new Ayah(null, ayahObject.getInt("number"), ayahObject.getString("audio"), ayahObject.getString("text"), ayahObject.getInt("numberInSurah"), surah.getId());
                            daoSession.getAyahDao().insert(ayah);
                        }

                        final int finalI = i;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.incrementProgressBy(1);
                                if (progressDialog.getProgress() == 114) {
                                    progressDialog.dismiss();
                                    fetchFromDatabase(((App) getApplication()).getDaoSession().getSurahDao().loadAll());
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onClick(Surah surah) {
        Intent intent = new Intent(this, ReadActivity.class);
        intent.putExtra("id", surah.getId());
        startActivity(intent);
    }
}