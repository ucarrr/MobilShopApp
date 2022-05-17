package com.example.shopapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.CardCategoryDesign>{

    private Context mContex;
    private List<Category> categoryList;


    public CategoryAdapter(Context mContex, List<Category> categoryList) {
        this.mContex = mContex;
        this.categoryList = categoryList;
    }

    @Override
    public CardCategoryDesign onCreateViewHolder(@NonNull   ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.category_card_design,parent,false);

        return new CardCategoryDesign(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  CategoryAdapter.CardCategoryDesign holder, int position) {


        Category category=categoryList.get(position);

        holder.textViewCategoryTitle.setText(category.getCategory_Name());

        String url=category.getCategory_image();
        Picasso.get().load(url).into(holder.imageViewCategory);

     /*   holder.imageViewCategory.setImageResource(
                mContex.getResources().
                        getIdentifier
                                (category.getCategory_image(),"drawable",mContex.getPackageName()));*/


        holder.cardViewCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContex,ProductActivity.class);
                intent.putExtra("category",category);
                mContex.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CardCategoryDesign extends RecyclerView.ViewHolder {


        private CardView cardViewCategory;
        private TextView textViewCategoryTitle;
        private ImageView imageViewCategory;



        public CardCategoryDesign(@NonNull View itemView) {
            super(itemView);
            cardViewCategory=itemView.findViewById(R.id.category_CardView);
            textViewCategoryTitle=itemView.findViewById(R.id.category_textView);
            imageViewCategory=itemView.findViewById(R.id.category_imageView);

        }
    }
}
