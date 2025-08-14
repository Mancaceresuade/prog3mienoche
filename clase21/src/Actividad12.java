public class Actividad12 {
    static class Factura {
        int idFactura;
        int idCliente;
        double importe;

        Factura(int idFactura, int idCliente, double importe) {
            this.idFactura = idFactura;
            this.idCliente = idCliente;
            this.importe = importe;
        }
    }

    static class Cliente {
        int idCliente;
        String nombre;

        Cliente(int idCliente, String nombre) {
            this.idCliente = idCliente;
            this.nombre = nombre;
        }
    }

    static class ClienteImporte {
        int idCliente;
        String nombre;
        double sumaImportes;

        ClienteImporte(int idCliente, String nombre, double sumaImportes) {
            this.idCliente = idCliente;
            this.nombre = nombre;
            this.sumaImportes = sumaImportes;
        }

        @Override
        public String toString() {
            return "ClienteImporte{idCliente=" + idCliente + ", nombre='" + nombre + "', sumaImportes=" + sumaImportes + "}";
        }
    }

    public static void main(String[] args) {
        Factura[] facturas = {
            new Factura(1, 100, 150.0),
            new Factura(2, 101, 200.0),
            new Factura(3, 100, 100.0),
            new Factura(4, 102, 300.0),
            new Factura(5, 101, 50.0)
        };

        Cliente[] clientes = {
            new Cliente(100, "Juan"),
            new Cliente(101, "Ana"),
            new Cliente(102, "Luis")
        };

        ClienteImporte[] resultado = new ClienteImporte[clientes.length];

        for (int i = 0; i < clientes.length; i++) {
            Cliente c = clientes[i];
            double suma = 0;
            for (Factura f : facturas) {
                if (f.idCliente == c.idCliente) {
                    suma += f.importe;
                }
            }
            resultado[i] = new ClienteImporte(c.idCliente, c.nombre, suma);
        }

        for (ClienteImporte ci : resultado) {
            System.out.println(ci);
        }
    }
}
