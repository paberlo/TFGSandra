package com.example.sandra.proyecto0509;

public class VariablesGlobales
{
    public static float contador=45;
    public static float minimodb=100;
    public static float maximodb=0;
    public static float ultimovalor=contador;
    private static float minimo=0.5f;
    private static float valor=0;

    public static void PonerContadorDB(float valordb)
    {
        if(valordb>ultimovalor)
        {
            valor=valordb-ultimovalor>minimo ? valordb-ultimovalor :minimo;
        }
        else
        {
            valor=valordb-ultimovalor<-minimo ? valordb-ultimovalor:minimo;
        }
        contador=ultimovalor+valor*0.2f;
        ultimovalor=contador;

        if(contador<minimodb)
            minimodb=contador;

        if(contador>maximodb)
            maximodb=contador;
    }
}
