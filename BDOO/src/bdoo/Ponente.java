/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdoo;

/**
 *
 * @author diurno2020
 */
public class Ponente {
    
    private String nif, nombre, email;
    private float cache;

    public Ponente(String a, String no, String e, float c) {
        this.nif=a;
        this.nombre=no;
        this.email=e;
        this.cache=c;
    }
    public Ponente(String a, String no, String e) {
        this.nif=a;
        this.nombre=no;
        this.email=e;
    }

    /**
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cache
     */
    public float getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(float cache) {
        this.cache = cache;
    }
    
    public String toString(){
        if(this.cache!=-1){
            return this.nif+" "+this.nombre+" "+this.email+" Caché:"+this.cache;
        }else{
            return this.nif+" "+this.nombre+" "+this.email;
        }
    }
    
}
