public class movingObject {
    private int oid;
    private Nodo head;

    public movingObject(int oid) {
        this.oid = oid;
        this.head = null;
    }

    void insertar(int t, String posicion) {
        if(head == null) {
            head = new Nodo(t, posicion);
        } else {
            Nodo auxNodo = head;
            Nodo newNode = new Nodo(t, posicion);
            while(auxNodo.next != null) {
                if(auxNodo.t > t && auxNodo.next.t < t) {
                    auxNodo.next = newNode;
                    newNode.next = auxNodo.next;
                    return;
                }
                auxNodo = auxNodo.next;
            }
        }
    }

    void Eliminar(int t) {
        if(head == null) {
            return;
        }

        if(head.t == t) {
            head = head.next;
            return;
        }

        Nodo auxNodo = head.next;
        Nodo prevNodo = head;
        while(auxNodo != null) {
            if(auxNodo.t == t) {
                prevNodo.next = auxNodo.next;
                return;
            }
            prevNodo = auxNodo;
            auxNodo = auxNodo.next;
        }
    }

    //falta implementar;
   float distancia(){
    return 0;
   }
 // implementar esta funcion
   boolean Intersecta(movingObject obj2) {
    return false;
   }
// falta implementar esta funcion
   boolean IntersectaRangeST() {
    return false;
   }
}





