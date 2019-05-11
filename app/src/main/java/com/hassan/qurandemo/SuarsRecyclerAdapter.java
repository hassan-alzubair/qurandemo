package com.hassan.qurandemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SuarsRecyclerAdapter extends RecyclerView.Adapter<SuarsRecyclerAdapter.SurahViewHolder>{

    private List<Surah> surahList;
    private OnSurahClickListener listener;

    public SuarsRecyclerAdapter(List<Surah> surahList) {
        this.surahList = surahList;
    }

    @NonNull
    @Override
    public SurahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.surah_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SurahViewHolder holder, int position) {
        final Surah surah = surahList.get(position);
        holder.txtSurahRelev.setText(surah.getRevelationType());
        holder.txtSurahAyaCount.setText(surah.getAyahs().size()+"");
        holder.txtSurahName.setText(surah.getSurahName());
        holder.txtNumber.setText((position+1)+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClick(surah);
                }
            }
        });
    }

    public void setOnSurahClickListener(OnSurahClickListener listener){
        this.listener = listener;
    }
    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public static class SurahViewHolder extends RecyclerView.ViewHolder{

        TextView txtNumber;
        TextView txtSurahName;
        TextView txtSurahAyaCount;
        TextView txtSurahRelev;

        public SurahViewHolder(View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.txtSuraNumber);
            txtSurahName = itemView.findViewById(R.id.txtSuraName);
            txtSurahAyaCount= itemView.findViewById(R.id.txtSurahAyaCount);
            txtSurahRelev= itemView.findViewById(R.id.txtSurahRelev);
        }
    }

    public interface OnSurahClickListener{
        void onClick(Surah surah);
    }
}