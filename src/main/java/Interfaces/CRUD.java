
package Interfaces;

import modelo.alumno_cls;


public interface CRUD {
    public String  listar();
    public alumno_cls list(int id);
    public boolean add(alumno_cls per);
    public boolean edit(alumno_cls per);
    public boolean eliminar(int id);
}
