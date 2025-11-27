package com.example.otech.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.otech.R;
import com.example.otech.adapter.OrderProductAdapter;
import com.example.otech.model.Order;
import com.example.otech.repository.MockDataStore;
import com.example.otech.util.Constants;
import com.example.otech.util.FormatUtils;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

public class OrderDetailActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TextView tvOrderId, tvOrderDate, tvOrderStatus;
    private TextView tvReceiverName, tvReceiverPhone, tvDeliveryAddress;
    private TextView tvSubtotal, tvShippingFee, tvTotalAmount;
    private RecyclerView rvOrderProducts;
    private MaterialButton btnCancelOrder;
    
    // Status icons
    private ImageView ivStatusPending, ivStatusProcessing, ivStatusShipping, ivStatusCompleted;

    private Order order;
    private MockDataStore dataStore;
    private String currentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        dataStore = MockDataStore.getInstance();

        // Get current user
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        currentUserId = prefs.getString(Constants.KEY_USER_ID, "");

        // Get order from intent
        order = (Order) getIntent().getSerializableExtra(Constants.EXTRA_ORDER);

        if (order == null) {
            Toast.makeText(this, "Không tìm thấy đơn hàng", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        initViews();
        displayOrderInfo();
        setupListeners();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        tvOrderId = findViewById(R.id.tvOrderId);
        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);
        tvReceiverName = findViewById(R.id.tvReceiverName);
        tvReceiverPhone = findViewById(R.id.tvReceiverPhone);
        tvDeliveryAddress = findViewById(R.id.tvDeliveryAddress);
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvShippingFee = findViewById(R.id.tvShippingFee);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        rvOrderProducts = findViewById(R.id.rvOrderProducts);
        btnCancelOrder = findViewById(R.id.btnCancelOrder);
        
        ivStatusPending = findViewById(R.id.ivStatusPending);
        ivStatusProcessing = findViewById(R.id.ivStatusProcessing);
        ivStatusShipping = findViewById(R.id.ivStatusShipping);
        ivStatusCompleted = findViewById(R.id.ivStatusCompleted);
    }

    private void displayOrderInfo() {
        // Order basic info
        tvOrderId.setText("Mã đơn: #" + order.getId());
        tvOrderDate.setText("Ngày đặt: " + android.text.format.DateFormat.format("dd/MM/yyyy", order.getOrderDate()));
        
        // Status with color
        String status = order.getStatus();
        tvOrderStatus.setText(getStatusText(status));
        tvOrderStatus.setTextColor(getStatusColor(status));
        
        // Update status progress
        updateStatusProgress(status);

        // Delivery address (using User info or order data)
        tvReceiverName.setText("Khách hàng"); // Can get from User model if needed
        tvReceiverPhone.setText(order.getPhone());
        tvDeliveryAddress.setText(order.getDeliveryAddress());

        // Price summary
        tvSubtotal.setText(FormatUtils.formatCurrency(order.getTotalAmount()));
        tvShippingFee.setText("Miễn phí"); // Free shipping
        tvTotalAmount.setText(FormatUtils.formatCurrency(order.getTotalAmount()));

        // Products list
        OrderProductAdapter adapter = new OrderProductAdapter(this, order.getItems());
        rvOrderProducts.setLayoutManager(new LinearLayoutManager(this));
        rvOrderProducts.setAdapter(adapter);

        // Show/hide cancel button based on status
        if ("pending".equalsIgnoreCase(status)) {
            btnCancelOrder.setVisibility(View.VISIBLE);
        } else {
            btnCancelOrder.setVisibility(View.GONE);
        }
    }

    private String getStatusText(String status) {
        switch (status.toLowerCase()) {
            case "pending":
                return "Chờ xử lý";
            case "processing":
                return "Đang xử lý";
            case "shipping":
                return "Đang giao hàng";
            case "completed":
                return "Hoàn thành";
            case "cancelled":
                return "Đã hủy";
            default:
                return status;
        }
    }

    private int getStatusColor(String status) {
        switch (status.toLowerCase()) {
            case "pending":
                return getResources().getColor(R.color.colorSecondary, null);
            case "processing":
                return getResources().getColor(R.color.colorPrimary, null);
            case "shipping":
                return getResources().getColor(R.color.colorPrimary, null);
            case "completed":
                return getResources().getColor(R.color.colorSuccess, null);
            case "cancelled":
                return getResources().getColor(R.color.colorPriceRed, null);
            default:
                return getResources().getColor(R.color.text_secondary, null);
        }
    }

    private void updateStatusProgress(String status) {
        // Reset all to inactive state
        resetStatusIcons();

        // Activate icons based on status
        switch (status.toLowerCase()) {
            case "pending":
                activateStatusIcon(ivStatusPending);
                break;
            case "processing":
                activateStatusIcon(ivStatusPending);
                activateStatusIcon(ivStatusProcessing);
                break;
            case "shipping":
                activateStatusIcon(ivStatusPending);
                activateStatusIcon(ivStatusProcessing);
                activateStatusIcon(ivStatusShipping);
                break;
            case "completed":
                activateStatusIcon(ivStatusPending);
                activateStatusIcon(ivStatusProcessing);
                activateStatusIcon(ivStatusShipping);
                activateStatusIcon(ivStatusCompleted);
                break;
        }
    }

    private void resetStatusIcons() {
        ivStatusPending.setBackgroundTintList(getResources().getColorStateList(R.color.gray_light, null));
        ivStatusProcessing.setBackgroundTintList(getResources().getColorStateList(R.color.gray_light, null));
        ivStatusShipping.setBackgroundTintList(getResources().getColorStateList(R.color.gray_light, null));
        ivStatusCompleted.setBackgroundTintList(getResources().getColorStateList(R.color.gray_light, null));
    }

    private void activateStatusIcon(ImageView imageView) {
        imageView.setBackgroundTintList(getResources().getColorStateList(R.color.colorSecondary, null));
    }

    private void setupListeners() {
        toolbar.setNavigationOnClickListener(v -> finish());

        btnCancelOrder.setOnClickListener(v -> showCancelOrderDialog());
    }

    private void showCancelOrderDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Hủy đơn hàng")
                .setMessage("Bạn có chắc chắn muốn hủy đơn hàng này?")
                .setPositiveButton("Hủy đơn", (dialog, which) -> {
                    // Update order status to cancelled
                    order.setStatus("cancelled");
                    dataStore.updateOrderStatus(order.getId(), "cancelled");
                    
                    Toast.makeText(this, "Đã hủy đơn hàng", Toast.LENGTH_SHORT).show();
                    
                    // Refresh display
                    displayOrderInfo();
                })
                .setNegativeButton("Không", null)
                .show();
    }
}
