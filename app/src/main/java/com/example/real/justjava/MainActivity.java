package com.example.real.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        // Figure out if the user wants chocolate topping
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice();
        String priceMessage = createOrderSummary(name, price ,hasWhippedCream, hasChocolate);
        priceMessage = priceMessage + "\nThank you!";
        displayMessage(priceMessage);
        calculatePrice(hasWhippedCream,hasChocolate);
        //display(quantity * 5);
        //display(2*5);
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    public void increment(View view) {
        if(quantity==100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        int quantity = 2;
        if(quantity==1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity =  quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

        /**
         * Calculates the price of the order.
         *
         *
         */
        private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
            int basePrice = 5;

            if(addWhippedCream) {
                basePrice = basePrice + 1;
            }
            if(addChocolate) {
                basePrice += 2;
            }

            return quantity * basePrice;

        }

        private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
            String priceMessage = "Name: " + name;
            priceMessage += "\nAdd whipped cream? " + addWhippedCream;
            priceMessage += "\nAdd chocolate? " + addChocolate;
            priceMessage += "\nQuantity: " + quantity;
            priceMessage += "\nTotal: $" + price;
            priceMessage += "\nThank you";
            return priceMessage;
        }
}