package com.example.sandra.proyecto0509;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrarUsuarios extends AppCompatActivity implements View.OnClickListener {

    Button registraseFinal;
    EditText namefinal;
    EditText passwordfinal;
    EditText correo;

    private ProgressDialog progressDialog;

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuarios);

        registraseFinal= (Button) findViewById(R.id.btn_registrarFinal);
        namefinal= (EditText) findViewById(R.id.et_name);
        passwordfinal= (EditText) findViewById(R.id.et_password);
        correo= (EditText) findViewById(R.id.et_correo);

        registraseFinal.setOnClickListener(this);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

       /*registraseFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });*/

    }

    /*private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = correo.getText().toString().trim();
        String password  = passwordfinal.getText().toString().trim();
        String name= namefinal.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Introduce el email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Introduce la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Introduce el nombre",Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando registro...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(RegistrarUsuarios.this,"Se ha registrado el usuario con el email: "+ correo.getText(),Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), CrearUsuarios.class);
                            startActivity(intencion);
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(RegistrarUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(RegistrarUsuarios.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }*/

    private void registrar(String email, String password){
        progressDialog.setMessage("Realizando registro...");
        progressDialog.show();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(RegistrarUsuarios.this,"Se ha registrado el usuario con el email: "+ correo.getText(),Toast.LENGTH_LONG).show();
                    Intent intencion = new Intent(getApplication(), CrearUsuarios.class);
                    startActivity(intencion);
                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                        Toast.makeText(RegistrarUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(RegistrarUsuarios.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        String email=correo.getText().toString();
        String password=passwordfinal.getText().toString();
        registrar(email,password);
    }


}
