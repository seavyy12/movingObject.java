public class movingObject {
    private int oid;
    private Nodo head;

    public movingObject(int oid) {
        this.oid = oid;
        this.head = null;
    }

    void insertar(int t, String posicion) {
        if (head == null) {
            head = new Nodo(t, posicion);
        } else {
            Nodo auxNodo = head;
            Nodo newNode = new Nodo(t, posicion);
            while (auxNodo.next != null) {
                if (auxNodo.t > t && auxNodo.next.t < t) {
                    newNode.next = auxNodo.next;
                    auxNodo.next = newNode;
                    return;
                }
                auxNodo = auxNodo.next;
            }
        }
    }

    void Eliminar(int t) {
        if (head == null) {
            return;
        }

        if (head.t == t) {
            head = head.next;
            return;
        }

        Nodo auxNodo = head.next;
        Nodo prevNodo = head;
        while (auxNodo != null) {
            if (auxNodo.t == t) {
                prevNodo.next = auxNodo.next;
                return;
            }
            prevNodo = auxNodo;
            auxNodo = auxNodo.next;
        }
    }


    float distancia() {
        if (head == null) {
            return 0;
        }
        Nodo auxNodo = head;
        float totalDistance = 0;

        while (auxNodo.next != null) {
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

        if (this.head == null || obj2.head == null) {
            return false;
        }

        Nodo auxNodo1 = this.head;
        Nodo auxNodo2 = obj2.head;

        while (auxNodo1 != null && auxNodo2 != null) {

            if (auxNodo1.posicion.equals(auxNodo2.posicion)) {
                return true;
            }

            if (auxNodo1.t < auxNodo2.t) {
                auxNodo1 = auxNodo1.next;
            } else {
                auxNodo2 = auxNodo2.next;
            }


        }
        return false;

    }

    // implementando...
    boolean IntersectaRangeST(String[] r, int[] t) {
        if (this.head == null || r == null || t == null || r.length != 2 || t.length != 2) {
            return false;
        }

        int t1=t[0];
        int t2=t[1];

        String[]coordenada_1 = r[0].split(",");
        String[]coordenada_2 = r[1].split(",");
        float rx1=Float.parseFloat(coordenada_1[0]);
        float ry1=Float.parseFloat(coordenada_1[1]);
        float rx2=Float.parseFloat(coordenada_2[0]);
        float ry2=Float.parseFloat(coordenada_2[1]);

        float minimo_x = Math.min(rx1, rx2);
        float maximo_x = Math.max(rx1, rx2);
        float minimo_y = Math.min(ry1, ry2);
        float maximo_y = Math.max(ry1, ry2);

        Nodo auxNodo = head;

        if (auxNodo.next == null) {
            if (auxNodo.t >= t1 && auxNodo.t <= t2) {
                String[] pos = auxNodo.posicion.split(",");
                float x = Float.parseFloat(pos[0]);
                float y = Float.parseFloat(pos[1]);
                if (x >= minimo_x && x <= maximo_x && y >= minimo_y && y <= maximo_y) {
                    return true;
                }
            }
            return false;
        }
        //recorrer nodo por nodo
        while (auxNodo.next != null) {
            if (auxNodo.t <= t2 && auxNodo.next.t >= t1) {

                String[] posicion_1 = auxNodo.posicion.split(",");
                String[] posicion_2 = auxNodo.next.posicion.split(",");
                float x1 = Float.parseFloat(posicion_1[0]);
                float y1 = Float.parseFloat(posicion_1[1]);
                float x2 = Float.parseFloat(posicion_2[0]);
                float y2 = Float.parseFloat(posicion_2[1]);
                //ver si algun extremo cayo dentro del rectangulo
                boolean p1_dentro = (x1 >= minimo_x && x1 <= maximo_x && y1 >= minimo_y && y1 <= maximo_y);
                boolean p2_dentro = (x2 >= minimo_x && x2 <= maximo_x && y2 >= minimo_y && y2 <= maximo_y);
                if (p1_dentro ||p2_dentro) {
                    return true;
                }
                float[][] bordes = {
                        {minimo_x, maximo_y, maximo_x, maximo_y},
                        {minimo_x, minimo_y, maximo_x, minimo_y},
                        {minimo_x, minimo_y, minimo_x, maximo_y},
                        {maximo_x, minimo_y, maximo_x, maximo_y}
                };
                //el for da 4 vueltas por las 4 paredes del rectangulo
                for (int i = 0; i < 4; i++) {
                    float p3_x = bordes[i][0];
                    float p3_y = bordes[i][1];
                    float p4_x = bordes[i][2];
                    float p4_y = bordes[i][3];

                    float s1_x = x2 - x1;
                    float s1_y = y2 - y1;
                    float s2_x = p4_x - p3_x;
                    float s2_y = p4_y - p3_y;

                    float denominador = (-s2_x * s1_y + s1_x * s2_y);
                    if (denominador != 0) {
                        float s = (-s1_y * (x1 - p3_x) + s1_x * (y1 - p3_y)) / denominador;
                        float t_int = ( s2_x * (y1 - p3_y) - s2_y * (x1 - p3_x)) / denominador;
                        if (s >= 0 && s <= 1 && t_int >= 0 && t_int <= 1) {
                            return true;
                        }
                    }
                }
            }
            auxNodo = auxNodo.next;
        }
        return false;
    }
}





