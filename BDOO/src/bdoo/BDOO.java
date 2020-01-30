package bdoo;

/**
 *
 * @author diurno2020
 */
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.ext.DatabaseFileLockedException;
import com.db4o.ext.DatabaseReadOnlyException;
import com.db4o.ext.Db4oIOException;
import com.db4o.ext.IncompatibleFileFormatException;
import com.db4o.ext.OldFormatException;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.List;

public class BDOO {

    
    public static void main(String[] args) {
        CreaFichero();
    }

    public static void CreaFichero() throws DatabaseFileLockedException, OldFormatException, IncompatibleFileFormatException, Db4oIOException, DatabaseReadOnlyException {
        ObjectContainer db= Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "congreso.db4o");
        
        try{
            //almacenarPonentes(db);
            //consultarPonentes(db);
            //consultaPonente200(db);
            //consultaCorreo(db);
            //consultaCache(db);
            //consultarPonentePorNombre(db);
            //consultaSODAponentes(db);
            //consultaSODAponentescache(db);
            //consultaCacheMayorMenor(db);
            //consultarPonenteNQcache200(db);
            consultaCacheNombre(db);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            db.close();
        }
    }
    public static void consultarPonentes (ObjectContainer db){
        Ponente p= new Ponente(null,null,null,0);
        ObjectSet res=db.queryByExample(p);
        mostrarConsulta(res);
    }
    
    public static void mostrarConsulta(ObjectSet resul){
        System.out.println("Recuperados: "+resul.size()+" Objetos");
        while(resul.hasNext()){
            System.out.println(resul.next());
        }
    }
    
    public static void consultaPonente200(ObjectContainer db){
        Ponente p= new Ponente(null,null,null,200);
        ObjectSet res=db.queryByExample(p);
        mostrarConsulta(res);
    }
    public static void consultarPonentePorNombre(ObjectContainer db){
        String nom="Pedro Sanchez";
        Ponente p= new Ponente(null,nom,null,0);
        ObjectSet res=db.queryByExample(p);
        mostrarConsulta(res);
    }
    public static void consultaSODAponentes(ObjectContainer db){
        Query query=db.query();
        //indica la clase a la que se hara la restriccion
        query.constrain(Ponente.class);
        ObjectSet result=query.execute();
        mostrarConsulta(result);
    }
    public static void consultaSODAponentescache(ObjectContainer db){
        Query query=db.query();
        //indica la clase a la que se hara la restriccion
        query.constrain(Ponente.class);
        query.descend("cache").constrain(100);
        ObjectSet result=query.execute();
        mostrarConsulta(result);
    }
    public static void consultaCorreo(ObjectContainer db){
        Ponente p= new Ponente(null,null,null,0);
        ObjectSet res=db.queryByExample(p);
        System.out.println("Recuperados: "+res.size()+" Objetos");
        while(res.hasNext()){
            p=(Ponente)res.next();
            if(p.getEmail().contains("gmail"))
            System.out.println(p.toString());
        }
    }
    public static void consultaCacheNombre(ObjectContainer db){
        String nombre="Antonio Camacho";
        Ponente p= new Ponente(null,null,null,0);
        Ponente p1= new Ponente(null,nombre,null,0);
        ObjectSet res=db.queryByExample(p);
        System.out.println("Recuperados: "+res.size()+" Objetos");
        ObjectSet res1=db.queryByExample(p1);
        while(res.hasNext()){
            p=(Ponente)res.next();
            p1=(Ponente)res1.next();
            if(!(p.getNombre().contains(p1.getNombre()))&& p.getCache()<p1.getCache()){
                System.out.println(p.toString());
            }           
        }   
    }
    
    public static void consultaCacheMayorMenor(ObjectContainer db){
        Ponente p=null;
        Query query=db.query();
        //indica la clase a la que se hara la restriccion
        query.constrain(Ponente.class);
        Constraint constra1=query.descend("cache").constrain(200).smaller().equal();
        query.descend("cache").constrain(50).greater().and(constra1);
        ObjectSet result=query.execute();
        mostrarConsulta(result);
    }
    public static void consultaCache(ObjectContainer db){
        
        Ponente p= new Ponente(null,null,null,0);
        ObjectSet res=db.queryByExample(p);
        while(res.hasNext()){
            p=(Ponente)res.next();
            if(p.getCache()>=100 && p.getCache()<300){
            System.out.println(p.toString());
           
            }
        }
        
    }
    public static void consultarPonenteNQcache200(ObjectContainer db){
        List res=db.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente p){
                return p.getCache()<=200 && p.getCache()>50;
            }
            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        mostrarConsulta((ObjectSet)res);
    }
    
    public static void almacenarPonentes(ObjectContainer db){
        Ponente p1=new Ponente("11A","Antonio Camacho","acamacho@gmail.com",300);
        Ponente p2=new Ponente("22B","Isabel Perez","iperez@gmail.com",100);
        Ponente p3=new Ponente("33C","Ana Navarro","ananavarro@gmail.com",200);
        Ponente p4=new Ponente("44D","Pedro Sanchez","psanchez@gmail.com",90);
        Ponente p5=new Ponente("11A","Antonio Camacho","acamacho@gmail.com",300);
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);
        db.store(p5);
    }
}
