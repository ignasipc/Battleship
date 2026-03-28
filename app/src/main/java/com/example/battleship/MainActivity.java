package com.example.battleship;

import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// TODO: Fer testing de la P0 amem si funciona tot correctament a un emulador o un mòvil.

public class MainActivity extends AppCompatActivity {
    private enum estatsJoc {JUGANT, ATURADA};
    private static estatsJoc estatActualJoc = estatsJoc.ATURADA;
    private static SurfaceView dibuix;                                                              // TODO: Comprobar más adelante si es correcta esta manera de obtener 'dibuix'

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

        // Posam apaisada la pantalla (P0.1)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Feim el TextView desplaçable (P0.1.6)
        TextView messageTextView = (TextView) findViewById(R.id.messagesTextView);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        dibuix = (SurfaceView) findViewById(R.id.hisSurfaceView);

        dibuix.post(() -> pintar());

        Button botoAturar = (Button) findViewById(R.id.buttonStop);
        Button botoNouJoc = (Button) findViewById(R.id.buttonNewGame);
        Button botoConnectar = (Button) findViewById(R.id.buttonConnect);
        Button botoPista = (Button) findViewById(R.id.buttonHint);

        // Lògica de l'estat actual del joc que indica quins botons están activats i quins no
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

    /**
     * Mètode per pintar a un SurfaceView anomenat dibuix.
     */
    public static void pintar(){
        if(dibuix.getHolder().getSurface().isValid()){
            int alt = dibuix.getHeight();
            int ampla = dibuix.getWidth();
            Canvas canvas = dibuix.getHolder().lockCanvas();
            canvas.drawColor(Color.BLACK);

            // El nostre mètode de dibuixat
            Paint p = new Paint();
            p.setColor(Color.RED);
            p.setStrokeWidth(5);
            canvas.drawLine(0,0, alt, ampla, p);

            dibuix.getHolder().unlockCanvasAndPost(canvas);
        }
    }
}