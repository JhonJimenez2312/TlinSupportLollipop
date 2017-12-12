package com.example.admin.tlintest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
    private FirebaseUser user;
    private FirebaseAuth fAuth;
    private RecyclerView rv;
    private List<Almacen> almacenes;
    private Adapter adapter;
    private DatabaseReference databaseReference;
    private final String BD="Almacenes";
    private ImageView pro_pic;

    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fAuth = FirebaseAuth.getInstance();

        //validamos el estado de sesion del usuario
        user= FirebaseAuth.getInstance().getCurrentUser();
        if (user==null){
            //si el usuario no esta logueado lo enviamos al login
            startActivity(new Intent(this,Login.class));
            finish();
        }else {
            //si esta logueado lo enviamos a la actividad principal
            setContentView(R.layout.activity_principal);
            //relacionamos el campo para la imagen de usuario
            pro_pic=(ImageView)findViewById(R.id.imgUser);
            //inicio listado de almacenes
            rv = (RecyclerView)findViewById(R.id.rvRecicler);
            rv.setLayoutManager(new LinearLayoutManager(this));
            almacenes = new ArrayList<>();
            //traemos la informacion de la base de datos
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            adapter = new Adapter(this.getApplicationContext(),almacenes);
            rv.setAdapter(adapter);
            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //vaciamos los almacenes almacenados anteriormente
                    almacenes.clear();
                    //recorrermos la informacion de la base de datos
                    //llenando la lista de almacenes en el adaptador
                    if(dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Almacen almacen = snapshot.getValue(Almacen.class);
                            almacenes.add(almacen);
                        }
                    }
                    //notificamos que hemos cambiado los datos al adaptador
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),
                            "Error", Toast.LENGTH_SHORT);
                }
            });

            //fin listado de almacenes

            //Actualizamos la vista con la informacion de la cuenta de google
            //String userName =user.getDisplayName();
            Uri personPhoto = user.getPhotoUrl();
            String url2 = user.getPhotoUrl().toString();
            Picasso.with(this.getApplicationContext()).load(url2).into(pro_pic);
        }


    }
    public void signOut(View v){
        //cerrar sesion
        fAuth.getInstance().signOut();
        fAuth.signOut();
        Intent i = new Intent(Principal.this, Login.class);
        String sesion  = "out";
        i.putExtra("OUT", sesion);
        startActivity(i);
        finish();
    }

}
