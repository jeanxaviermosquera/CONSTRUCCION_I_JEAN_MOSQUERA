package com.mycompany.club;

import com.mycompany.club.model.Account;
import com.mycompany.club.model.Beneficiary;
import com.mycompany.club.model.Invoice;
import com.mycompany.club.model.Membership;
import com.mycompany.club.model.Socio;
import com.mycompany.club.utils.InvoiceStatus;
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
    public static List<Beneficiary> beneficiaries = new ArrayList<>();
    public static List<Invoice> invoices = new ArrayList<>();

    public static void main(String[] args) {

        //Menu
        while (true) {
            System.out.println("Menu de opciones");
            System.out.println("1. Afiliar socio.");
            System.out.println("2. Registrar persona autorizada.");
            System.out.println("3. Pagar factura.");
            System.out.println("4. Aumentar fondos.");
            System.out.println("5. Eliminar socio.");
            System.out.println("6. Mostrar socios.");
            System.out.println("Seleccione una opcion: ");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    afiliarSocio();
                    break;
                case 2:
                    personasAutorizadas();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:
                    mostrarSocios();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        }

    }

    private static void afiliarSocio() {
        if (socios.size() < 35) {
            System.out.println("");
            Scanner scanner = new Scanner(System.in);

            Socio socio = new Socio();
            Membership membership = new Membership();
            Account account = new Account();

            //Creaciopn de id universal
            socio.setId(UUID.randomUUID().toString());
            membership.setId(UUID.randomUUID().toString());
            account.setCreated_at(new Date());
            account.setUpdated_at(new Date());
            account.setId_socio(socio.getId());
            membership.setId_socio(socio.getId());
            membership.setCreated_at(new Date());
            membership.setUpdated_at(new Date());
            membership.setStatus(MembershipStatus.ACTIVA);

            System.out.print("\nPor favor, ingrese el nombre del socio: ");
            socio.setFirst_name(scanner.next());

            System.out.print("\nPor favor, ingrese los apellidos del socio: ");
            socio.setLast_name(scanner.next());

            System.out.print("\nPor favor, ingrese la edad: ");
            socio.setAge(scanner.nextInt());

            System.out.print("\nPor favor, ingrese el tipo de documento (CC, CE, TI): ");
            socio.setDocument_type(scanner.next());

            System.out.print("\nPor favor, ingrese la cedula del socio: ");
            socio.setDocument_number(scanner.nextInt());

            if (checkIfDocumentNumberExist(socio.getDocument_number(), "socio")) {
                System.out.println("Ya existe un socio con esta identificacion.");
                return;
            }

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
                default ->
                    System.out.println("Debes de seleccionar una de las opciones mostrada en pantalla.");
            }

            //añadir asocio a la lista de socios
            socios.add(socio);
            memberships.add(membership);
            accounts.add(account);

            System.out.print("\nSocio agregado exitosamente.\n");
        } else {
            System.out.println("Ya no se pueden agregar mas socios");
        }

    }

    public static boolean checkIfDocumentNumberExist(int documentNumber, String userType) {
        switch (userType) {
            case "socio" -> {
                for (Socio socio : socios) {
                    if (socio.getDocument_number() == documentNumber) {
                        return true;
                    }
                }
                return false;
            }
            case "beneficiary" -> {
                for (Beneficiary beneficiary : beneficiaries) {
                    if (beneficiary.getDocument_number() == documentNumber) {
                        return true;
                    }
                }
                return false;
            }
            default -> {
                return false;
            }
        }
    }

    public static void mostrarSocios() {
        for (Socio socio : socios) {
            Membership membership = memberships.stream().filter(item -> item.getId_socio().equals(socio.getId())).findFirst().get();
            System.out.println(socio.getId() + "-" + socio.getFirst_name() + " - " + socio.getLast_name() + " - " + socio.getDocument_number() + " - " + membership.getType());
        }
        System.out.println("\n\n");
    }

    public static void personasAutorizadas() {
        Scanner scanner = new Scanner(System.in);
        Beneficiary beneficiary = new Beneficiary();

        System.out.println("Para agregar un beneficiario primero debes seleccionar un socio\n.");
        System.out.println("Esta es nuestra lista de socios:\n ");
        mostrarSocios();

        System.out.println("Ahora, copia y pega el ID del socio acontinuacion: ");

        String id_socio = scanner.next();

        if (checkIfSoicioCanAddBeneficiary(id_socio)) {
            if (numberOfBeneficiariesRegisteredBySocio(id_socio) < 10) {
                System.out.println("Ingrese el nombre del beneficiario: ");
                beneficiary.setFirst_name(scanner.next());

                System.out.print("\nPor favor, ingrese los apellidos del beneficiario: ");
                beneficiary.setLast_name(scanner.next());

                System.out.print("\nPor favor, ingrese la edad del beneficiario: ");
                beneficiary.setAge(scanner.nextInt());

                System.out.print("\nPor favor, ingrese la cedula del socio: ");
                beneficiary.setDocument_number(scanner.nextInt());

                System.out.print("\nPor favor, ingrese el tipo de documento del beneficiario (CC, CE, TI): ");
                beneficiary.setDocument_type(scanner.next());

                if (checkIfDocumentNumberExist(beneficiary.getDocument_number(), "beneficiary")) {
                    System.out.println("Ya existe un beneficiario con esta identificacion.");
                    return;
                }
                
                beneficiaries.add(beneficiary);

                System.out.println("Beneficiario registrado correctamente.");
            } else {
                System.out.println("Ya tienes 10 beneficiarios registrados.");
                return;
            }

        } else {
            System.out.println("No pueedes agregar un beneficiario hasta no tener saldo disponible.");
            return;
        }

    }

    public static int numberOfBeneficiariesRegisteredBySocio(String id_socio) {
        List<Beneficiary> beneficiariesBySocio = beneficiaries.stream().filter(item -> item.getId_socio().equals(id_socio)).toList();
        return beneficiariesBySocio.size();
    }

    public static boolean checkIfSoicioCanAddBeneficiary(String id_socio) {
        List<Invoice> invoicesFiltered = invoices.stream().filter(item -> item.getId_socio().equals(id_socio)).toList();
        Account account = accounts.stream().filter(item -> item.getId_socio().equals(id_socio)).findFirst().get();

        double total = 0;
        double result;

        for (Invoice invoice : invoicesFiltered) {
            if (invoice.getStatus().equals(InvoiceStatus.PENDING)) {
                total = total + invoice.getAmount();
            }
        }
        result = account.getMoney() - total;
        return result > 0;
    }
}
