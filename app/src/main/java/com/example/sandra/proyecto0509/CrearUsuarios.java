package com.example.sandra.proyecto0509;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;



import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CrearUsuarios extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    private Button btn_registrado;

// y quiero acceder a esta variable desde
    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    FirebaseAuth.AuthStateListener authStateListener;

    private static final Pattern PASSWORD_PATTERN= Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            //"(?=.*[a-z])" +         //at least 1 lower case letter
            //"(?=.*[A-Z])" +         //at least 1 upper case letter
            //"(?=.*[a-zA-Z])" //+    //any letter
            //"(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuarios);


        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.ed_email);
        TextPassword = (EditText) findViewById(R.id.et_password);

        btnRegistrar = (Button) findViewById(R.id.btn_register);
        //attaching listener to button
        btnRegistrar.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);

        btn_registrado= (Button) findViewById(R.id.btn_yaregistrado);
        btn_registrado.setOnClickListener(this);

        authStateListener=new FirebaseAuth.AuthStateListener() {
            //comprobamos el inicio y el cierre de sesion
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();

                if(user!=null){
                    Toast.makeText(CrearUsuarios.this,"Sesion iniciada con email " +user.getEmail(),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(CrearUsuarios.this,PantallaSecundaria.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(CrearUsuarios.this,"Sesion cerrada",Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    public void registrar(String email, String password){

        /*progressDialog.setMessage("Realizando registro...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();*/
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                   // Toast.makeText(CrearUsuarios.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                }
                else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                        Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(CrearUsuarios.this,"No se pudo registrar el usuario la contraseña tiene que tener al menos 6 caracteres con algun numero ",Toast.LENGTH_LONG).show();
                }
                //progressDialog.dismiss();

            }
        });
    }

    public void iniciarsesion(String email, String password){
        /*progressDialog.setMessage("Realizando consulta...");
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();*/
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(CrearUsuarios.this,"Ha iniciado sesion  el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                    Intent intencion = new Intent(getApplication(), CrearUsuarios.class);
                    startActivity(intencion);
                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                        Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(CrearUsuarios.this,"No se pudo iniciar sesion ",Toast.LENGTH_LONG).show();
                }

            }
        });
        //progressDialog.dismiss();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btn_register:


                String emailInicio=TextEmail.getText().toString();
                String passwordInicio=TextPassword.getText().toString();
                if(emailInicio.isEmpty() || passwordInicio.isEmpty()){
                    Toast.makeText(CrearUsuarios.this,"Introduce el email y contraseña y pulsa REGISTRAR ",Toast.LENGTH_SHORT).show();
                }
                else if(emailInicio.equals("jefeestudios33@hotmail.com")){
                    Intent intencion = new Intent(getApplication(), Pantalla_Secundaria_Jefe.class);
                    startActivity(intencion);
                }
                else {
                    registrar(emailInicio,passwordInicio);
                }
                break;
            case R.id.btn_yaregistrado:

                String emailSesion=TextEmail.getText().toString();
                String passwordSesion=TextPassword.getText().toString();
                if(emailSesion.isEmpty() || passwordSesion.isEmpty()){
                    Toast.makeText(CrearUsuarios.this,"Introduce el email y contraseña y pulsa INICIAR SESION",Toast.LENGTH_SHORT).show();
                }
               else  if(emailSesion.equals("jefeestudios33@hotmail.com")){
                    Intent intencion = new Intent(getApplication(), Pantalla_Secundaria_Jefe.class);
                    startActivity(intencion);
                }
                else {
                    iniciarsesion(emailSesion,passwordSesion);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(authStateListener);
    }
}
