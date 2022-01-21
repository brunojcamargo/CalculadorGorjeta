package dev.bc4.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText    editValor;
    private TextView    textPorcentagem;
    private TextView    textGorjeta;
    private TextView    textTotal;
    private SeekBar     SeekBarGorjeta;

    private double porcentagem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editValor       = findViewById(R.id.editValor);
        textPorcentagem = findViewById(R.id.textPercent);
        textGorjeta     = findViewById(R.id.textGorjeta);
        textTotal       = findViewById(R.id.textTotal);
        SeekBarGorjeta  = findViewById(R.id.seekBarGorjeta);


        SeekBarGorjeta.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress;
                textPorcentagem.setText(Math.round(porcentagem) + " %");
                calcular();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void calcular(){
        String valorRecuperado = editValor.getText().toString();
        if(valorRecuperado == null || valorRecuperado.equals("")){
            Toast.makeText(
                    getApplicationContext(),
                    "Digite um valor primeiro!",
                    Toast.LENGTH_LONG
            ).show();
        }else{
            double valorDigitado    = Double.parseDouble(valorRecuperado);
            double gorjeta          = valorDigitado * (porcentagem/100);
            double total            = valorDigitado+gorjeta;

            String GorjetaFormatado = new DecimalFormat("#,##0.00").format(gorjeta);
            String totalFormatado = new DecimalFormat("#,##0.00").format(total);

            textGorjeta.setText("R$ "+GorjetaFormatado);
            textTotal.setText("R$ "+totalFormatado);
        }
    }
}







