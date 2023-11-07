
package com.mycompany.club;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Usuario
 */
public class Club {

    public static List<Socio> socios = new ArrayList<>();

    public static void main(String[] args) {
        
        //Menu
        while (true){
            System.out.println("Menu de opciones");
            System.out.println("1. Afiliar socio.");
            System.out.println("2. Registrar persona autorizada.");
            System.out.println("3. Pagar factura.");
            System.out.println("4. Aumentar fondos.");
            System.out.println("5. Eliminar socio.");
            System.out.println("Seleccione una opcion: ");
            
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    afiliarSocio();
                    break;
                case 2:
                    mostrarSocios();
                    break;
                case 3:
                   
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
           
        }
        
    }
    private static void afiliarSocio(){
        if (socios.size() < 35){
            System.out.println("");
            Scanner scanner = new Scanner(System.in);
            
            Socio socio = new Socio();
            
            //Creaciopn de id universal
            socio.setId(UUID.randomUUID().toString());
            
            System.out.print("\nPor favor, ingrese el nombre del socio: ");
            socio.setFirst_name(scanner.next());
            
            System.out.print("\nPor favor, ingrese los apellidos del socio: ");
            socio.setLast_name(scanner.next());
            
            System.out.print("\nPor favor, ingrese la cedula del socio: ");
            socio.setDocument_number(scanner.nextInt());
            
            if (checkIfDocumentNumberExist(socio.getDocument_number())){
                System.out.println("Ya existe un usuario con esta identificacion.");
                return;   
            }
            
            System.out.print("\nPor favor, ingrese el tipo de documento: ");
            socio.setDocument_type(scanner.next());
            
            System.out.print("\nPor favor, ingrese la edad: ");
            socio.setAge(scanner.next());
            
            //añadir el paciente  a la lista de pacientes
            socios.add(socio);
            
            
            System.out.print("\nSocio agregado exitosamente.\n");
        } else{
            System.out.println("Ya no se pueden agregar mas socios" );
        }
        
    } 
    public static boolean checkIfDocumentNumberExist(int documentNumber){
        for (Socio socio : socios) {
            if(socio.getDocument_number() == documentNumber){
                return true;    
            }
        }   
        return false;
    }
            
    public static void mostrarSocios() {
        for (Socio socio : socios) {
            System.out.println(socio.getFirst_name() + " - " + socio.getLast_name() + " - " + socio.getDocument_number());
        }
        System.out.println("\n\n");
    } 
            
       
      
}

