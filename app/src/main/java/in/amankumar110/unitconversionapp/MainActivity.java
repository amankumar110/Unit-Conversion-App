package in.amankumar110.unitconversionapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    private static final double POUNDS_PER_KILOGRAM = 2.204;
    private static final int GRAMS_PER_KILOGRAM = 1000;

    private static final int KILOGRAMS_PER_TONNE =1000;

    private LinearLayout button;
    private TextView weightDisplay;
    private EditText weightInput;
    private RadioGroup radioGroup;

    private RadioButton chosenRadioOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.enter);
        weightDisplay = findViewById(R.id.displayWeight);
        weightInput = findViewById(R.id.weightInput);
        radioGroup = findViewById(R.id.radioGroup);

        chosenRadioOption = findViewById(radioGroup.getCheckedRadioButtonId());

        button.setOnClickListener((View v) -> {
            String kilograms = weightInput.getText().toString();

            if (kilograms.isEmpty()) {
                showToast("Please enter a number!");
                return;
            }

            if (chosenRadioOption == null) {
                showToast("Please select a conversion unit!");
                return;
            }

            try {
                float weightInKilograms = Float.parseFloat(kilograms);
                String unit = chosenRadioOption.getText().toString();

                switch (unit) {
                    case "Pounds":
                        displayWeight(convertToPounds(weightInKilograms));
                        break;
                    case "Grams":
                        displayWeight(convertToGrams(weightInKilograms));
                        break;
                    case "Tonnes":
                        displayWeight(convertToTonnes(weightInKilograms));
                        break;
                }
            } catch (NumberFormatException e) {
                showToast("Please enter a valid number!");
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            chosenRadioOption = findViewById(checkedId);
        });
    }

    private float convertToPounds(float kilograms) {
        return (float) (kilograms * POUNDS_PER_KILOGRAM);
    }

    private float convertToGrams(float kilograms) {
        return kilograms * GRAMS_PER_KILOGRAM;
    }

    private float convertToTonnes(float kilograms) {
        return kilograms / KILOGRAMS_PER_TONNE;
    }

    private void displayWeight(float weight) {
        weightDisplay.setText(String.valueOf(weight));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}