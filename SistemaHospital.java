package practica2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Medico {
    String numeroColegiatura;
    String nombre;
    String especialidad;

    public Medico(String numeroColegiatura, String nombre, String especialidad) {
        this.numeroColegiatura = numeroColegiatura;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
}

class Paciente {
    String DNI;
    String nombre;
    String direccion;
    double peso;
    double temperatura;
    Medico medico;

    public Paciente(String DNI, String nombre, String direccion, double peso, double temperatura, Medico medico) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.direccion = direccion;
        this.peso = peso;
        this.temperatura = temperatura;
        this.medico = medico;
    }
}

public class SistemaHospital {

    static ArrayList<Paciente> listaPacientes = new ArrayList<>();
    static ArrayList<Medico> listaMedicos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            System.out.println("\n--- Sistema de Gestión Hospitalaria ---");
            System.out.println("1. Registrar datos del paciente");
            System.out.println("2. Eliminar datos del paciente");
            System.out.println("3. Modificar datos del paciente");
            System.out.println("4. Mostrar el peso que mas se repite");
            System.out.println("5. Mostrar la cantidad de pacientes por sus pesos");
            System.out.println("6. Mostrar el peso mayor y menor");
            System.out.println("7. Mostrar la cantidad de personas por rango de pesos");
            System.out.println("8. Mostrar la lista de pacientes ordenados por sus apellidos");
            System.out.println("9. Buscar al doctor de un paciente");
            System.out.println("10. Buscar los doctores por su especialidad");
            System.out.println(" Apretar 0 para salir del programa");
            System.out.print("Ingrese la opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarPaciente();
                    break;
                case 2:
                    eliminarPaciente();
                    break;
                case 3:
                    modificarPaciente();
                    break;
                case 4:
                    mostrarPesoMasRepetido();
                    break;
                case 5:
                    mostrarCantidadPorPeso();
                    break;
                case 6:
                    mostrarPesoMayorMenor();
                    break;
                case 7:
                    mostrarCantidadPorRangoPesos();
                    break;
                case 8:
                    mostrarListaOrdenada();
                    break;
                case 9:
                    buscarDoctorDePaciente();
                    break;
                case 10:
                    buscarDoctoresPorEspecialidad();
                    break;
                case 0:
                    System.out.println("Saliendo del programa, hasta pronto");
                    break;
                default:
                    System.out.println("Opción no valida, Intentelo de nuevo.");
                    break;
            }
        } while (opcion != 0);
    }

    private static void registrarPaciente() {
        System.out.println("--- Registro del paciente ---");
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Peso: ");
        double peso = scanner.nextDouble();
        System.out.print("Temperatura: ");
        double temperatura = scanner.nextDouble();
        scanner.nextLine();      
    }

    private static void eliminarPaciente() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para eliminar.");
            return;
        }

        mostrarPacientes();

        System.out.print("Ingrese la posición del paciente a eliminar: ");
        int posicion = scanner.nextInt();

        if (posicion < 1 || posicion > listaPacientes.size()) {
            System.out.println("Posicion no valida, Intentelo de nuevo.");
            return;
        }

        listaPacientes.remove(posicion - 1);
        System.out.println("El paciente ha sido eliminado con exito.");
    }

    private static void modificarPaciente() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para modificar.");
            return;
        }

        mostrarPacientes();

        System.out.print("Ingrese la posición del paciente a modificar: ");
        int posicion = scanner.nextInt();

        if (posicion < 1 || posicion > listaPacientes.size()) {
            System.out.println("Posición no válida. Inténtelo de nuevo.");
            return;
        }

        Paciente pacienteSeleccionado = listaPacientes.get(posicion - 1);

        System.out.println("\n--- Modificación de Paciente ---");
        System.out.print("Nuevo DNI (actual: " + pacienteSeleccionado.DNI + "): ");
        pacienteSeleccionado.DNI = scanner.nextLine();
        System.out.print("Nuevo Nombre (actual: " + pacienteSeleccionado.nombre + "): ");
        pacienteSeleccionado.nombre = scanner.nextLine();
        System.out.print("Nueva Dirección (actual: " + pacienteSeleccionado.direccion + "): ");
        pacienteSeleccionado.direccion = scanner.nextLine();
        System.out.print("Nuevo Peso (actual: " + pacienteSeleccionado.peso + "): ");
        pacienteSeleccionado.peso = scanner.nextDouble();
        System.out.print("Nueva Temperatura (actual: " + pacienteSeleccionado.temperatura + "): ");
        pacienteSeleccionado.temperatura = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Paciente modificado con éxito.");
    }

    private static void mostrarPesoMasRepetido() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para mostrar el peso más repetido.");
            return;
        }

        Map<Double, Integer> pesoFrecuencia = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.peso;
            pesoFrecuencia.put(peso, pesoFrecuencia.getOrDefault(peso, 0) + 1);
        }

        double pesoMasRepetido = 0;
        int maxFrecuencia = 0;

        for (Map.Entry<Double, Integer> entry : pesoFrecuencia.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                pesoMasRepetido = entry.getKey();
            }
        }

        System.out.println("El peso más repetido es: " + pesoMasRepetido);
    }

    private static void mostrarCantidadPorPeso() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para mostrar la cantidad por peso.");
            return;
        }

        Map<Double, Integer> pesoFrecuencia = new HashMap<>();

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.peso;
            pesoFrecuencia.put(peso, pesoFrecuencia.getOrDefault(peso, 0) + 1);
        }

        for (Map.Entry<Double, Integer> entry : pesoFrecuencia.entrySet()) {
            System.out.println("Peso: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }

    private static void mostrarPesoMayorMenor() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para mostrar el peso mayor y menor.");
            return;
        }

        double pesoMayor = Double.MIN_VALUE;
        double pesoMenor = Double.MAX_VALUE;

        for (Paciente paciente : listaPacientes) {
            if (paciente.peso > pesoMayor) {
                pesoMayor = paciente.peso;
            }
            if (paciente.peso < pesoMenor) {
                pesoMenor = paciente.peso;
            }
        }

        System.out.println("Peso Mayor: " + pesoMayor);
        System.out.println("Peso Menor: " + pesoMenor);
    }

    private static void mostrarCantidadPorRangoPesos() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para mostrar la cantidad por rango de pesos.");
            return;
        }

        int rango1 = 0, rango2 = 0, rango3 = 0, rango4 = 0;

        for (Paciente paciente : listaPacientes) {
            double peso = paciente.peso;
            if (peso >= 40 && peso < 60) {
                rango1++;
            } else if (peso >= 60 && peso < 80) {
                rango2++;
            } else if (peso >= 80 && peso < 100) {
                rango3++;
            } else if (peso >= 100 && peso <= 120) {
                rango4++;
            }
        }

        System.out.println("Rango 40-60: " + rango1 + " personas");
        System.out.println("Rango 60-80: " + rango2 + " personas");
        System.out.println("Rango 80-100: " + rango3 + " personas");
        System.out.println("Rango 100-120: " + rango4 + " personas");
    }

    private static void mostrarListaOrdenada() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para mostrar la lista ordenada.");
            return;
        }

        Collections.sort(listaPacientes, (p1, p2) -> p1.nombre.compareTo(p2.nombre));

        System.out.println("--- Lista de Pacientes Ordenados por Apellidos ---");
        for (Paciente paciente : listaPacientes) {
            System.out.println("Nombre: " + paciente.nombre + ", Apellido: " + paciente.nombre.split(" ")[1]);
        }
    }

    private static void buscarDoctorDePaciente() {
        if (listaPacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados para buscar al doctor.");
            return;
        }

        mostrarPacientes();

        System.out.print("Ingrese la posición del paciente para buscar al doctor: ");
        int posicion = scanner.nextInt();

        if (posicion < 1 || posicion > listaPacientes.size()) {
            System.out.println("Posición no válida. Inténtelo de nuevo.");
            return;
        }

        Paciente pacienteSeleccionado = listaPacientes.get(posicion - 1);

        System.out.println("El paciente " + pacienteSeleccionado.nombre + " fue atendido por el doctor " +
                pacienteSeleccionado.medico.nombre);
    }

    private static void buscarDoctoresPorEspecialidad() {
        if (listaMedicos.isEmpty()) {
            System.out.println("No hay médicos registrados para buscar por la especialidad.");
            return;
        }

        System.out.print("Ingrese la especialidad a buscar: ");
        String especialidadBuscada = scanner.nextLine();

        boolean encontrado = false;

        for (Medico medico : listaMedicos) {
            if (medico.especialidad.equalsIgnoreCase(especialidadBuscada)) {
                System.out.println("Doctor: " + medico.nombre + ", Especialidad: " + medico.especialidad);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron doctores con la especialidad especifica.");
        }
    }

    private static void mostrarMedicos() {
        System.out.println("--- Lista de Médicos ---");
        for (int i = 0; i < listaMedicos.size(); i++) {
            Medico medico = listaMedicos.get(i);
            System.out.println("[" + (i + 1) + "] " + "Número de Colegiatura: " + medico.numeroColegiatura +
                    ", Nombre: " + medico.nombre + ", Especialidad: " + medico.especialidad);
        }
    }

    private static Medico buscarMedico(String numeroColegiatura) {
        for (Medico medico : listaMedicos) {
            if (medico.numeroColegiatura.equals(numeroColegiatura)) {
                return medico;
            }
        }
        return null;
    }

    private static void mostrarPacientes() {
        System.out.println("--- Lista de Pacientes ---");
        for (int i = 0; i < listaPacientes.size(); i++) {
            Paciente paciente = listaPacientes.get(i);
            System.out.println("[" + (i + 1) + "] " + "DNI: " + paciente.DNI + ", Nombre: " + paciente.nombre +
                    ", Peso: " + paciente.peso + ", Temperatura: " + paciente.temperatura);
        }
    }
}

