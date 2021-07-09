import entity.Address;
import entity.Contact;
import service.AddressService;
import service.ContactService;
import util.Database;

public class Main {
    public static void main(String[] args) {

        Contact contact = new Contact();
        Address address = new Address();
        AddressService addressService = new AddressService();
        ContactService contactService = new ContactService();

//        contact.setName("Liv");
//        contact.setAge(2);
//        contactService.save(contact);
//
        System.out.println(contactService.getAll());
        System.out.println(addressService.getAll());

//        contact.setId(6);
//        contact.setName("Alex");
//        contact.setAge(27);
//        contactService.save(contact);

//        contactService.delete(3);

//        address.setCity("Lviv");
//        address.setStreet("Kyivska");
//        address.setHouseNumber(131);
//        addressService.save(address);


//        System.out.println(addressService.getAll());

//        contactService.delete(1);
//        addressService.delete(3);


    }
}
