package id.ac.poliban.mi.VC.e020320078.fda_e020320078.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import id.ac.poliban.mi.VC.e020320078.fda_e020320078.Adapter.CartListAdapter;
import id.ac.poliban.mi.VC.e020320078.fda_e020320078.Helper.ManagementCart;
import id.ac.poliban.mi.VC.e020320078.fda_e020320078.R;

public class CartListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewList;
        private ManagementCart managementCart;
        private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private ScrollView scrollView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cart_list);

            managementCart = new ManagementCart(this);

            initView();
            initList();
            calculateCard();
            bottomNavigation();
        }
        private void bottomNavigation() {
            FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
            LinearLayout homeBtn = findViewById(R.id.homeBtn1);

            floatingActionButton.setOnClickListener(v -> startActivity(new Intent(CartListActivity.this, CartListActivity.class)));

            homeBtn.setOnClickListener(v -> startActivity(new Intent(CartListActivity.this, MainActivity.class)));
        }
        private void initList() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewList.setLayoutManager(linearLayoutManager);
            RecyclerView.Adapter<CartListAdapter.ViewHolder> adapter = new CartListAdapter(managementCart.getListCard(), this, this::calculateCard);

            recyclerViewList.setAdapter(adapter);
            if (managementCart.getListCard().isEmpty()) {
                emptyTxt.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.GONE);
            } else {
                emptyTxt.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            }
        }

        @SuppressLint("SetTextI18n")
        private void calculateCard() {
            double percentTax = 0.02;
            double delivery = 10;

            double tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
            double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
            double itemTotal = Math.round(managementCart.getTotalFee() * 100.0) / 100.0;

            totalFeeTxt.setText("$" + itemTotal);
            taxTxt.setText("$" + tax);
            deliveryTxt.setText("$" + delivery);
            totalTxt.setText("$" + total);
        }
        private void initView() {

            recyclerViewList = findViewById(R.id.cartView);
            totalFeeTxt = findViewById(R.id.totalFeeTxt);
            taxTxt = findViewById(R.id.taxTxt);
            deliveryTxt = findViewById(R.id.deliveryTxt);
            totalTxt = findViewById(R.id.totalTxt);
            emptyTxt = findViewById(R.id.emptyTxt);
            scrollView = findViewById(R.id.scrollView4);
        }
    }