
package com.mycompany.club;

import com.mycompany.club.model.Account;
import com.mycompany.club.model.Membership;
import com.mycompany.club.model.Socio;
import com.mycompany.club.utils.MembershipStatus;
import com.mycompany.club.utils.MembershipType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Usuario
 */
public class Club {

    public static List<Socio> socios = new ArrayList<>();
    public static List<Membership> memberships = new ArrayList<>();
    public static List<Account> accounts = new ArrayList<>();

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
                case 4:
                   
                    break;
                case 5:
                   
                    break;
                case 6:
                   
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
            Membership membership = new Membership();
            Account account = new Account();
            
            //Creaciopn de id universal
            socio.setId(UUID.randomUUID().toString());
            membership.setId(UUID.randomUUID().toString());
            account.setCreated_at(new Date());
            account.setUpdated_at(new  Date());
            account.setId_socio(socio.getId());
            membership.setId_socio(socio.getId());
            membership.setCreated_at(new Date());
            membership.setUpdated_at(new Date());
            membership.setStatus(MembershipStatus.ACTIVA);
            
            
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
            
            System.out.println("Seleciona el tipo de membrecia: \n(digita 1 para VIP  \n digita 2 para Regular) ");
            switch (scanner.nextInt()) {
                case 1 -> { 
                    membership.setType(MembershipType.VIP);
                    account.setMoney(100000);
                }
                case 2 -> {
                    membership.setType(MembershipType.REGULAR);
                    account.setMoney(50000);
                }    
                default -> System.out.println("Debes de seleccionar una de las opciones mostrada en pantalla.");
            }
           
            
            //añadir asocio a la lista de socios
            socios.add(socio);
            memberships.add(membership);
            accounts.add(account);
            
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
            Membership membership = memberships.stream().filter(item -> item.getId_socio().equals(socio.getId())).findFirst().get();
            System.out.println(socio.getFirst_name() + " - " + socio.getLast_name() + " - " + socio.getDocument_number() + " - " + membership.getType());
        }
        System.out.println("\n\n");
    } 

  
}

