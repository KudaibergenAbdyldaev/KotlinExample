package com.example.brandstore.Fragments.HomeFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brandstore.Model.ProductData;
import com.example.brandstore.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterHomeJava extends RecyclerView.Adapter<AdapterHomeJava.Holder> {
    StorageReference storageReference;
    DatabaseReference reference;
    FirebaseUser user;

    private Context context;
    private List<ProductData> productDataList = new ArrayList<>();

    public AdapterHomeJava(Context context, List<ProductData> productDataList) {
        this.context = context;
        this.productDataList = productDataList;
    }

    @NonNull
    @Override
    public AdapterHomeJava.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_main, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHomeJava.Holder holder, int position) {
        final ProductData uploadCurrent = productDataList.get(position);
        holder.txt_name.setText(uploadCurrent.getNameProduct());
        holder.txt_price.setText(uploadCurrent.getPriceProduct());
        Picasso.get()
                .load(uploadCurrent.getImageView())
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return productDataList.size();
    }

    static class Holder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_price;
        ImageView imageView;
        Holder(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.name_product);
            txt_price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
