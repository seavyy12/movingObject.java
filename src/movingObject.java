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

   
   float distancia(){
    if(head == null) {
        return 0;
    }
    Nodo auxNodo = head;
    float totalDistance = 0;

    while(auxNodo.next != null) {
        String[] pos1 = auxNodo.posicion.split(",");
        String[] pos2 = auxNodo.next.posicion.split(",");
        float x1 = Float.parseFloat(pos1[0]);
        float y1 = Float.parseFloat(pos1[1]);
        float x2 = Float.parseFloat(pos2[0]);
        float y2 = Float.parseFloat(pos2[1]);
        totalDistance += Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        auxNodo = auxNodo.next;
    }

    return totalDistance;
   }
 
   
   boolean Intersecta(movingObject obj2) {
      
    if(this.head == null || obj2.head == null) {
        return false;
    }
    
    Nodo auxNodo1 = this.head;
      Nodo auxNodo2 = obj2.head;

      while(auxNodo1 != null && auxNodo2 != null) {
        
        if(auxNodo1.posicion.equals(auxNodo2.posicion)) {
            return true;
        }

        if(auxNodo1.t < auxNodo2.t) {
            auxNodo1 = auxNodo1.next;
        } else {
            auxNodo2 = auxNodo2.next;
        }
          

      }
        return false;

   }
// falta implementar esta funcion
   boolean IntersectaRangeST() {
    return false;
   }

}





