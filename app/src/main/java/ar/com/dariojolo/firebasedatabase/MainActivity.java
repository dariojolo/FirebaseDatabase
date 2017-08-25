package ar.com.dariojolo.firebasedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView lblCielo;
    private TextView lblHumedad;
    private TextView lblTemperatura;
    private DatabaseReference dbPrediccion;
    private ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblCielo = (TextView)findViewById(R.id.lblCielo);
        lblHumedad = (TextView)findViewById(R.id.lblHumedad);
        lblTemperatura = (TextView)findViewById(R.id.lblTemperatura);

      /*  DatabaseReference dbCielo =
                FirebaseDatabase.getInstance().getReference()
                .child("prediccion-hoy")
                .child("cielo");

        dbCielo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String valor = dataSnapshot.getValue().toString();
                lblCielo.setText(valor);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", databaseError.toException().toString());
            }
        });*/
        dbPrediccion = FirebaseDatabase.getInstance().getReference()
                .child("prediccion-hoy");

        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Prediccion p = dataSnapshot.getValue(Prediccion.class);
                lblCielo.setText(p.getCielo());
                lblTemperatura.setText(p.getTemperatura() + "ºC");
                lblHumedad.setText(p.getHumedad() + "%");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("ERROR", databaseError.toException().toString());
            }
        };
        dbPrediccion.addValueEventListener(eventListener);
        //dbPrediccion.addListenerForSingleValueEvent(eventListener);
        //dbPrediccion.removeEventListener(eventListener);

    }
}
