package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        EditText nameEditText = (EditText) findViewById(R.id.name_edittext);

        String name = nameEditText.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);

        int price = calculatePrice(whippedCreamCheckBox.isChecked(), chocolateCheckBox.isChecked());

        String priceMessage = createOrderSummary(price, whippedCreamCheckBox.isChecked(),chocolateCheckBox.isChecked(),name);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "tshepzmogapi@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for" + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is for when the plus button is clicked
     * @param view
     */
    public void increment(View view)
    {
        if (quantity < 10)
        {
            quantity += 1;
            displayQuantity(quantity);
        }
        else
        {
            Toast.makeText(this,"You can't order more than 100 coffees!", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is for when the minus button is clicked
     * @param view
     */
    public void decrement(View view)
    {
        if (quantity > 1)
        {
            quantity -= 1;
            displayQuantity(quantity);
        }
        else
        {
            Toast.makeText(this,"You can't order less than 1 coffee!", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * calculates the total price of the items purchased
     * @return total price of items charged  5 units each
     */

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate)
    {
        int basePrice = 5;

        if (hasWhippedCream)
            basePrice += 1;
        if (hasChocolate)
            basePrice += 2;
        return quantity * basePrice;
    }


    /**
     * This method displays the order summary on the screen
     * @param price of the item
     * @param hasWhippedCream does the ordered coffee have whipped cream
     * @param hasChocolate does the ordered coffee have chocolate
     * @param name of the person ordering the coffee
     * @return priceMessage
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String name)
    {
        String priceMessage = "Name : " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity : " + quantity;
        priceMessage += "\nTotal : R" + price + "\nThank you";
        return priceMessage;
    }

}