package com.example.shopapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.SimpleTimeZone;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CardViewDesign> {
    private Context context;
    private List<Products> productsList;

    private FirebaseAuth mAuth;

    private DatabaseReference myRefProduct;

    private FirebaseDatabase database;


    public ProductAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public CardViewDesign onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.card_product_design,parent,false);
        return new CardViewDesign(itemView);
    }

    public void AddCart(){


    }

    @Override
    public void onBindViewHolder(@NonNull  ProductAdapter.CardViewDesign holder, int position) {

         Products products=productsList.get(position);

  /*       holder.imageViewProduct.setImageResource(
                context.getResources().getIdentifier(products.getProduct_image(),
                        "drawable",context.getPackageName()));*/
        String url=products.getProduct_image();
        Picasso.get().load(url).into(holder.imageViewProduct);
        holder.textViewProductTitle.setText(products.getProduct_name());
        holder.textViewProductCost.setText(products.getProduct_cost()+" TL ");
        holder.buttonAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String saveCurrentTime , SaveCurrentDate;
                Calendar calendar=Calendar.getInstance();

                SimpleDateFormat currentDate=new SimpleDateFormat("MMM dd, yyyy");
                SaveCurrentDate=currentDate.format(calendar.getTime());

                SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
                saveCurrentTime=currentTime.format(calendar.getTime());

                final DatabaseReference cartlistref=FirebaseDatabase.getInstance().getReference("cartList");

                /*database = FirebaseDatabase.getInstance();
                myRefProduct = database.getReference("category");
*/
                final HashMap<String,Object> cartmap=new HashMap<>();
                cartmap.put("product_id",products.getProduct_id());
                cartmap.put("Product_name",products.getProduct_name());
                cartmap.put("Product_cost",products.getProduct_cost());
                cartmap.put("Product_image",products.getProduct_image());
                cartmap.put("Product_brand",products.getProduct_brand());
                cartmap.put("Product_shipment",products.getProduct_shipment());
                cartmap.put("Product_genderType",products.getProduct_genderType());
                cartmap.put("Time",saveCurrentTime);
                cartmap.put("Date",SaveCurrentDate);

                mAuth=FirebaseAuth.getInstance();
                cartlistref.child(mAuth.getUid())
                        .child("Products").child(products.getProduct_id()).updateChildren(cartmap).
                        addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(context,products.getProduct_name()+" Added Card ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });





            }
        });


        holder.cardViewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("product",products);
                context.startActivity(intent);

            }
        });
        holder.imageViewMoreMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context," More menu Card ",Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu=new PopupMenu(context,holder.imageViewMoreMenu);
                MenuInflater inflater=popupMenu.getMenuInflater();
                inflater.inflate(R.menu.card_more_menu,popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.detail_menu:
                                Toast.makeText(context," Detail menu Card ",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(context,DetailActivity.class);
                                intent.putExtra("product",products);
                                context.startActivity(intent);
                                return true;
                            case R.id.enroll_now_menu:
                                Toast.makeText(context," Enroll Now menu Card ",Toast.LENGTH_SHORT).show();
                                return true;

                            default: return false;

                        }
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }


    public class CardViewDesign extends RecyclerView.ViewHolder{

        public ImageView imageViewProduct;
        public TextView textViewProductTitle;
        public TextView textViewProductCost;
        public Button buttonAddCart;
        public ImageView imageViewMoreMenu;
        public CardView cardViewProduct;

        public CardViewDesign(@NonNull View itemView) {
            super(itemView);
            imageViewProduct=itemView.findViewById(R.id.imageViewProduct);
            textViewProductTitle=itemView.findViewById(R.id.textViewProductTitle);
            textViewProductCost=itemView.findViewById(R.id.textViewProductCost);
            buttonAddCart=itemView.findViewById(R.id.buttonAddCart);
            imageViewMoreMenu=itemView.findViewById(R.id.imageMoreMenu);
            cardViewProduct=itemView.findViewById(R.id.product_CardView);
        }
    }
}
