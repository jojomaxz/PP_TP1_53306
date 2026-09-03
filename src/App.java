import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Estudiante> estudiantes = new ArrayList<>();


          //CARGA DE ESTUDIANTES


        boolean continuarEstudiantes = true;

        System.out.println("CARGA DE ESTUDIANTES");
        System.out.println("====================");

        while (continuarEstudiantes) {

            System.out.print(
                    "Ingrese el número de legajo del estudiante: "
            );

            String legajo = scanner.nextLine();

            System.out.print(
                    "Ingrese el nombre completo del estudiante: "
            );

            String nombre = scanner.nextLine();

            Estudiante estudiante =
                    new Estudiante(legajo, nombre);

            estudiantes.add(estudiante);

            System.out.print(
                    "¿Desea registrar otro estudiante? (S/N): "
            );

            String respuesta =
                    scanner.nextLine().trim().toLowerCase();

            continuarEstudiantes =
                    respuesta.equals("s")
                            || respuesta.equals("si")
                            || respuesta.equals("sí");
        }



          //CARGA DE EVENTOS


        boolean continuarEventos = true;
        int idEvento = 1;

        System.out.println();
        System.out.println("CARGA DE EVENTOS");
        System.out.println("================");

        while (continuarEventos) {

            System.out.print("Ingrese el nombre del evento: ");
            String titulo = scanner.nextLine();

            System.out.print("Indique el importe base: ");
            double costoBase = scanner.nextDouble();
            scanner.nextLine();

            System.out.print(
                    "¿El evento tendrá un costo para los participantes? (S/N): "
            );

            String respuesta =
                    scanner.nextLine().trim().toLowerCase();

            boolean tieneCosto =
                    respuesta.equals("s")
                            || respuesta.equals("si")
                            || respuesta.equals("sí");

            boolean esGratuito = !tieneCosto;

            EventoUniversitario evento =
                    new EventoUniversitario(
                            "EVT-" + idEvento,
                            titulo,
                            costoBase,
                            esGratuito
                    );



             //ASIGNACIÓN DE SALA


            System.out.print(
                    "Ingrese el nombre de la sala en la que se realizará el evento: "
            );

            String nombreSala = scanner.nextLine();

            Sala sala =
                    new Sala(idEvento, nombreSala);

            evento.asignarSala(sala);



             //CARGA DE ACTIVIDADES


            System.out.println();
            System.out.println(
                    "CARGA DE ACTIVIDADES PARA EL EVENTO "
                            + evento.getTitulo()
            );

            System.out.println(
                    "=============================================="
            );

            boolean continuarActividades = true;
            int idActividad = 1;

            while (continuarActividades) {

                System.out.print(
                        "Ingrese el nombre de la actividad: "
                );

                String tituloActividad =
                        scanner.nextLine();

                System.out.print(
                        "Indique la cantidad máxima de estudiantes: "
                );

                int cupo = scanner.nextInt();
                scanner.nextLine();

                System.out.print(
                        "¿La actividad corresponde a una Charla o Taller? "
                                + "(Charla/Taller): "
                );

                String tipo =
                        scanner.nextLine().trim().toLowerCase();

                evento.crearActividad(
                        idActividad,
                        tituloActividad,
                        cupo,
                        tipo
                );

                idActividad++;

                System.out.print(
                        "¿Desea agregar otra actividad al evento "
                                + evento.getTitulo()
                                + "? (S/N): "
                );

                respuesta =
                        scanner.nextLine().trim().toLowerCase();

                continuarActividades =
                        respuesta.equals("s")
                                || respuesta.equals("si")
                                || respuesta.equals("sí");
            }



              //INSCRIPCIÓN DE ESTUDIANTES


            boolean continuarInscripciones = true;

            System.out.println();
            System.out.println(
                    "INSCRIPCIÓN DE ESTUDIANTES A LAS ACTIVIDADES DEL EVENTO "
                            + evento.getTitulo()
            );

            System.out.println(
                    "=========================================================="
            );

            while (continuarInscripciones) {

                System.out.print(
                        "Ingrese el legajo del estudiante que desea inscribir: "
                );

                String legajo =
                        scanner.nextLine();

                Estudiante estudianteEncontrado = null;

                for (Estudiante estudiante : estudiantes) {

                    if (estudiante.getLegajo().equals(legajo)) {
                        estudianteEncontrado = estudiante;
                        break;
                    }
                }

                if (estudianteEncontrado == null) {

                    System.out.println(
                            "No se encontró un estudiante con ese legajo."
                    );

                } else {

                    System.out.println("Actividades disponibles:");

                    for (Actividad actividad : evento.getActividades()) {

                        System.out.println(
                                actividad.getId()
                                        + " - "
                                        + actividad.getTitulo()
                        );
                    }

                    System.out.print(
                            "Indique el identificador de la actividad: "
                    );

                    int actividadElegida =
                            scanner.nextInt();

                    scanner.nextLine();

                    Actividad actividadEncontrada = null;

                    for (Actividad actividad : evento.getActividades()) {

                        if (actividad.getId() == actividadElegida) {
                            actividadEncontrada = actividad;
                            break;
                        }
                    }

                    if (actividadEncontrada == null) {

                        System.out.println(
                                "No existe una actividad con ese identificador."
                        );

                    } else {

                        actividadEncontrada.inscribir(
                                estudianteEncontrado
                        );
                    }
                }

                System.out.print(
                        "¿Desea realizar una nueva inscripción? (S/N): "
                );

                respuesta =
                        scanner.nextLine().trim().toLowerCase();

                continuarInscripciones =
                        respuesta.equals("s")
                                || respuesta.equals("si")
                                || respuesta.equals("sí");
            }



             //MOSTRAR EVENTO


            System.out.println();
            System.out.println("INFORMACIÓN DEL EVENTO");

            evento.mostrarDatos();



             //OTRO EVENTO


            System.out.print(
                    "¿Desea registrar otro evento? (S/N): "
            );

            respuesta =
                    scanner.nextLine().trim().toLowerCase();

            continuarEventos =
                    respuesta.equals("s")
                            || respuesta.equals("si")
                            || respuesta.equals("sí");

            idEvento++;
        }



         //TOTAL


        System.out.println();

        System.out.println(
                "CANTIDAD TOTAL DE EVENTOS REGISTRADOS: "
                        + EventoUniversitario.getCantidadEventos()
        );

        scanner.close();
    }
}