package com.example.battleship;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private enum estatsJoc {JUGANT, ATURADA};
    private static estatsJoc estatActualJoc = estatsJoc.ATURADA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        TextView messageTextView = (TextView) findViewById(R.id.messagesTextView);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        Button botoAturar = (Button) findViewById(R.id.buttonStop);
        Button botoNouJoc = (Button) findViewById(R.id.buttonNewGame);
        Button botoConnectar = (Button) findViewById(R.id.buttonConnect);
        Button botoPista = (Button) findViewById(R.id.buttonHint);

        if (estatActualJoc == estatsJoc.JUGANT){
            // Aturar i Pista activats, la resta desactivats
            botoAturar.setEnabled(true);
            botoPista.setEnabled(true);
            botoNouJoc.setEnabled(false);
            botoConnectar.setEnabled(false);
        } else if (estatActualJoc == estatsJoc.ATURADA){
            // Nou joc i Connectar activats, la resta desactivats
            botoNouJoc.setEnabled(true);
            botoConnectar.setEnabled(true);
            botoAturar.setEnabled(false);
            botoPista.setEnabled(false);
        }

        botoAturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button Pressed", "Button Stop has ben pressed");
            }
        });

        botoNouJoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button Pressed", "Button New Game has ben pressed");
            }
        });

        botoConnectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button Pressed", "Button Connect has ben pressed");
            }
        });

        botoPista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Button Pressed", "Button Hint has ben pressed");
            }
        });
    }
}