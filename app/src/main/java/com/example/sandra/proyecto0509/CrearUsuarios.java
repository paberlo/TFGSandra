package com.example.sandra.proyecto0509;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.regex.Pattern;

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
        /*databaseReference= FirebaseDatabase.getInstance().getReference();
        String userID=firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("USUARIOS").child(userID).child("Contadores").setValue("3");*/
        /*String uid=firebaseAuth.getUid();
        Log.i("UID",uid);*/

        //Referenciamos los views
        TextEmail = (EditText) findViewById(R.id.ed_email);
        TextPassword = (EditText) findViewById(R.id.et_password);

        btnRegistrar = (Button) findViewById(R.id.btn_register);
        //attaching listener to button
        btnRegistrar.setOnClickListener(this);


        progressDialog = new ProgressDialog(this);

        btn_registrado=findViewById(R.id.btn_yaregistrado);
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


    /*private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Introduce el email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Introduce la contraseña",Toast.LENGTH_LONG).show();
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

                            Toast.makeText(CrearUsuarios.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(CrearUsuarios.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }*/
    /*private void loguearUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//(precio.equals(""))
            Toast.makeText(this, "Introduce el email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Introduce la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando consulta...");
        progressDialog.show();

        //loguear usuario
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(CrearUsuarios.this, "Bienvenido: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent(getApplication(), PantallaSecundaria.class);
                            intencion.putExtra(PantallaSecundaria.user, user);
                            startActivity(intencion);


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(CrearUsuarios.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
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
                    Toast.makeText(CrearUsuarios.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                        Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(CrearUsuarios.this,"No se pudo registrar el usuario la contraseña tiene que tener al menos 6 caracteres con algun numero ",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void iniciarsesion(String email, String password){
        progressDialog.setMessage("Realizando consulta...");
        progressDialog.show();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(CrearUsuarios.this,"Ha iniciado sesion  el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                    Intent intencion = new Intent(getApplication(), CrearUsuarios.class);
                    startActivity(intencion);
                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                        Toast.makeText(CrearUsuarios.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(CrearUsuarios.this,"No se pudo iniciar sesion ",Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:
        switch (view.getId()) {

            case R.id.btn_register:
                //Invocamos al método:
                String emailInicio=TextEmail.getText().toString();
                String passwordInicio=TextPassword.getText().toString();
                //validarPassword();
                registrar(emailInicio,passwordInicio);
               // validarDatos();

                //validarEmail();

                break;
            case R.id.btn_yaregistrado:
                //loguearUsuario();
                String emailSesion=TextEmail.getText().toString();
                String passwordSesion=TextPassword.getText().toString();
                iniciarsesion(emailSesion,passwordSesion);
                break;
        }
    }

    private boolean validarEmail(){
        String emailInput=TextEmail.getEditableText().toString().trim();

        if(emailInput.isEmpty()){
            TextEmail.setError("Email no puede estar vacio");
            return false;
        }
        else if(Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            TextEmail.setError("Introduce un email valido");
            return false;
        }
        else{
            TextEmail.setError(null);
            return true;
        }
    }

    private boolean validarPassword(){
        String passInput=TextPassword.getEditableText().toString().trim();
        if(passInput.isEmpty()){
            TextPassword.setError("Contraseña no puede ser vacio");
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(passInput).matches()){
            TextPassword.setError("La contraseña debe ser al menos 6 caracteres y contener algun numero");
            return false;
        }
        else{
            TextPassword.setError(null);
            return true;
        }
    }

    /*private boolean comprobarpassword(String pass){
        Pattern patron = Pattern.compile("^[a-zA-Z0-9 ]+$");
        if (!patron.matcher(pass).matches() || pass.length() > 10) {
            TextPassword.setError("La contraseña tiene que tener al menos 6 caracteres");
            return false;
        } else {
            TextPassword.setError(null);
        }

        return true;
    }

    private void validarDatos(){
        String pwd = TextPassword.getEditableText().toString();
        boolean a=comprobarpassword(pwd);
    }*/

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
