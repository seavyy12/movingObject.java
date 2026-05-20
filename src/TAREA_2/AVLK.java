package TAREA_2;

import TAREA_1.Nodo;

public class AVLK {
    private NodoAVLK raiz;
    private boolean crecio;
    AVLK() {
        raiz = null;
        crecio = false;
    }

   class NodoAVLK{
           NodoAVLK lchild;
           NodoAVLK rchild;
           int key;
           int peso;
           short balance;

        public NodoAVLK(int key, int peso, NodoAVLK lchild, NodoAVLK rchild ) {
            this.key = key;
            this.peso = peso;
            this.balance = 0;
            this.lchild = lchild;
            this.rchild = rchild;
        }

   }

// insertar listo 
   void insertar(int k){
      raiz = insertarAVLK(raiz, k);
      crecio=false;
   }

   private NodoAVLK insertarAVLK(NodoAVLK a, int k){
    if( a== null){ crecio = true; return new NodoAVLK(k, 1, null, null); }
    else {
        if(k<a.key){
            a.lchild=insertarAVLK(a.lchild, k);
            
            if(crecio){
                switch(a.balance){
                    case 1: crecio=false; a=balanceaIzq(a); break;
                    case 0: a.balance=1; break;
                    case -1: crecio = false; a.balance=0; break;
                }

            }

        }
        else{
            a.rchild=insertarAVLK(a.rchild, k);
            if(crecio){
                switch(a.balance){
                    case 1: crecio=false; a.balance=0; break;
                    case 0: a.balance=-1; break;
                    case -1: crecio = false; a=BalanceDerecha(a); break;
                }

            }
        }
    }

    a.peso = obtenerPeso(a.lchild) + obtenerPeso(a.rchild) + 1;
    return a;
   }

   // eliminar listo
   void eliminar(int k){
        raiz = eliminarAVLK(raiz, k);
        crecio=false;
    }

    private NodoAVLK eliminarAVLK(NodoAVLK a , int elemento){
        if(elemento == a.key){
            if(a.lchild == null && a.rchild == null){
                crecio= true;
                return null;
            }
            else if(a.lchild == null){
              crecio = true;
              return a.rchild;
            }
            else{
                a.key = buscarMayorIzq(a.lchild);
                a.lchild = eliminarAVLK(a.lchild, a.key);
                if(crecio){a=BalanceDerecha(a);}
            }

        }
        
        else if(a.key > elemento){
            a.lchild = eliminarAVLK(a.lchild, elemento);
            if(crecio){a=BalanceDerecha(a);}
        }
        else{
            a.rchild = eliminarAVLK(a.rchild, elemento);
            if(crecio){a=BalanceIzquierda(a);}
        }
        return a;
    }

   
// buscar listo
   boolean buscar(int k){
       if(k<=0) return false;
      return buscarAVLK(raiz, k);
   }

   private boolean buscarAVLK(NodoAVLK nodo, int k) {
    if(nodo==null) return false;
    if(nodo.key==k) return true;

    if(k<nodo.key){
        return buscarAVLK(nodo.lchild, k);
    }
    else{
        return buscarAVLK(nodo.rchild, k);
    }

   }




   int i_esimo(int i){// falta terminar
       if(i<1 || i>obtenerPeso(raiz)) return -1;
       return i_esimoAVLK(raiz, i);
   }

    private int i_esimoAVLK(NodoAVLK nodo, int i) {
    if(nodo==null) return -1;

    int pesoIzq = obtenerPeso(nodo.lchild);

    if(pesoIzq== i-1) return nodo.key;

    if(pesoIzq>=i) return i_esimoAVLK(nodo.lchild, i);

    else return i_esimoAVLK(nodo.rchild, i-pesoIzq-1);

    }

    // verifico si el peso es null o no , me facilita mucho el trabajo :)
   private int obtenerPeso(NodoAVLK n) {
    if (n == null) return 0;
    return n.peso;
}
  
  
  
   private NodoAVLK BalanceDerecha(NodoAVLK a){
       if(a.rchild.balance == -1){
         a.balance= a.rchild.balance = 0;
         a= roteIzq(a);
       }
       else{
        switch(a.rchild.lchild.balance){
            case 1:
                a.balance = 0;
                a.rchild.balance = -1;
                break;
            case 0:
                a.balance = a.rchild.balance = 0;
                break;
            case -1:
                a.balance = 1;
                a.rchild.balance = 0;
                break;
        }
        a.rchild.lchild.balance = 0;
        a=roteDerIzq(a);
       }
       return a;
    }

    private NodoAVLK balanceaIzq(NodoAVLK a) {
    if(a.lchild.balance == 1) {
        a.balance = a.lchild.balance = 0;
        a = roteDer(a);
    }
    else {
        switch(a.lchild.rchild.balance) {
            case 1:  a.balance = -1; a.lchild.balance = 0;  break;
            case 0:  a.balance = a.lchild.balance = 0;      break;
            case -1: a.balance = 0;  a.lchild.balance = 1;  break;
        }
        a.lchild.rchild.balance = 0;
        a = roteIzqDer(a);
    }
    return a;
    }

    private NodoAVLK BalanceIzquierda( NodoAVLK a){
       switch(a.balance){
        case 1: 
        if(a.lchild.balance != -1){
            a = roteDer(a);
            if(a.balance==0){
            a.balance =-1;
            a.rchild.balance = 1;
            crecio = false;
            }
            else{
                a.balance = a.rchild.balance = 0;
            }
        }
        else{
            a= roteIzq(a);
            a.rchild.balance = a.balance == 1 ? (short) -1 : (short) 0;
            a.lchild.balance = a.balance == -1 ? (short) 1 : (short) 0;
            a.balance = 0;
        }
        break;
        case 0: a.balance=1;
        crecio= false;
        break;
        case -1: a.balance=0;
        break;
       }
       return a;
    }


    




//rotacion izquierda;
   private NodoAVLK roteIzq( NodoAVLK a){
    NodoAVLK y = a.rchild;
    a.rchild = y.lchild;
    y.lchild = a;

    a.peso = obtenerPeso(a.lchild) + obtenerPeso(a.rchild) + 1;
    y.peso = obtenerPeso(y.lchild) + obtenerPeso(y.rchild) + 1;
    
    return y;
   }


   //rotacion derecha;
   private NodoAVLK roteDer(NodoAVLK a){
    NodoAVLK y = a.lchild;
    a.lchild = y.rchild;
    y.rchild = a;

    a.peso = obtenerPeso(a.lchild) + obtenerPeso(a.rchild) + 1;
    y.peso = obtenerPeso(y.lchild) + obtenerPeso(y.rchild) + 1;
    return y;
    }
// rotacion derecha-izquierda
    private NodoAVLK roteDerIzq(NodoAVLK a){
        a.rchild = roteDer(a.rchild);
        return roteIzq(a);
    }
// rotacion izquierda-derecha
    private NodoAVLK roteIzqDer(NodoAVLK a){
        a.lchild = roteIzq(a.lchild);
        return roteDer(a);
    }
          

  private int buscarMayorIzq(NodoAVLK lchild) {
       if(lchild.rchild == null){
           return lchild.key;
       }
       else{
           return buscarMayorIzq(lchild.rchild);
       }
    }
             

} 

