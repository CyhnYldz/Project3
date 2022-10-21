package service;

import entity.Cart;
import entity.CartItem;
import entity.Item;
import entity.User;
import exception.ItemNotFoundException;

public class ShoppingServiceImpl extends AbstractService implements ShoppingService{

    ItemService itemService = new ItemServiceImp();

    @Override
    public void completePurchase(User currentUser, Cart shoppingCart) throws ItemNotFoundException {
        
        

        boolean continueShopping = true;
        while(continueShopping){

            // Kullaniciya hangi urunu almak istedigini soralim...
            System.out.println("Lutfen urun seciminizi yaparak SEPET'e ekleyiniz...");
            System.out.println("---------------------------------------------------");

            // Database de bulunan urunleri alt alta listeleyelim...
            for (int i = 0; i < itemService.getAllItems().size(); i++) {
                System.out.println(itemService.getAllItems().get(i).getName()+" icin "+(i+1)+" yazip ENTER a basiniz");
            }

            // Kullanicinin sectigi urunun id sini 'chosenItemId' isimli bir degiskende tutalim...
            final int chosenItemId = scan.nextInt();
            final Item chosenItem = itemService.getItemById(chosenItemId);

            // Secilen urunden kac adet almak istedigini soralim...
            // Kullanicinin girdisini 'chosenItemCount' isimli bir degiskende tutalim...
            System.out.println("Secilen urun "+chosenItem.getName()+"'den kac adet satin almak istiyorsunuz?");
            final int chosenItemCount = scan.nextInt();

            // CartItem i alisveris sepetine ekleyelim - shoppingCart.addToCart() - methoduyla ekleyelim...
            shoppingCart.addToCart(new CartItem(chosenItem,chosenItemCount));
            System.out.println("Sepetinizde "+shoppingCart.getTotalCartCost()+" Euro degerinde urun bulunmaktadir...");

            // TODO BU SINIFTA DEGISIKLIK YAPILMAYACAKTIR ------------------------------------------------------------------

            boolean isInputValid = true;
            while (isInputValid){
                System.out.println("Alisverisi tamamlamak icin 1 i, devam etmek icin 2'yi tuslayin");
                // Kullanicinin secimini 'userChoice' isimli bir degiskende tutalim...
                final int userChoice = scan.nextInt();

                switch (userChoice) {
                    case 1 : {
                        System.out.println("Odemeniz alinmistir...Bizi tercih ettiginiz icin tesekkur ederiz");
                        System.out.println("Urununuz '" + currentUser.getAddress().getDescription() + "' adresine kargolanacaktir.");
                        shoppingCart.emptyCart();
                        isInputValid = false;
                        continueShopping = false;
                        break;
                    }
                    case 2 : {
                        isInputValid = false;
                        break;
                    }
                    default : System.out.println("Gecerli bir secim yapmadiniz...");
                }
            }
        }
    }

    // Bu service class inda ShoppingService icerisinde tanimli govdesiz methodlarin implementationunu yapacaksiniz...
    // Class ismindeki KIRMIZI ikaza giderseniz 'implement mothods' secenegini goreceksiniz... Onu secin ve baslayin...


    // completePurchase methodu cagirildiginda, daha once ApplicationRunner sinifinda bulunan butun alisveris yapma
    // kodunu bunyesinde barindirir... Scanner ile kullaniciya urun secimi yapma, sepete ekleme vs hususlarinda ihtiyac duydugu
    // Scanner objesine AbstractService i extend ederek ulasir...
    // Return tipi viod bir method olup, ApplicationRunner sinifinda gecen hafta kullandigimiz kodun bu class a tasinmasindan ibarettir..
    // Yukarida tanimlanan itemService, daha once Database sinifinda yer alan fakat yeniden yapilandirilarak ItemService icersinde
    // tanimlanan getItemById(), getAllItems() vb. methodlara ulasabilmek maksadiyla eklenmistir...
    // TODO itemService implementation sinifindaki odevleriniz bitmeden baslamayiniz...
}
