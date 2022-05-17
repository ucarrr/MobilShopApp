package com.example.shopapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CardViewMyCartDesign>  {

    private Context context;
    private List<Products> productsList;

    private DatabaseReference myRefProduct;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;


    public CartAdapter() {
    }

    @NonNull

    @Override
    public CardViewMyCartDesign onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView=
                LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.mycart_cartview,parent,false);
        return new CardViewMyCartDesign(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  CartAdapter.CardViewMyCartDesign holder, int position) {

        Products products=productsList.get(position);

   /*     holder.imageViewcart.setImageResource(
                context.getResources().getIdentifier(products.getProduct_image(),
                        "drawable",context.getPackageName()));*/
        String url=products.getProduct_image();
        Picasso.get().load(url).into(holder.imageViewcart);
        holder.textViewCartName.setText(products.getProduct_name());
        holder.textViewcartestmate.setText(products.getProduct_shipment());
        holder.textViewCartCost.setText(products.getProduct_cost()+" Tl ");
        holder.textViewCartBrand.setText(products.getProduct_brand());
       // holder.textViewQunatity.setText("prod");
        //holder.textViewProductSize.setText("sdasdasd");

        holder.garbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                mAuth=FirebaseAuth.getInstance();
                myRefProduct = database.getReference("cartList");
                myRefProduct.child(mAuth.getUid()).child("Products").child(products.getProduct_id()).
                        removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull   Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(context,products.getProduct_name()+" Deleted In Cart ",Toast.LENGTH_SHORT).show();
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

    public CartAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    public class CardViewMyCartDesign extends RecyclerView.ViewHolder{

        private ImageView imageViewcart,garbage;
        private TextView textViewCartName,textViewProductSize;
        private TextView textViewCartCost,textViewcartestmate,textViewCartBrand,textViewQunatity;
        private Button buttonCart;
        private CardView cardViewCart;

        public CardViewMyCartDesign(@NonNull  View itemView) {
            super(itemView);


            imageViewcart=itemView.findViewById(R.id.imageViewCartviewmyCart);
            textViewCartName=itemView.findViewById(R.id.CartviewMycartproductname);
            textViewCartBrand=itemView.findViewById(R.id.textViewcartviewmycartResourceName);
            textViewCartCost=itemView.findViewById(R.id.textViewcartviewmycartCost);
            cardViewCart=itemView.findViewById(R.id.product_CardView);
            textViewcartestmate=itemView.findViewById(R.id.textViewcartviewmycartEstimate);
            //textViewProductSize=itemView.findViewById(R.id.textViewcardviewsize);
            textViewQunatity=itemView.findViewById(R.id.cartviewmycartAdet);
            garbage=itemView.findViewById(R.id.garbage);

        }
    }

}
