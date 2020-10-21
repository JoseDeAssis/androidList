package com.example.exemplocrudtenis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TennisListRVAdapter extends RecyclerView.Adapter<TennisListRVAdapter.TennisListRVViewHolder> implements Serializable {

    private final LayoutInflater mLayoutInflater;
    private List<Tennis> tennisList;
    private final TenisDetailsListener tennisDetailsListener;
    private final Context context;

    public class TennisListRVViewHolder extends RecyclerView.ViewHolder {

        private final TextView tennisModel, tennisId, tennisPrice;
        private final ImageView tennisImg;

        private TennisListRVViewHolder(View itemView, final TenisDetailsListener listener) {
            super(itemView);

            tennisModel = itemView.findViewById(R.id.recyclerViewTennisModel);
            tennisId = itemView.findViewById(R.id.recyclerViewTennisId);
            tennisPrice = itemView.findViewById(R.id.recyclerViewTennisPrice);
            tennisImg = itemView.findViewById(R.id.recyclerViewTenisImg);
            ImageButton tennisDeleteBtn = itemView.findViewById(R.id.recyclerViewDeleteBtn);
            ImageButton tennisEditBtn = itemView.findViewById(R.id.recyclerViewEditBtn);
            CardView tennisCardView = itemView.findViewById(R.id.recyclerViewTennisCardView);

            tennisCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tennis tennis = tennisList.get(getAdapterPosition());
                    listener.editTennis(tennis);
                }
            });

            tennisEditBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tennis tennis = tennisList.get(getAdapterPosition());
                    listener.editTennis(tennis);
                }
            });

            tennisDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Tennis tennis = tennisList.get(getAdapterPosition());
                    listener.deleteTennis(tennis);
                }
            });
        }
    }

    public TennisListRVAdapter(Context context, TenisDetailsListener mListener) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.tennisDetailsListener = mListener;
        tennisList = new ArrayList<>();
    }

    @NonNull
    @Override
    public TennisListRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TennisListRVViewHolder(mLayoutInflater.
                inflate(R.layout.app_rv_sneakers_list, parent, false), this.tennisDetailsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TennisListRVViewHolder holder, final int position) {
        if (this.tennisList != null) {
            holder.tennisModel.setText(this.tennisList.get(position).getTennisModel());
            String tennisId = "Id: " + this.tennisList.get(position).getId();
            holder.tennisId.setText(tennisId);

            String tennisPrice = "$ " + this.tennisList.get(position).getTennisPrice();
            holder.tennisPrice.setText(tennisPrice);

            try {
                // get input stream
                InputStream ims = context.getAssets().open(this.tennisList.get(position).getTennisModel() + ".png");
                // load image as Drawable
                Bitmap bitmap = BitmapFactory.decodeStream(ims);
                holder.tennisImg.setImageBitmap(bitmap);
                ims.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void setTennisList(List<Tennis> tennisList) {
        this.tennisList = tennisList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tennisList != null ? tennisList.size() : 0;
    }

}
