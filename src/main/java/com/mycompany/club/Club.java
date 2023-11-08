package com.mycompany.club;

import com.mycompany.club.model.Account;
import com.mycompany.club.model.Beneficiary;
import com.mycompany.club.model.Invoice;
import com.mycompany.club.model.Items;
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
            System.out.println("4. Registrar un consumo en la cuenta de un socio.");
            System.out.println("5. Aumentar fondos.");
            System.out.println("6. Eliminar socio.");
            System.out.println("7. Mostrar socios.");
            System.out.println("8. Mostrar facturas.");
            System.out.println("Seleccione una opcion: ");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    createSocio();
                    break;
                case 2:
                    addBeneficiaries();
                    break;
                case 3:
                    //payInvoice();
                    break;
                case 4:
                    createConsumption();
                    break;
                case 5:
                    addMoreMoneyToAccount();
                    break;
                case 6:
                    deleteSocioById();
                    break;
                case 7:
                    showSocios();
                    break;
                case 8:
                    showInvoices();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

        }

    }

    private static void createConsumption() {
        Scanner scanner = new Scanner(System.in);

        Invoice invoice = new Invoice();
        List<Items> items = new ArrayList<>();
        int total = 0;

        invoice.setCreated_at(new Date());
        invoice.setUpdated_at(new Date());
        invoice.setStatus(InvoiceStatus.PENDING);

        System.out.println("Ingresa concepto de la factura: ");
        invoice.setConcept(scanner.nextLine());

        System.out.println("Para ingresar los items de la factura, utiliza el siguiente formato: nombre del item 1,precio; nombre del item 2,precio;");
        System.out.println("Ejemplo: Portatil,1000000;nevera,320000");
        System.out.println("Ingresa los items:  \n");

        String itemsValue = scanner.nextLine();

        try {
            if (!itemsValue.isEmpty()) {
                String[] itemsSplited = itemsValue.split(";");
                for (String item : itemsSplited) {
                    String[] itemsFinalSplited = item.split(",");
                    items.add(new Items(itemsFinalSplited[0], itemsFinalSplited[1]));
                    total = total + Integer.parseInt(itemsFinalSplited[1]);
                }
                invoice.setItems(items);
                invoice.setAmount(total);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Ingresa el numero de documento del socio al que se le debe registrar esta factura: ");
        String documentNumber = scanner.next();

        List<Socio> socioFiltered = socios.stream().filter(item -> item.getDocument_number().equals(documentNumber)).toList();

        if (socioFiltered.isEmpty()) {
            System.out.println("No existe un socio con la cédula recibida como parámetro.");
            return;
        }

        invoice.setId_socio(socioFiltered.get(0).getId());

        if (countInvoicesPendingBySocio(socioFiltered.get(0).getId()) < 20) {
            double result = amountAvailableBySocio(socioFiltered.get(0).getId()) - total;
            if (result >= 0) {
                invoices.add(invoice);
                System.out.println("Factura registrada exitosamente.");
                return;
            } else {
                System.out.println("No se puede registrar la factura por falta de fondos.");
                return;
            }
        } else {
            System.out.println("Un socio solo puede tener maximo 20 facturas pendientes por pagar.");
            return;
        }

    }

    private static void addMoreMoneyToAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa el numero de documento del socio que deseas ingresar mas dinero a la cuenta: ");
        String documentNumber = scanner.next();

        System.out.println("Ingresa monto que deseas ingresar a la cuenta: ");
        double amount = scanner.nextDouble();

        List<Socio> socioFiltered = socios.stream().filter(item -> item.getDocument_number().equals(documentNumber)).toList();

        if (socioFiltered.isEmpty()) {
            System.out.println("No existe un socio con la cédula recibida como parámetro.");
            return;
        }

        Membership membership = memberships.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getId())).findFirst().get();
        if (membership.getType().equals(MembershipType.VIP)) {
            if (amount > 5000000) {
                System.out.println("Para VIP se puede agregar maximo $5'000.000 a la cuenta.");
                return;
            } else {
                accounts.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getDocument_number()))
                        .forEach(account -> account.setMoney(account.getMoney() + amount));
                accounts.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getDocument_number()))
                        .forEach(account -> account.setUpdated_at(new Date()));
                System.out.println("Cuenta fondeada exitosamente.");
            }
        }

        if (membership.getType().equals(MembershipType.REGULAR)) {
            if (amount > 1000000) {
                System.out.println("Para REGULAR se puede agregar maximo $1'000.000 a la cuenta.");
                return;
            } else {
                accounts.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getDocument_number()))
                        .forEach(account -> account.setMoney(account.getMoney() + amount));
                accounts.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getDocument_number()))
                        .forEach(account -> account.setUpdated_at(new Date()));
                System.out.println("Cuenta fondeada exitosamente.");
            }
        }

    }

    private static boolean deleteSocioById() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingresa el numero de documento del socio a eliminar: ");

        String documentNumber = scanner.next();

        List<Socio> socioFiltered = socios.stream().filter(item -> item.getDocument_number().equals(documentNumber)).toList();

        if (socioFiltered.isEmpty()) {
            System.out.println("No existe un socio con la cédula recibida como parámetro.");
            return false;
        }

        Membership membership = memberships.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getId())).findFirst().get();
        if (membership.getType().equals(MembershipType.VIP)) {
            System.out.println("No se pueden eliminar socios de tipo VIP.");
            return false;
        }

        List<Invoice> invoicesFilteredBySocio = invoices.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getId())).toList();
        long invoicesPending = invoicesFilteredBySocio.stream().filter(item -> item.getStatus().equals(InvoiceStatus.PENDING)).count();
        if (invoicesPending > 0) {
            System.out.println("No se pueden eliminar socios con facturas pendientes de pago.");
            return false;
        }

        long beneficiariesBySocio = beneficiaries.stream().filter(item -> item.getId_socio().equals(socioFiltered.get(0).getId())).count();
        if (beneficiariesBySocio > 0) {
            System.out.println("No se puede eliminar un socio con más de un beneficiario.");
            return false;
        }

        //Deleting socio
        socios.stream()
                .filter(item -> item.getDocument_number().equals(documentNumber))
                .findFirst()
                .map(socio -> {
                    socios.remove(socio);
                    return socio;
                });
        System.out.println("Socio eliminado correctamente.");
        return true;
    }

    private static void createSocio() {
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
            socio.setDocument_number(scanner.next());

            if (checkIfDocumentNumberExist(socio.getDocument_number(), "socio")) {
                System.out.println("Ya existe un socio con esta identificacion.");
                return;
            }

            System.out.println("Seleciona el tipo de membrecia: \n(digita 1 para VIP  \n digita 2 para Regular) ");
            int membershipType = scanner.nextInt();
            MembershipType type;

            switch (membershipType) {
                case 1 -> {
                    type = MembershipType.VIP;
                    membership.setType(MembershipType.VIP);
                    account.setMoney(100000);
                }
                case 2 -> {
                    type = MembershipType.REGULAR;
                    membership.setType(MembershipType.REGULAR);
                    account.setMoney(50000);
                }
                default -> {
                    System.out.println("Debes de seleccionar una de las opciones mostrada en pantalla.");
                    return;
                }
            }
            if (checkIsCanBeAddNewSocio(type)) {
                socios.add(socio);
                memberships.add(membership);
                accounts.add(account);
                System.out.print("\nSocio agregado exitosamente.\n");
            } else {
                System.out.println("Ya no puedes agregar mas socios VIP.");
                return;
            }

        } else {
            System.out.println("Ya no se pueden agregar mas socios");
        }

    }

    private static boolean checkIfDocumentNumberExist(String documentNumber, String userType) {
        switch (userType) {
            case "socio" -> {
                for (Socio socio : socios) {
                    if (socio.getDocument_number().equals(documentNumber)) {
                        return true;
                    }
                }
                return false;
            }
            case "beneficiary" -> {
                for (Beneficiary beneficiary : beneficiaries) {
                    if (beneficiary.getDocument_number().equals(documentNumber)) {
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

    private static void showSocios() {
        for (Socio socio : socios) {
            Membership membership = memberships.stream().filter(item -> item.getId_socio().equals(socio.getId())).findFirst().get();
            System.out.println(socio.getId() + "-" + socio.getFirst_name() + " - " + socio.getLast_name() + " - " + socio.getDocument_number() + " - " + membership.getType());
        }
        System.out.println("\n\n");
    }

    private static void showInvoices() {
        for (Invoice invoice : invoices) {
            System.out.println(invoice.getId_socio() + "-" + invoice.getAmount() + " - " + invoice.getStatus() + " - " + invoice.getConcept());
        }
        System.out.println("\n\n");
    }

    private static void addBeneficiaries() {
        Scanner scanner = new Scanner(System.in);
        Beneficiary beneficiary = new Beneficiary();

        System.out.println("Para agregar un beneficiario primero debes seleccionar un socio\n.");
        System.out.println("Esta es nuestra lista de socios:\n ");
        showSocios();

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

                System.out.print("\nPor favor, ingrese el tipo de documento del beneficiario (CC, CE, TI): ");
                beneficiary.setDocument_type(scanner.next());

                System.out.print("\nPor favor, ingrese la cedula del socio: ");
                beneficiary.setDocument_number(scanner.next());

                beneficiary.setId_socio(id_socio);

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

    private static int numberOfBeneficiariesRegisteredBySocio(String id_socio) {
        List<Beneficiary> beneficiariesBySocio = beneficiaries.stream().filter(item -> item.getId_socio().equals(id_socio)).toList();
        return beneficiariesBySocio.size();
    }

    private static boolean checkIfSoicioCanAddBeneficiary(String id_socio) {
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

    private static double amountAvailableBySocio(String id_socio) {
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
        return result;
    }

    private static long countInvoicesPendingBySocio(String id_socio) {
        List<Invoice> invoicesFiltered = invoices.stream().filter(item -> item.getId_socio().equals(id_socio)).toList();
        List<Invoice> invoicesInPending = invoicesFiltered.stream().filter(item -> item.getStatus().equals(InvoiceStatus.PENDING)).toList();

        return invoicesInPending.size();
    }

    private static boolean checkIsCanBeAddNewSocio(MembershipType type) {
        if (type.equals(MembershipType.VIP)) {
            long countVip = memberships.stream().filter(item -> item.getType().equals(MembershipType.VIP)).count();
            return countVip < 3;
        } else {
            return true;
        }
    }
}
