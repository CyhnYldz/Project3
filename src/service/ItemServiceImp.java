package service;

import itemService.Database;
import entity.Item;
import exception.ItemNotFoundException;

import java.util.List;

public class ItemServiceImp implements ItemService{
    // ItemService icerisinde tanilanmis govdesiz tum methodlarin implementation unu bu class da yapacaksiniz...
    // Class ismindeki KIRMIZI ikaza giderseniz 'implement mothods' secenegini goreceksiniz... Onu secin ve baslayin...



    // GetAllItems methodu Database e gidip oradan butun item lari alir ve bir List olarak return eder...
    @Override
    public List<Item> getAllItems() {


        return Database.items;
    }
    // getItemById methodu parametre olarak aldigi id ile Database de boyle bir item var mi diye kontrol eder,
    // yoksa, ItemNotFound exception firlatir, varsa ilgili item i return eder...
    @Override
    public Item getItemById(int id) throws ItemNotFoundException {
        for (Item k:getAllItems()){
            if (k.getId()==id){
                return k;
            }
        }
        throw new ItemNotFoundException("Ürün Bulunamadı");
    }
    // addItem methodu, parametre olarak aldigi item i Database deki items listesine ekler...
    @Override
    public void addItem(Item item) {
        Database.items.add(item);

    }
    // deleteItemById methodu parametre olarak aldigi id ile Database de o urunu arar, bulamazsa ItemnotFound exception firlatir
    // ilgili item mevcut ise onu Database list inden siler...
    @Override
    public void deleteItemById(int id) throws ItemNotFoundException {
        for (Item k:getAllItems()){
            if (k.getId()==id){
                Database.items.remove(k);
            }
        }
        throw new ItemNotFoundException("Ürün Bulunamadı");

    }
    // updateItem methodu parametre olarak aldigi Item i Database de arar, yoksa ItemNotFound exception firlatir,
    // Varsa Datalase deki items listesinden silip, guncel halini Database List ine ekler...
    @Override
    public void updateItem(Item item) throws ItemNotFoundException {

        deleteItemById(item.getId());
        addItem(item);


    }




}
